package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseAndroidViewModel(application) {
    private val personRepository: PersonRepository = PersonRepository()

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.login(email, password)
            if (response.isSuccessful && response.body() != null) {
                super.saveUserAuthentication(response.body()!!)
                _login.value = ValidationModel()
            } else {
                _login.value = errorMessage(response)
            }
        }
    }

}