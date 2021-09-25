package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

data class AddSurveyQuestionState(
    var isLoading: Boolean = false,
    var isAdded: Boolean = false,
    var buttonClicked: Boolean = false,
    var error: String = ""
)
