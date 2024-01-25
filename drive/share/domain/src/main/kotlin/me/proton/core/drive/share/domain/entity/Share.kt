/*
 * Copyright (c) 2021-2024 Proton AG.
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
package me.proton.core.drive.share.domain.entity

import kotlinx.serialization.Serializable
import me.proton.core.domain.entity.UserId
import me.proton.core.drive.base.domain.entity.TimestampS
import me.proton.core.drive.volume.domain.entity.VolumeId
import me.proton.core.user.domain.entity.AddressId

@Serializable
data class ShareId(val userId: UserId, val id: String)

data class Share(
    val id: ShareId,
    val volumeId: VolumeId,
    val rootLinkId: String,
    val addressId: AddressId?,
    val isMain: Boolean,
    val isLocked: Boolean,
    val key: String,
    val passphrase: String,
    val passphraseSignature: String,
    val creationTime: TimestampS? = null,
    val type: Type,
) {
    enum class Type {
        UNKNOWN,
        MAIN,
        STANDARD,
        DEVICE,
        PHOTO,
    }
}
