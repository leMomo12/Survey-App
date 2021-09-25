package com.mnowo.surveyapp.domain.repository

import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.model.Survey
import com.mnowo.surveyapp.domain.model.SurveyQuestion

interface SurveyRepository {

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean

    suspend fun registerWithEmailAndPassword(email: String, password: String): Boolean

    suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey)

    suspend fun addSurvey(survey: Survey): Boolean

    suspend fun addSurveyQuestion(surveyQuestion: SurveyQuestion)

    suspend fun deleteSurveyQuestion()
}