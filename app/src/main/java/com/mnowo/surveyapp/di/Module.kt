package com.mnowo.surveyapp.di

import android.content.Context
import androidx.room.Room
import com.mnowo.surveyapp.common.Constants
import com.mnowo.surveyapp.data.remote.remote_datasource.FirebaseDb
import com.mnowo.surveyapp.data.repository.SurveyRepositoryImpl
import com.mnowo.surveyapp.data.local.SurveyDatabase
import com.mnowo.surveyapp.data.local.SurveyDao
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideSurveyRepository(
        firebaseDb: FirebaseDb,
        surveyDao: SurveyDao
    ) : SurveyRepository {
        return SurveyRepositoryImpl(firebaseDb, surveyDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SurveyDatabase {
        return Room.databaseBuilder(
            appContext,
            SurveyDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: SurveyDatabase) = db.surveyDao()
}