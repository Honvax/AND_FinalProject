package com.alfrsms.and_finalproject.data.room.account

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "account_database")
data class AccountEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "contact")
    var contact: String? = null,
    @ColumnInfo(name = "email")
    var email: String? = "",
    @ColumnInfo(name = "password")
    var password: String? = "",
    @ColumnInfo(name = "full_name")
    var fullName: String? = "",
) : Parcelable