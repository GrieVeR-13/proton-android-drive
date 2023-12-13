/*
 * Copyright (c) 2021-2023 Proton AG.
 * This file is part of Proton Core.
 *
 * Proton Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Proton Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Proton Core.  If not, see <https://www.gnu.org/licenses/>.
 */
package me.proton.core.drive.upload.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.proton.core.domain.entity.UserId
import me.proton.core.drive.base.data.api.ProtonApiCode.ALREADY_EXISTS
import me.proton.core.drive.base.data.api.ProtonApiCode.FEATURE_DISABLED
import me.proton.core.drive.base.data.extension.log
import me.proton.core.drive.base.data.workmanager.addTags
import me.proton.core.drive.base.data.workmanager.onProtonHttpException
import me.proton.core.drive.base.domain.extension.avoidDuplicateFileName
import me.proton.core.drive.base.domain.extension.trimForbiddenChars
import me.proton.core.drive.base.domain.provider.ConfigurationProvider
import me.proton.core.drive.base.domain.usecase.BroadcastMessages
import me.proton.core.drive.feature.flag.domain.repository.FeatureFlagRepository
import me.proton.core.drive.feature.flag.domain.usecase.RefreshFeatureFlags
import me.proton.core.drive.linkupload.domain.entity.UploadFileLink
import me.proton.core.drive.linkupload.domain.usecase.GetUploadFileLink
import me.proton.core.drive.linkupload.domain.usecase.UpdateName
import me.proton.core.drive.upload.data.extension.isRetryable
import me.proton.core.drive.upload.data.extension.logTag
import me.proton.core.drive.upload.data.extension.retryOrAbort
import me.proton.core.drive.upload.data.manager.UploadWorkManagerImpl.Companion.TAG_UPLOAD_WORKER
import me.proton.core.drive.upload.data.worker.WorkerKeys.KEY_UPLOAD_FILE_LINK_ID
import me.proton.core.drive.upload.data.worker.WorkerKeys.KEY_USER_ID
import me.proton.core.drive.upload.domain.manager.UploadErrorManager
import me.proton.core.drive.upload.domain.usecase.CreateNewFile
import me.proton.core.drive.worker.domain.usecase.CanRun
import me.proton.core.drive.worker.domain.usecase.Done
import me.proton.core.drive.worker.domain.usecase.Run
import java.util.concurrent.TimeUnit

@HiltWorker
@OptIn(ExperimentalCoroutinesApi::class)
class CreateNewFileWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    workManager: WorkManager,
    broadcastMessages: BroadcastMessages,
    getUploadFileLink: GetUploadFileLink,
    uploadErrorManager: UploadErrorManager,
    private val createNewFile: CreateNewFile,
    private val updateName: UpdateName,
    private val refreshFeatureFlags: RefreshFeatureFlags,
    configurationProvider: ConfigurationProvider,
    canRun: CanRun,
    run: Run,
    done: Done,
) : UploadCoroutineWorker(
    appContext = appContext,
    workerParams = workerParams,
    workManager = workManager,
    broadcastMessages = broadcastMessages,
    getUploadFileLink = getUploadFileLink,
    uploadErrorManager = uploadErrorManager,
    configurationProvider = configurationProvider,
    canRun = canRun,
    run = run,
    done = done,
) {

    override suspend fun doLimitedRetryUploadWork(uploadFileLink: UploadFileLink): Result {
        uploadFileLink.logWorkState("creating file")
        createNewFile(uploadFileLink)
            .onFailure { error ->
                error.handleFeatureDisabled() //TODO: this should be handled in Core for any response
                val retryable = error.isRetryable || error.handle(uploadFileLink)
                val canRetry = canRetry()
                error.log(
                    tag = uploadFileLink.logTag(),
                    message = """
                        Creating new file failed "${error.message}" retryable $retryable, 
                        max retries reached ${!canRetry}
                    """.trimIndent().replace("\n", " ")
                )
                return retryOrAbort(retryable && canRetry, error, uploadFileLink.name)
            }
        return Result.success()
    }

    private suspend fun Throwable.handle(uploadFileLink: UploadFileLink): Boolean =
        onProtonHttpException { protonCode ->
            if (protonCode == ALREADY_EXISTS) {
                updateName(
                    uploadFileLinkId = uploadFileLink.id,
                    name = uploadFileLink.name
                        .trimForbiddenChars()
                        .avoidDuplicateFileName()
                )
                true
            } else {
                false
            }
        } ?: false

    private suspend fun Throwable.handleFeatureDisabled() =
        onProtonHttpException { protonCode ->
            if (protonCode == FEATURE_DISABLED) {
                refreshFeatureFlags(userId, FeatureFlagRepository.RefreshId.API_ERROR_FEATURE_DISABLED).getOrNull()
            }
        }

    companion object {
        fun getWorkRequest(
            userId: UserId,
            uploadFileLinkId: Long,
            networkType: NetworkType,
            tags: List<String> = emptyList(),
        ): OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(CreateNewFileWorker::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(networkType)
                        .build()
                )
                .setInputData(
                    Data.Builder()
                        .putString(KEY_USER_ID, userId.id)
                        .putLong(KEY_UPLOAD_FILE_LINK_ID, uploadFileLinkId)
                        .build()
                )
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    WorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .addTags(listOf(userId.id) + TAG_UPLOAD_WORKER + tags)
                .build()
    }
}
