package com.alfrsms.and_finalproject.data.room.account

import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountDao: AccountDao) {
    suspend fun readAccountByUsername(userName: String): AccountEntity {
        return accountDao.readAccountByUsername(userName)
    }

    suspend fun readAccountById(id: Long): AccountEntity? {
        return accountDao.readAccountById(id)
    }

    suspend fun readAccountByEmail(email: String): AccountEntity {
        return accountDao.readAccountByEmail(email)
    }

    suspend fun insertAccount(accountEntity: AccountEntity) {
        accountDao.insertAccount(accountEntity)
    }

    suspend fun updateAccount(accountEntity: AccountEntity) {
        accountDao.updateAccount(accountEntity)
    }

    suspend fun deleteAccount(accountEntity: AccountEntity) {
        accountDao.deleteAccount(accountEntity)
    }

}