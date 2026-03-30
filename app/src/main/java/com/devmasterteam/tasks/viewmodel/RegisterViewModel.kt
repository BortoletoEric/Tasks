package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import kotlinx.coroutines.launch


class RegisterViewModel(application: Application) : BaseAndroidViewModel(application) {
    private val personRepository = PersonRepository(application.applicationContext)

    private val _createUser = MutableLiveData<ValidationModel>()
    val createUser: LiveData<ValidationModel> = _createUser

    fun create(name: String, email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.create(name, email, password, "TRUE")
            if (response.isSuccessful && response.body() != null) {
                val personModel = response.body()!!

                RetrofitClient.addHeaders(personModel.personKey, personModel.token)

                super.saveUserAuthentication(personModel)
                _createUser.value = ValidationModel()
            } else {
                _createUser.value = errorMessage(response)
            }
        }
    }
}