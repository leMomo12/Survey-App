package com.mnowo.surveyapp.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MainScreen : Screen("main_screen")
    object NewSurveyScreen : Screen("new_survey_screen")
    object AddSurveyQuestionScreen : Screen("add_survey_question_screen")
}
