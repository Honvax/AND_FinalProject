package com.alfrsms.and_finalproject.ui.profile

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import androidx.work.*
import com.alfrsms.and_finalproject.data.DataStoreManager
import com.alfrsms.and_finalproject.utils.Utils
import com.alfrsms.and_finalproject.worker.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataStoreManager: DataStoreManager, application: Application) : ViewModel() {

    private var outputUri: Uri? = null

    private val workManager = WorkManager.getInstance(application)

    internal val outputWorkInfos: LiveData<List<WorkInfo>> = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    internal fun applyBlur(uri: Uri) {
        var continuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
        blurBuilder.setInputData(createInputDataForUri(uri))

        continuation = continuation.then(blurBuilder.build())

        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .addTag(TAG_OUTPUT)
            .build()

        continuation = continuation.then(save)

        continuation.enqueue()
    }

    private fun createInputDataForUri(uri: Uri): Data {
        val builder = Data.Builder()
        uri.let {
            builder.putString(KEY_IMAGE_URI, uri.toString())
        }
        return builder.build()
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = Utils.uriOrNull(outputImageUri)
    }

    fun editAccount(fullname: String,contact: String,email: String,password:String) {
        viewModelScope.launch {
            dataStoreManager.setFullname(fullname)
            dataStoreManager.setContact(contact)
            dataStoreManager.setEmail(email)
            dataStoreManager.setPassword(password)
        }
    }

    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun uploadImage(image: String) {
        viewModelScope.launch {
            dataStoreManager.setImage(image)
        }
    }

    fun getImage(): LiveData<String> {
        return dataStoreManager.getImage().asLiveData()
    }
}