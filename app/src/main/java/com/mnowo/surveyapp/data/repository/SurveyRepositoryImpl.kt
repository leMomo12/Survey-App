package com.mnowo.surveyapp.data.repository

import android.util.Log.d
import com.mnowo.surveyapp.data.remote.remote_datasource.FirebaseDb
import com.mnowo.surveyapp.data.local.SurveyDao
import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.model.Survey
import com.mnowo.surveyapp.domain.model.SurveyQuestion
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

    override suspend fun addSurvey(survey: Survey): Boolean {
        return firebaseDb.addSurvey(survey)
    }

    override suspend fun addSurveyQuestion(surveyQuestion: SurveyQuestion) {
        d("AddSurvey", "Repository: ${surveyQuestion.questionAnswers} , ${surveyQuestion.questionTitle}")
        return surveyDao.addSurveyQuestion(surveyQuestion)
    }

    override suspend fun deleteSurveyQuestion() {
        return surveyDao.deleteSurveyQuestion()
    }

    override suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey) {
        return surveyDao.addSurveyTitleAndDescriptionCaching(newSurvey)
    }
}