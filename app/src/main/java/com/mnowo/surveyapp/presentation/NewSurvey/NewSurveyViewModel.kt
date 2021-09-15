package com.mnowo.surveyapp.presentation.NewSurvey

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewSurveyViewModel @Inject constructor(

) : ViewModel() {

    private val _surveyTitle = mutableStateOf("")
    val surveyTitle: State<String> = _surveyTitle

    private val _surveyTitleError = mutableStateOf(false)
    val surveyTitleError: State<Boolean> = _surveyTitleError

    private val _surveyDescription = mutableStateOf("")
    val surveyDescription: State<String> = _surveyDescription

    private val _surveyDescriptionError = mutableStateOf(false)
    val surveyDescriptionError: State<Boolean> = _surveyDescriptionError

    fun setSurveyTitleError(isError: Boolean) {
        _surveyTitleError.value = isError
    }

    fun setSurveyTitle(title: String) {
        _surveyTitle.value = title
    }

    fun setSurveyDescriptionError(isError: Boolean) {
        _surveyDescriptionError.value = isError
    }

    fun setSurveyDescription(description: String) {
        _surveyDescription.value = description
    }
}