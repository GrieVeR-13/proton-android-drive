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
 *//*

package me.proton.core.drive.base.domain.usecase

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import me.proton.core.accountmanager.domain.AccountManager
import me.proton.core.drive.messagequeue.domain.MessageQueue
import me.proton.core.drive.messagequeue.domain.entity.BroadcastMessage
import javax.inject.Inject

class ListenToBroadcastMessages @Inject constructor(
    private val messageQueue: MessageQueue<BroadcastMessage>,
    private val accountManager: AccountManager,
) {
    operator fun invoke() = messageQueue.queue.filter { message ->
        val userId = accountManager.getPrimaryUserId().first { userId -> userId != null }
        message.userId == userId?.id
    }
}
*/
