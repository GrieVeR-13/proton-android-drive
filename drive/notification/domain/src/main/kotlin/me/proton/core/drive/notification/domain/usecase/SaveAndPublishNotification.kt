/*
 * Copyright (c) 2022-2023 Proton AG.
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
package me.proton.core.drive.notification.domain.usecase

import me.proton.core.drive.announce.event.domain.entity.Event
import me.proton.core.drive.base.domain.util.coRunCatching
import me.proton.core.drive.notification.domain.entity.NotificationId
import me.proton.core.drive.notification.domain.repository.NotificationRepository
import javax.inject.Inject

class SaveAndPublishNotification @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val publishNotification: PublishNotification,
) {
    suspend operator fun invoke(
        notificationId: NotificationId,
        event: Event,
    ) = coRunCatching {
        when (notificationId) {
            is NotificationId.User -> {
                notificationRepository.insertNotificationEvent(notificationId, event)
                publishNotification(notificationId, notificationRepository.getAllNotificationEvents(notificationId))
            }
            is NotificationId.App -> publishNotification(notificationId, listOf(event))
        }
    }
}
