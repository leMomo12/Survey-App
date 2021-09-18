package com.mnowo.surveyapp.presentation.NewSurvey

import android.util.Log.d
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewSurveyViewModel @Inject constructor(
    private val repository: SurveyRepository
) : ViewModel() {

    private val _surveyTitle = mutableStateOf("")
    val surveyTitle: State<String> = _surveyTitle

    private val _surveyTitleError = mutableStateOf(false)
    val surveyTitleError: State<Boolean> = _surveyTitleError

    private val _surveyDescription = mutableStateOf("")
    val surveyDescription: State<String> = _surveyDescription

    private val _surveyDescriptionError = mutableStateOf(false)
    val surveyDescriptionError: State<Boolean> = _surveyDescriptionError

    private val _successfulAdded = mutableStateOf(false)
    val successfulAdded: State<Boolean> = _successfulAdded

    fun setSurveyTitleError(isError: Boolean) {
        _surveyTitleError.value = isError
    }

    fun setSurveyTitle(title: String) {
        _surveyTitle.value = title
    }

    fun setSurveyDescriptionError(isError: Boolean) {
        _surveyDescriptionError.value = isError
    }

    fun setSurveyDescription(description: String) {
        _surveyDescription.value = description
    }

    fun setSuccessFullAdded(status: Boolean) {
        _successfulAdded.value = status
    }

    fun checkInputs(title: String, description: String) = viewModelScope.launch {
        when {
            title.trim() == "" -> {
                if (description.trim() == "") {
                    _surveyDescriptionError.value = true
                    _surveyTitleError.value = true
                } else {
                    _surveyDescriptionError.value = false
                    _surveyTitleError.value = true
                }
            }
            description.trim() == "" -> {
                _surveyDescriptionError.value = true
                _surveyTitleError.value = false
            }
            else -> {
                d("Status", "Hello")
                _surveyDescriptionError.value = false
                _surveyTitleError.value = false
                val newSurvey = NewSurvey(1, title, description)
                repository.addSurveyTitleAndDescriptionCaching(newSurvey)
                _successfulAdded.value = true
            }
        }
    }
}