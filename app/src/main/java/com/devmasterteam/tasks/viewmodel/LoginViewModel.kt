package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.local.PreferencesManager
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application.applicationContext)
    private val personRepository: PersonRepository = PersonRepository()

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.login(email, password)
            if (response.isSuccessful && response.body() != null) {
                _login.value = ValidationModel()
            } else {
                val msgJason = response.errorBody()?.string().toString()
                val message = Gson().fromJson(msgJason, String::class.java)
                _login.value = ValidationModel(message)
            }
        }
    }

}