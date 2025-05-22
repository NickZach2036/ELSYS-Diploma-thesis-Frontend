package com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DummyViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var text = MutableStateFlow("started")

    init {
        onTextChanged()
    }

    fun login() {
        viewModelScope.launch {
            authRepository.login("user", "password")
        }
    }

    fun onTextChanged() {
        viewModelScope.launch {
            text.emit("emitted")
        }
    }
}