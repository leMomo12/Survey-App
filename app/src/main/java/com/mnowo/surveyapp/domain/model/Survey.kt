package com.mnowo.surveyapp.domain.model

data class Survey(
    var id: Int,
    var title: String,
    var description: String,
    var questions: SurveyQuestion
)
