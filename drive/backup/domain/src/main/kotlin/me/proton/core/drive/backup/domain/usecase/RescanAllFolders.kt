/*
 * Copyright (c) 2023-2024 Proton AG.
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

package me.proton.core.drive.backup.domain.usecase

import me.proton.core.domain.entity.UserId
import me.proton.core.drive.base.domain.util.coRunCatching
import me.proton.core.drive.link.domain.entity.FolderId
import me.proton.core.drive.linkupload.domain.entity.UploadFileLink
import javax.inject.Inject

class RescanAllFolders @Inject constructor(
    private val resetFoldersUpdateTime: ResetFoldersUpdateTime,
    private val syncFolders: SyncFolders,
) {
    suspend operator fun invoke(folderId: FolderId) = coRunCatching {
        resetFoldersUpdateTime(folderId).getOrThrow()
        syncFolders(folderId, UploadFileLink.BACKUP_PRIORITY).getOrThrow()
    }

    suspend operator fun invoke(userId: UserId) = coRunCatching {
        resetFoldersUpdateTime(userId).getOrThrow()
        syncFolders(userId, UploadFileLink.BACKUP_PRIORITY).getOrThrow()
    }
}
