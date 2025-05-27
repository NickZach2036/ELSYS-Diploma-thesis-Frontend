package com.example.elsysdiplomathesisfrontend.ui.feature.signupscreen

data class SignUpData(
    val username: String = "",
    val password: String = "",
    val visibility: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
