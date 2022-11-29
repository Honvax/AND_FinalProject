package com.alfrsms.and_finalproject.data.room.account

import androidx.room.*

@Dao
interface AccountDao {
    @Query("SELECT * FROM ACCOUNT_DATABASE WHERE USER_NAME == :userName")
    suspend fun readAccountByUsername(userName: String): AccountEntity

    @Query("SELECT * FROM ACCOUNT_DATABASE WHERE id == :id LIMIT 1")
    suspend fun readAccountById(id : Long) : AccountEntity?

    @Query("SELECT * FROM ACCOUNT_DATABASE WHERE EMAIL == :email")
    suspend fun readAccountByEmail(email: String): AccountEntity

    @Insert
    suspend fun insertAccount(account: AccountEntity)

    @Update
    suspend fun updateAccount(account: AccountEntity)

    @Delete
    suspend fun deleteAccount(account: AccountEntity)
}