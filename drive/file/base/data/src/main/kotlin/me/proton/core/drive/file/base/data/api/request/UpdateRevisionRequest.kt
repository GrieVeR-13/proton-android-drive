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
package me.proton.core.drive.file.base.data.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.proton.core.drive.base.data.api.Dto.BLOCK_NUMBER
import me.proton.core.drive.base.data.api.Dto.MANIFEST_SIGNATURE
import me.proton.core.drive.base.data.api.Dto.PHOTO
import me.proton.core.drive.base.data.api.Dto.SIGNATURE_ADDRESS
import me.proton.core.drive.base.data.api.Dto.X_ATTR
import me.proton.core.drive.file.base.data.api.entity.PhotoDto

@Serializable
data class UpdateRevisionRequest(
    @SerialName(MANIFEST_SIGNATURE)
    val manifestSignature: String,
    @SerialName(SIGNATURE_ADDRESS)
    val signatureAddress: String,
    @SerialName(BLOCK_NUMBER)
    val blockNumber: Long,
    @SerialName(X_ATTR)
    val xAttr: String,
    @SerialName(PHOTO)
    val photo: PhotoDto?,
)
