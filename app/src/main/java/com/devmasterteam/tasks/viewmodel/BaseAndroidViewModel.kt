package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.google.gson.Gson
import retrofit2.Response

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    fun <T> errorMessage(response: Response<T>): ValidationModel {
        return ValidationModel(
            Gson().fromJson(
                response.errorBody()?.string().toString(),
                String::class.java
            )
        )
    }
}