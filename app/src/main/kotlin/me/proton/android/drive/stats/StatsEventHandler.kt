/*
 * Copyright (c) 2023 Proton AG.
 * This file is part of Proton Drive.
 *
 * Proton Drive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Proton Drive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Proton Drive.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.proton.android.drive.stats

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.proton.core.domain.entity.UserId
import me.proton.core.drive.announce.event.domain.entity.Event
import me.proton.core.drive.announce.event.domain.entity.Event.BackupStarted
import me.proton.core.drive.announce.event.domain.entity.Event.Upload
import me.proton.core.drive.announce.event.domain.handler.EventHandler
import javax.inject.Inject

class StatsEventHandler @Inject constructor(
    private val backupCompletedSideEffect: BackupCompletedSideEffect,
    private val backupStartedSideEffect: BackupStartedSideEffect,
    private val uploadSideEffect: UploadSideEffect,
) : EventHandler {

    private val mutex = Mutex()
    override suspend fun onEvent(
        userId: UserId,
        event: Event,
    ) = mutex.withLock {
        when (event) {
            BackupStarted -> backupStartedSideEffect(userId)
            is Event.BackupCompleted -> backupCompletedSideEffect(userId)
            is Upload -> uploadSideEffect(event)
            else -> Unit
        }
    }
}
