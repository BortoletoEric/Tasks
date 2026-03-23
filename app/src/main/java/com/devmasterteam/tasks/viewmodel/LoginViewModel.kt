package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.local.PreferencesManager
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application.applicationContext)
    private val personRepository: PersonRepository = PersonRepository()

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.login(email, password)
            if (response.isSuccessful && response.body() != null) {
                _login.value = true
            } else {
                _login.value = false
            }
        }
    }

}