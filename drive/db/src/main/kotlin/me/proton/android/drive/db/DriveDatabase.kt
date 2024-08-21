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

package me.proton.android.drive.db

import android.content.Context
import androidx.room.Database
import androidx.room.TypeConverters
import me.proton.core.account.data.db.AccountConverters
import me.proton.core.account.data.db.AccountDatabase
import me.proton.core.account.data.entity.AccountEntity
import me.proton.core.account.data.entity.AccountMetadataEntity
import me.proton.core.account.data.entity.SessionDetailsEntity
import me.proton.core.account.data.entity.SessionEntity
import me.proton.core.challenge.data.db.ChallengeConverters
import me.proton.core.challenge.data.db.ChallengeDatabase
import me.proton.core.challenge.data.entity.ChallengeFrameEntity
import me.proton.core.contact.data.local.db.ContactConverters
import me.proton.core.contact.data.local.db.ContactDatabase
import me.proton.core.contact.data.local.db.entity.ContactCardEntity
import me.proton.core.contact.data.local.db.entity.ContactEmailEntity
import me.proton.core.contact.data.local.db.entity.ContactEmailLabelEntity
import me.proton.core.contact.data.local.db.entity.ContactEntity
import me.proton.core.crypto.android.keystore.CryptoConverters
import me.proton.core.data.room.db.BaseDatabase
import me.proton.core.data.room.db.CommonConverters
import me.proton.core.drive.base.data.db.entity.UrlLastFetchEntity
//import me.proton.core.drive.drivelink.data.db.DriveLinkDatabase
import me.proton.core.drive.link.data.db.LinkDatabase
import me.proton.core.drive.link.data.db.entity.LinkEntity
import me.proton.core.drive.link.data.db.entity.LinkFilePropertiesEntity
import me.proton.core.drive.link.data.db.entity.LinkFolderPropertiesEntity
import me.proton.core.drive.share.data.db.ShareEntity
import me.proton.core.user.data.db.UserConverters
import me.proton.core.user.data.entity.AddressEntity
import me.proton.core.user.data.entity.AddressKeyEntity
import me.proton.core.user.data.entity.UserEntity
import me.proton.core.user.data.entity.UserKeyEntity
import me.proton.core.drive.base.data.db.BaseDatabase as DriveBaseDatabase

