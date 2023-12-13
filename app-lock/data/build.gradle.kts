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

plugins {
    id("com.android.library")
}

android {
    namespace = "me.proton.android.drive.lock.data"
}

driveModule(
    hilt = true,
    room = true,
    serialization = true,
    workManager = true,
    i18n = true,
) {
    api(project(":app-lock:domain"))
    api(project(":drive:base:data"))
    api(project(":drive:base:presentation")) // failing to merge values without it.
    api(libs.androidx.biometric)
}
