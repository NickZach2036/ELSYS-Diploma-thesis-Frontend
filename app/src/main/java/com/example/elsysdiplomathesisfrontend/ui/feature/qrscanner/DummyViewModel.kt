package com.example.elsysdiplomathesisfrontend.ui.feature.qrscanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DummyViewModel : ViewModel() {
    var text = MutableStateFlow("started")

    init {
        onTextChanged()
    }

    fun onTextChanged() {
        viewModelScope.launch {
            text.emit("emitted")
        }
    }
}