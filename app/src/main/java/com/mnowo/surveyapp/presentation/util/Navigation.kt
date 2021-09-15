package com.mnowo.surveyapp.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen.AddSurveyQuestionScreen
import com.mnowo.surveyapp.presentation.LoginScreen.LoginScreen
import com.mnowo.surveyapp.presentation.MainScreen.MainScreen
import com.mnowo.surveyapp.presentation.NewSurvey.NewSurveyScreen
import com.mnowo.surveyapp.presentation.RegisterScreen.RegisterScreen
import com.mnowo.surveyapp.presentation.SplashScreen.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.AddSurveyQuestionScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.NewSurveyScreen.route) {
            NewSurveyScreen(navController = navController)
        }
        composable(Screen.AddSurveyQuestionScreen.route) {
            AddSurveyQuestionScreen(navController = navController)
        }
    }
}