package com.mnowo.surveyapp.presentation.LoginScreen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnowo.surveyapp.common.TestTags
import com.mnowo.surveyapp.di.Module
import com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen.AddSurveyQuestionScreen
import com.mnowo.surveyapp.presentation.MainActivity
import com.mnowo.surveyapp.presentation.MainScreen.MainScreen
import com.mnowo.surveyapp.presentation.NewSurvey.NewSurveyScreen
import com.mnowo.surveyapp.presentation.RegisterScreen.RegisterScreen
import com.mnowo.surveyapp.presentation.SplashScreen.SplashScreen
import com.mnowo.surveyapp.presentation.theme.SurveyAppTheme
import com.mnowo.surveyapp.presentation.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(Module::class)
class LoginEndToEndTest {

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
                NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
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
        }
    }

    @Test
    fun navigateToRegisterScreen() {
        composeRule.onNodeWithTag(TestTags.LOGIN_SCREEN_TEXT_BUTTON).performClick()

    }

    @Test
    fun login() {
        composeRule.onNodeWithTag(TestTags.LOGIN_EMAIL).performTextInput("c@c.de")
        composeRule.onNodeWithTag(TestTags.LOGIN_PASSWORD).performTextInput("12345435346")

        composeRule.onNodeWithTag(TestTags.LOGIN_EMAIL).assert(hasText("c@c.de"))
        composeRule.onNodeWithTag(TestTags.LOGIN_PASSWORD).assert(hasText("12345435346"))

        composeRule.onNodeWithTag(TestTags.LOGIN_BUTTON).performClick()

        composeRule.onNodeWithTag(TestTags.LOGIN_EMAIL).assertIsNotEnabled()
        composeRule.onNodeWithTag(TestTags.LOGIN_PASSWORD).assertIsNotEnabled()
        composeRule.onNodeWithTag(TestTags.LOGIN_BUTTON).assertIsNotEnabled()
        composeRule.onNodeWithTag(TestTags.LOGIN_PROGRESSBAR).assertIsDisplayed()

    }
}