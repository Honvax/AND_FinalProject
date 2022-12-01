package com.alfrsms.and_finalproject.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun setContact(contact: String) {
        context.accountDataStore.edit {
            it[CONTACT] = contact
        }
    }

    suspend fun setPassword(password: String) {
        context.accountDataStore.edit {
            it[PASSWORD] = password
        }
    }

    suspend fun setEmail(email: String) {
        context.accountDataStore.edit {
            it[EMAIL] = email
        }
    }

    suspend fun setFullname(fullname: String) {
        context.accountDataStore.edit {
            it[FULLNAME] = fullname
        }
    }


    suspend fun setImage(image: String){
        context.accountDataStore.edit {
            it[IMAGE] = image
        }
    }

    suspend fun statusLogin(isLogin: Boolean) {
        context.accountDataStore.edit {
            it[LOGIN_STATUS] = isLogin
        }
    }

    fun getContact(): Flow<String> {
        return context.accountDataStore.data.map {
            it[CONTACT] ?: ""
        }
    }

    fun getPassword(): Flow<String> {
        return context.accountDataStore.data.map {
            it[PASSWORD] ?: ""
        }
    }

    fun getImage(): Flow<String> {
        return context.accountDataStore.data.map {
            it[IMAGE] ?: ""
        }
    }

    fun getLoginStatus(): Flow<Boolean> {
        return context.accountDataStore.data.map {
            it[LOGIN_STATUS] ?: false
        }
    }

    companion object {
        private const val DATASTORE_NAME = "database"

        private val CONTACT = stringPreferencesKey("contact_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val FULLNAME = stringPreferencesKey("fullname_key")
        private val IMAGE = stringPreferencesKey("image_key")
        private val LOGIN_STATUS = booleanPreferencesKey("login_status_key")

        private val Context.accountDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

}