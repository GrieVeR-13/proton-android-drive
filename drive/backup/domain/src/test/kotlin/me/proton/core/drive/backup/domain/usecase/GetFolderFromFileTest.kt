/*
 * Copyright (c) 2023 Proton AG.
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

import kotlinx.coroutines.test.runTest
import me.proton.core.drive.backup.data.repository.BackupFileRepositoryImpl
import me.proton.core.drive.backup.data.repository.BackupFolderRepositoryImpl
import me.proton.core.drive.backup.domain.entity.BackupFile
import me.proton.core.drive.backup.domain.entity.BackupFileState
import me.proton.core.drive.backup.domain.entity.BackupFolder
import me.proton.core.drive.base.domain.entity.TimestampS
import me.proton.core.drive.base.domain.extension.bytes
import me.proton.core.drive.db.test.DriveDatabaseRule
import me.proton.core.drive.db.test.myDrive
import me.proton.core.drive.db.test.userId
import me.proton.core.drive.link.domain.entity.FolderId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GetFolderFromFileTest {
    @get:Rule
    val database = DriveDatabaseRule()
    private lateinit var folderId: FolderId

    private lateinit var addFolder: AddFolder
    private lateinit var getFolderFromFile: GetFolderFromFile
    private lateinit var setFiles: SetFiles

    @Before
    fun setUp() = runTest {
        folderId = database.myDrive { }
        val folderRepository = BackupFolderRepositoryImpl(database.db)
        val fileRepository = BackupFileRepositoryImpl(database.db)
        addFolder = AddFolder(folderRepository)
        getFolderFromFile = GetFolderFromFile(folderRepository)
        setFiles = SetFiles(fileRepository)
    }

    @Test
    fun empty() = runTest {
        val folderFromFile = getFolderFromFile(userId, "uri").getOrThrow()

        assertNull(folderFromFile)
    }

    @Test
    fun match() = runTest {
        val backupFolder = BackupFolder(
            bucketId = 0,
            folderId = folderId,
        )
        addFolder(backupFolder)
        setFiles(
            userId = userId,
            backupFiles = listOf(
                BackupFile(
                    bucketId = 0,
                    uriString = "uri",
                    mimeType = "",
                    name = "",
                    hash = "",
                    size = 0.bytes,
                    state = BackupFileState.IDLE,
                    date = TimestampS(0),
                ),
            ),
        )

        val folderFromFile = getFolderFromFile(userId, "uri").getOrThrow()

        assertEquals(backupFolder, folderFromFile)
    }
}
