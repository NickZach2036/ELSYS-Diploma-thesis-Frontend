package com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QRScannerViewModel() : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun getState(isLogged: Boolean) {
        viewModelScope.launch {
            if (isLogged) {
                _isLoggedIn.emit(true)
            } else {
                _isLoggedIn.emit(false)
            }
        }
    }
}
