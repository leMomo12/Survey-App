package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import android.util.Log.d
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.model.Survey
import com.mnowo.surveyapp.domain.model.SurveyQuestion
import com.mnowo.surveyapp.domain.use_case.AddSurveyQuestionUseCase
import com.mnowo.surveyapp.domain.use_case.AddSurveyUseCase
import com.mnowo.surveyapp.presentation.LoginScreen.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSurveyQuestionViewModel @Inject constructor(
    private val addSurveyUseCase: AddSurveyUseCase,
    private val addSurveyQuestionUseCase: AddSurveyQuestionUseCase
) : ViewModel() {


    val answerList = mutableStateListOf<String>()

    private val _answerCount = mutableStateOf(2)
    val answerCount: State<Int> = _answerCount

    private val _surveyTitle = mutableStateOf("")
    val surveyTitle: State<String> = _surveyTitle

    private val _state = mutableStateOf(AddSurveyQuestionState())
    val state: State<AddSurveyQuestionState> = _state

    private val _isTitleError = mutableStateOf(false)
    val itTitleError: State<Boolean> = _isTitleError

    private val _uiEnabled = mutableStateOf(true)
    val uiEnabled: State<Boolean> = _uiEnabled

    fun setUiEnabled(status: Boolean) {
        _uiEnabled.value = status
    }

    fun setTitleError(status: Boolean) {
        _isTitleError.value = status
    }

    fun increaseAnswerCount() {
        _answerCount.value += 1
    }

    fun decreaseAnswerCount() {
        _answerCount.value -= 1
    }

    fun setSurveyTitle(title: String) {
        _surveyTitle.value = title
    }

    fun setButtonClicked(value: Boolean) {
        _state.value = AddSurveyQuestionState(buttonClicked = value)
    }

    fun addSurvey(surveyQuestion: SurveyQuestion) = viewModelScope.launch {

        if (surveyQuestion.questionAnswers.isBlank() || surveyQuestion.questionAnswers.trim() == "") {
            d("AddSurvey", "Field empty ")
        } else if (surveyQuestion.questionTitle.isBlank() || surveyQuestion.questionTitle.trim() == "") {
            d("AddSurvey", "Title empty")
        } else {
            addSurveyQuestionUseCase(surveyQuestion).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = AddSurveyQuestionState(isLoading = true)
                        d("AddSurvey", "Loading")
                    }
                    is Resource.Error -> {
                        _state.value = AddSurveyQuestionState(
                            error = it.message ?: "An unexpected error occurred"
                        )
                        d("AddSurvey", "Error ${it.message}")
                    }
                    is Resource.Success -> {
                        d("AddSurvey", "Success")
                        _state.value = AddSurveyQuestionState(isAdded = true)
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

}

