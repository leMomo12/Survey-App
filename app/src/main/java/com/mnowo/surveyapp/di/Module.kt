package com.mnowo.surveyapp.di

import com.mnowo.surveyapp.data.remote.firebase.FirebaseDb
import com.mnowo.surveyapp.data.repository.SurveyRepositoryImpl
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideSurveyRepository(
        firebaseDb: FirebaseDb
    ) : SurveyRepository {
        return SurveyRepositoryImpl(firebaseDb)
    }
}