@Database(
    entities = [
        // Core
        AccountEntity::class,
        AccountMetadataEntity::class,
        SessionEntity::class,
        SessionDetailsEntity::class,
        UserEntity::class,
        UserKeyEntity::class,
        AddressEntity::class,
        AddressKeyEntity::class,
//        KeySaltEntity::class,
//        PublicAddressEntity::class,
//        PublicAddressKeyEntity::class,
//        HumanVerificationEntity::class,
//        UserSettingsEntity::class,
//        OrganizationEntity::class,
//        OrganizationKeysEntity::class,
//        EventMetadataEntity::class,
//        FeatureFlagEntity::class,
        ChallengeFrameEntity::class,
//        PurchaseEntity::class,
//        GooglePurchaseEntity::class,
//        ObservabilityEventEntity::class,
//        AddressChangeEntity::class,
//        SelfAuditResultEntity::class,
//        NotificationEntity::class,
//        PushEntity::class,
//        TelemetryEventEntity::class,
        ContactCardEntity::class,
        ContactEmailEntity::class,
        ContactEmailLabelEntity::class,
        ContactEntity::class,
//        RecoveryFileEntity::class,
//        PublicAddressInfoEntity::class,
//        PublicAddressKeyDataEntity::class,
//        LabelEntity::class,
        // Drive
//        VolumeEntity::class,
        ShareEntity::class,
//        ShareUrlEntity::class,
//        ShareExternalInvitationEntity::class,
//        ShareInvitationEntity::class,
//        ShareMemberEntity::class,
//        ShareMembershipEntity::class,
        LinkEntity::class,
        LinkFilePropertiesEntity::class,
        LinkFolderPropertiesEntity::class,
//        LinkOfflineEntity::class,
//        LinkDownloadStateEntity::class,
//        DownloadBlockEntity::class,
//        LinkTrashStateEntity::class,
        // Trash
//        TrashWorkEntity::class,
        // MessageQueue
//        MessageEntity::class,
        // AppUiSettings
//        UiSettingsEntity::class,
        // DriveLinkPaged
//        DriveLinkRemoteKeyEntity::class,
        // Sorting
//        SortingEntity::class,
        // Upload
//        LinkUploadEntity::class,
//        UploadBlockEntity::class,
//        UploadBulkEntity::class,
//        UploadBulkUriStringEntity::class,
//        FolderMetadataEntity::class,
//        TrashMetadataEntity::class,
        // Backup
//        BackupConfigurationEntity::class,
//        BackupDuplicateEntity::class,
//        BackupErrorEntity::class,
//        BackupFileEntity::class,
//        BackupFolderEntity::class,
        // Stats
//        InitialBackupEntity::class,
//        UploadStatsEntity::class,
        // UserMessage
//        DismissedQuotaEntity::class,
//        DismissedUserMessageEntity::class,
        // Notification
//        NotificationChannelEntity::class,
//        NotificationEventEntity::class,
//        TaglessNotificationEventEntity::class,
        // Selection
//        LinkSelectionEntity::class,
        // Worker
//        WorkerRunEntity::class,
        // Photos
//        PhotoListingEntity::class,
//        PhotoListingRemoteKeyEntity::class,
        // FeatureFlag
//        DriveFeatureFlagRefreshEntity::class,
//        MediaStoreVersionEntity::class,
        // Device
//        DeviceEntity::class,
        // Base
        UrlLastFetchEntity::class,
        // Log
//        LogEntity::class,
//        LogLevelEntity::class,
//        LogOriginEntity::class,
        // Sharing
//        SharedWithMeListingEntity::class,
//        SharedByMeListingEntity::class,
//        SharedRemoteKeyEntity::class,
    ],
    version = DriveDatabase.VERSION,
    autoMigrations = [
//        AutoMigration(from = 4, to = 5),
//        AutoMigration(from = 5, to = 6),
//        AutoMigration(from = 7, to = 8),
//        AutoMigration(from = 9, to = 10),
//        AutoMigration(from = 13, to = 14),
//        AutoMigration(from = 15, to = 16),
//        AutoMigration(from = 16, to = 17),
//        AutoMigration(from = 17, to = 18, spec = ShareDatabase.DeleteBlockSizeFromShareEntity::class),
//        AutoMigration(from = 18, to = 19),
//        AutoMigration(from = 22, to = 23),
//        AutoMigration(from = 23, to = 24),
//        AutoMigration(from = 26, to = 27),
    ],
    exportSchema = true
)
@TypeConverters(
    // Core
    CommonConverters::class,
    AccountConverters::class,
    UserConverters::class,
    CryptoConverters::class,
//    HumanVerificationConverters::class,
//    UserSettingsConverters::class,
//    EventManagerConverters::class,
    ChallengeConverters::class,
//    CoreNotificationConverters::class,
//    PushConverters::class,
    ContactConverters::class,
//    LabelConverters::class,
    // Drive
//    EventConverters::class,
//    LinkSelectionConverters::class
)
abstract class DriveDatabase :
    BaseDatabase(),
    AccountDatabase,
//    UserDatabase,
//    AddressDatabase,
    ContactDatabase,
//    KeySaltDatabase,
//    HumanVerificationDatabase,
//    PublicAddressDatabase,
//    UserSettingsDatabase,
//    LabelDatabase,
//    OrganizationDatabase,
//    FeatureFlagDatabase,
//    VolumeDatabase,
//    ShareDatabase,
//    ShareUrlDatabase,
//    ShareUserDatabase,
    LinkDatabase,
//    FolderDatabase,
//    LinkAncestorDatabase,
//    LinkOfflineDatabase,
//    LinkDownloadDatabase,
//    LinkTrashDatabase,
//    LinkSelectionDatabase,
//    MessageQueueDatabase,
//    AppUiSettingsDatabase,
//    EventMetadataDatabase,
    ChallengeDatabase,
//    SortingDatabase,
//    LinkUploadDatabase,
//    StatsDatabase,
//    DriveLinkDatabase,
//    DriveLinkPagedDatabase,
//    DriveLinkTrashDatabase,
//    DriveLinkOfflineDatabase,
//    DriveLinkDownloadDatabase,
//    DriveLinkSharedDatabase,
//    DriveLinkSelectionDatabase,
//    NotificationDatabase,
//    PaymentDatabase,
//    BackupDatabase,
//    UserMessageDatabase,
//    ObservabilityDatabase,
//    KeyTransparencyDatabase,
//    WorkerDatabase,
//    CoreNotificationDatabase,
//    PushDatabase,
//    TelemetryDatabase,
//    PhotoDatabase,
//    DriveLinkPhotoDatabase,
//    DriveFeatureFlagDatabase,
//    MediaStoreVersionDatabase,
//    DeviceDatabase,
    DriveBaseDatabase
