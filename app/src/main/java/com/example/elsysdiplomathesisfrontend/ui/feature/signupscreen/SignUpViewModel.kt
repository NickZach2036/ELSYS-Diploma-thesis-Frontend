package com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signUpData = MutableStateFlow(SignUpData())
    val signUpData = _signUpData.asStateFlow()

    fun updateUsername(username: String) {
        val currentState = _signUpData.value
        _signUpData.value = currentState.copy(username = username)
    }

    fun updatePassword(password: String) {
        val currentState = _signUpData.value
        _signUpData.value = currentState.copy(password = password)
    }

    fun updateVisibility(visibility: Boolean) {
        val currentState = _signUpData.value
        _signUpData.value = currentState.copy(visibility = visibility)
    }

    fun register(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val currentState = _signUpData.value
        _signUpData.value = currentState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = authRepository.register(
                _signUpData.value.username,
                _signUpData.value.password
            )
            if (result.isSuccess) {
                val updated = _signUpData.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
                _signUpData.value = updated
                onSuccess("Registration successful")
            } else {
                val failed = _signUpData.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message ?: "Регистрацията се провали"
                )
                _signUpData.value = failed
                onError(failed.errorMessage ?: "")
            }
        }
    }
}
