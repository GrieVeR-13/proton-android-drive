/*
 * Copyright (c) 2024 Proton AG.
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

package me.proton.core.drive.base.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class IsValidEmailAddressTest(
    private val emailAddress: String,
    private val isValid: Boolean,
) {
    private val isValidEmailAddress = IsValidEmailAddress()

    @Test
    fun test() {
        assertEquals(isValid, isValidEmailAddress(emailAddress))
    }

    companion object {
        @get:Parameterized.Parameters(name = "{0} should be a valid email address: {1}")
        @get:JvmStatic
        val data = listOf(
            arrayOf("", false),
            arrayOf("test", false),
            arrayOf("test@", false),
            arrayOf("@test.com", false),
            arrayOf("a@b.c", true),
        )
    }
}

