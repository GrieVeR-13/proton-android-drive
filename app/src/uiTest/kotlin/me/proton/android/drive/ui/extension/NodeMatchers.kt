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

package me.proton.android.drive.ui.extension

import androidx.compose.ui.test.SemanticsMatcher
import me.proton.core.drive.files.presentation.extension.DriveLinkSemanticsProperties.HasThumbnail
import me.proton.core.drive.files.presentation.extension.DriveLinkSemanticsProperties.ItemType
import me.proton.core.drive.files.presentation.extension.DriveLinkSemanticsProperties.LayoutType
import me.proton.core.drive.files.presentation.extension.DriveLinkSemanticsProperties.LinkName
import me.proton.core.drive.files.presentation.extension.ItemType
import me.proton.core.drive.files.presentation.extension.LayoutType
import me.proton.test.fusion.ui.compose.builders.OnNode
import me.proton.test.fusion.ui.compose.wrappers.NodeMatchers

fun <T : NodeMatchers<T>> NodeMatchers<T>.withLinkName(name: String): T =
    addSemanticMatcher(SemanticsMatcher.expectValue(LinkName, name))

fun <T : NodeMatchers<T>> NodeMatchers<T>.withLayoutType(layoutType: LayoutType): T =
    addSemanticMatcher(SemanticsMatcher.expectValue(LayoutType, layoutType))

fun <T : NodeMatchers<T>> NodeMatchers<T>.withItemType(itemType: ItemType): T =
    addSemanticMatcher(SemanticsMatcher.expectValue(ItemType, itemType))

fun <T : NodeMatchers<T>> NodeMatchers<T>.withThumbnail(hasThumbnail: Boolean): T =
    addSemanticMatcher(SemanticsMatcher.expectValue(HasThumbnail, hasThumbnail))