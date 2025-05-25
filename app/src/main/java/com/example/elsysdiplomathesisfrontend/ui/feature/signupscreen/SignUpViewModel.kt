package com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _signUpData = MutableStateFlow(SignUpData())
    val signUpData = _signUpData.asStateFlow()

    fun updateUsername(username: String) {
        _signUpData.update { state -> state.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _signUpData.update { state -> state.copy(password = password) }
    }

    fun updateVisibility(visibility: Boolean) {
        _signUpData.update { state -> state.copy(visibility = visibility) }
    }
}
