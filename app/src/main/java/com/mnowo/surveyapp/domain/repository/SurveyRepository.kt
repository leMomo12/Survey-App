package com.mnowo.surveyapp.domain.repository

import com.mnowo.surveyapp.domain.model.NewSurvey

interface SurveyRepository {

    suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey)
}