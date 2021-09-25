package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import com.mnowo.surveyapp.domain.model.SurveyQuestion

sealed class AddSurveyQuestionEvent {
    data class EnteredQuestionTitle(var title: String) : AddSurveyQuestionEvent()
    data class EnteredQuestionAnswers(var questionAnswers: MutableList<SurveyQuestion>) : AddSurveyQuestionEvent()
    object NextQuestion : AddSurveyQuestionEvent()
    object FinishSurvey : AddSurveyQuestionEvent()
}