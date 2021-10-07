package com.mnowo.surveyapp.presentation.LoginScreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnowo.surveyapp.di.Module
import com.mnowo.surveyapp.presentation.MainActivity
import com.mnowo.surveyapp.presentation.theme.SurveyAppTheme
import com.mnowo.surveyapp.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(Module::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {

        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            SurveyAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.LoginScreen.route
                ) {
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }
                }
            }
        }
    }
}