package com.mnowo.surveyapp.presentation.SplashScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mnowo.surveyapp.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isLoggedIn = mutableStateOf<Boolean>(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    init {
        checkWhereToNavigate()
    }

    fun checkWhereToNavigate() = viewModelScope.launch {
        _isLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
    }

}