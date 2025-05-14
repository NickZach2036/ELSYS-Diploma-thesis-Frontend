package com.example.elsysdiplomathesisfrontend.ui.feature.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
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

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                authRepository.login(
                    _loginData.value.username,
                    _loginData.value.password
                )
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Грешка при логване")
            }
        }
    }
}
