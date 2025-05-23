package com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _loginData = MutableStateFlow(LoginData())
    val loginData = _loginData.asStateFlow()

    fun updateUsername(username: String) {
        _loginData.update { state -> state.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _loginData.update { state -> state.copy(password = password) }
    }

    fun updateVisibility(visibility: Boolean) {
        _loginData.update { state -> state.copy(visibility = visibility) }
    }
}
