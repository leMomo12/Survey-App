package com.mnowo.surveyapp.domain.repository

interface SurveyRepository {

    suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean
}