//    LogDatabase,
//    DeviceRecoveryDatabase
{

    companion object {
        const val VERSION = 66

//        private val migrations = listOf(
//            DriveDatabaseMigrations.MIGRATION_1_2,
//            DriveDatabaseMigrations.MIGRATION_2_3,
//            DriveDatabaseMigrations.MIGRATION_3_4,
//            // AutoMigration(from = 4, to = 5)
//            // AutoMigration(from = 5, to = 6)
//            DriveDatabaseMigrations.MIGRATION_6_7,
//            // AutoMigration(from = 7, to = 8)
//            DriveDatabaseMigrations.MIGRATION_8_9,
//            // AutoMigration(from = 9, to = 10)
//            DriveDatabaseMigrations.MIGRATION_10_11,
//            DriveDatabaseMigrations.MIGRATION_11_12,
//            DriveDatabaseMigrations.MIGRATION_12_13,
//            // AutoMigration(from = 13, to = 14)
//            DriveDatabaseMigrations.MIGRATION_14_15,
//            // AutoMigration(from = 15, to = 16)
//            // AutoMigration(from = 16, to = 17)
//            // AutoMigration(from = 17, to = 18)
//            // AutoMigration(from = 18, to = 19)
//            DriveDatabaseMigrations.MIGRATION_19_20,
//            DriveDatabaseMigrations.MIGRATION_20_21,
//            DriveDatabaseMigrations.MIGRATION_21_22,
//            // AutoMigration(from = 22, to = 23)
//            // AutoMigration(from = 23, to = 24)
//            DriveDatabaseMigrations.MIGRATION_24_25,
//            DriveDatabaseMigrations.MIGRATION_25_26,
//            // AutoMigration(from = 26, to = 27)
//            DriveDatabaseMigrations.MIGRATION_27_28,
//            DriveDatabaseMigrations.MIGRATION_28_29,
//            DriveDatabaseMigrations.MIGRATION_29_30,
//            DriveDatabaseMigrations.MIGRATION_30_31,
//            DriveDatabaseMigrations.MIGRATION_31_32,
//            DriveDatabaseMigrations.MIGRATION_32_33,
//            DriveDatabaseMigrations.MIGRATION_33_34,
//            DriveDatabaseMigrations.MIGRATION_34_35,
//            DriveDatabaseMigrations.MIGRATION_35_36,
//            DriveDatabaseMigrations.MIGRATION_36_37,
//            DriveDatabaseMigrations.MIGRATION_37_38,
//            DriveDatabaseMigrations.MIGRATION_38_39,
//            DriveDatabaseMigrations.MIGRATION_39_40,
//            DriveDatabaseMigrations.MIGRATION_40_41,
//            DriveDatabaseMigrations.MIGRATION_41_42,
//            DriveDatabaseMigrations.MIGRATION_42_43,
//            DriveDatabaseMigrations.MIGRATION_43_44,
//            DriveDatabaseMigrations.MIGRATION_44_45,
//            DriveDatabaseMigrations.MIGRATION_45_46,
//            DriveDatabaseMigrations.MIGRATION_46_47,
//            DriveDatabaseMigrations.MIGRATION_47_48,
//            DriveDatabaseMigrations.MIGRATION_48_49,
//            DriveDatabaseMigrations.MIGRATION_49_50,
//            DriveDatabaseMigrations.MIGRATION_50_51,
//            DriveDatabaseMigrations.MIGRATION_51_52,
//            DriveDatabaseMigrations.MIGRATION_52_53,
//            DriveDatabaseMigrations.MIGRATION_53_54,
//            DriveDatabaseMigrations.MIGRATION_54_55,
//            DriveDatabaseMigrations.MIGRATION_55_56,
//            DriveDatabaseMigrations.MIGRATION_56_57,
//            DriveDatabaseMigrations.MIGRATION_57_58,
//            DriveDatabaseMigrations.MIGRATION_58_59,
//            DriveDatabaseMigrations.MIGRATION_59_60,
//            DriveDatabaseMigrations.MIGRATION_60_61,
//            DriveDatabaseMigrations.MIGRATION_61_62,
//            DriveDatabaseMigrations.MIGRATION_62_63,
//            DriveDatabaseMigrations.MIGRATION_63_64,
//            DriveDatabaseMigrations.MIGRATION_64_65,
//            DriveDatabaseMigrations.MIGRATION_65_66,
//        )

        fun buildDatabase(context: Context): DriveDatabase =
            databaseBuilder<DriveDatabase>(context, "db-drive")
                .fallbackToDestructiveMigrationOnDowngrade()
//                .apply { migrations.forEach { addMigrations(it) } }
                .build()
    }
}
