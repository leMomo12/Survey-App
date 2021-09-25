package com.mnowo.surveyapp.presentation.SplashScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mnowo.surveyapp.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    val loginState = viewModel.isLoggedIn.value

    LaunchedEffect(key1 = true) {
        if(loginState) {
            navController.navigate(Screen.MainScreen.route)
        } else {
            navController.navigate(Screen.LoginScreen.route)
        }
    }
}