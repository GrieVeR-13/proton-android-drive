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

package me.proton.core.drive.upload.domain.usecase

import me.proton.core.drive.base.domain.usecase.GetSignatureAddress
import me.proton.core.drive.base.domain.util.coRunCatching
import me.proton.core.drive.crypto.domain.usecase.upload.ManifestSignature
import me.proton.core.drive.key.domain.usecase.GetAddressKeys
import me.proton.core.drive.linkupload.domain.entity.UploadFileLink
import me.proton.core.drive.linkupload.domain.usecase.UpdateManifestSignature
import javax.inject.Inject

class UpdateEmptyFileManifestSignature @Inject constructor(
    private val updateManifestSignature: UpdateManifestSignature,
    private val manifestSignature: ManifestSignature,
    private val getSignatureAddress: GetSignatureAddress,
    private val getAddressKeys: GetAddressKeys,
) {
    suspend operator fun invoke(uploadFileLink: UploadFileLink): Result<Unit> = coRunCatching {
        updateManifestSignature(
            uploadFileLinkId = uploadFileLink.id,
            manifestSignature = manifestSignature(
                signKey = getAddressKeys(
                    userId = uploadFileLink.userId,
                    email = getSignatureAddress(uploadFileLink.userId),
                )
            ).getOrThrow()
        ).getOrThrow()
    }
}
