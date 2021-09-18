package com.mnowo.surveyapp.presentation.LoginScreen

data class LoginState(
    val isLoading: Boolean = false,
    val isLoggedIn : Boolean = false,
    val isFinished : Boolean = false,
    val error: String = ""
)
