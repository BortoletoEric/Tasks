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


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application.applicationContext)
    private val personRepository = PersonRepository()

    private val _createUser = MutableLiveData<ValidationModel>()
    val createUser: LiveData<ValidationModel> = _createUser

    fun create(name: String, email: String, password: String) {
        viewModelScope.launch {
            val response = personRepository.create(name, email, password, "TRUE")
            if (response.isSuccessful && response.body() != null) {
                _createUser.value = ValidationModel()
            } else {
                val msgJason = response.errorBody()?.string().toString()
                val message = Gson().fromJson(msgJason, String::class.java)
                _createUser.value = ValidationModel(message)
            }
        }
    }
}