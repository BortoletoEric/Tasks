package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.exception.NoInternetException
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.local.PreferencesManager
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseAndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application.applicationContext)
    private val personRepository: PersonRepository =
        PersonRepository(application.applicationContext)
    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = personRepository.login(email, password)
                if (response.isSuccessful && response.body() != null) {
                    val personModel = response.body()!!

                    RetrofitClient.addHeaders(personModel.personKey, personModel.token)

                    super.saveUserAuthentication(personModel)

                    val responsePriority = priorityRepository.getList()
                    if (responsePriority.isSuccessful && responsePriority.body() != null) {
                        priorityRepository.save(responsePriority.body()!!)
                    }

                    _login.value = ValidationModel()
                } else {
                    _login.value = errorMessage(response)
                }
            } catch (e: Exception) {
                _login.value = handleException(e)
            }
        }
    }

    fun verifyUserLogged() {
        viewModelScope.launch {
            val token = preferencesManager.get(TaskConstants.SHARED.TOKEN_KEY)
            val personKey = preferencesManager.get(TaskConstants.SHARED.PERSON_KEY)

            if (token != "" && personKey != "") {
                RetrofitClient.addHeaders(personKey, token)
                _loggedUser.value = true

                val response = priorityRepository.getList()
                if (response.isSuccessful && response.body() != null) {
                    priorityRepository.save(response.body()!!)
                }
            } else {
                _loggedUser.value = false
            }
        }

    }

}