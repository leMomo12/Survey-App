package com.mnowo.surveyapp.data.repository

import com.mnowo.surveyapp.data.remote.firebase.FirebaseDb
import com.mnowo.surveyapp.data.room.dao.SurveyDao
import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    private val firebaseDb: FirebaseDb,
    private val surveyDao: SurveyDao
) : SurveyRepository{

    override suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean {
        return firebaseDb.signInWithEmailAndPassword(email, password)
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        return firebaseDb.registerWithEmailAndPassword(email, password)
    }

    override suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey) {
        return surveyDao.addSurveyTitleAndDescriptionCaching(newSurvey)
    }
}