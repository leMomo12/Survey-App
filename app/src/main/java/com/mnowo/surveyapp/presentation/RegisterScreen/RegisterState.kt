package com.mnowo.surveyapp.presentation.RegisterScreen

data class RegisterState(
    var isLoading: Boolean = false,
    var isRegistered: Boolean = false,
    var error: String = ""
)