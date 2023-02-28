package com.example.cta_youtube_usability_app.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ButtonPositionViewModel(private val repository: ButtonPositionEntityRepository) :
    ViewModel() {

    //データベース操作をしているときはUIを一時的に操作できないようにする変数
    //StateFlowでFragmentに値が変化したことを通知する
    private val _isLoading: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success)
    val isLoading: StateFlow<UiState> = _isLoading

    val allButtonPositionData: StateFlow<List<ButtonPositionEntity>?> =
        flow {
            _isLoading.value = UiState.IsLoading
            try {//flow内でemitメソッドを使用して1つずつ送信される
                emit(repository.getAllButtonPositionData())
                _isLoading.value = UiState.Success
            } catch (e: Exception) {
                _isLoading.value = UiState.Error(e)
            }
        }.stateIn(//stateInメソッドを使用してStateFlowに変換
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    fun insertButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _isLoading.value = UiState.IsLoading
                repository.insertButtonPosition(buttonPosition)
                _isLoading.value = UiState.Success
            } catch (e: Exception) {
                _isLoading.value = UiState.Error(e)
            }
        }
    }

    fun updateButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _isLoading.value = UiState.IsLoading
                repository.updateButtonPosition(buttonPosition)
            } catch (e: Exception) {
                _isLoading.value = UiState.Error(e)
            }
        }
    }

    fun deleteButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _isLoading.value = UiState.IsLoading
                repository.deleteButtonPosition(buttonPosition)
            } catch (e: Exception) {
                _isLoading.value = UiState.Error(e)
            }
        }
    }
}

//ButtonPositionViewModelに引数があるため、Factoryを作成していく
class ButtonPositionViewModelFactory(private val repository: ButtonPositionEntityRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ButtonPositionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ButtonPositionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class UiState {
    object Success : UiState()
    data class Error(val exception: Throwable) : UiState()
    object IsLoading : UiState()
}
