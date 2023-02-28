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

class ButtonPositionViewModel(private val repository: ButtonPositionRepository) :
    ViewModel() {

    //データベース操作をしているときはUIを一時的に操作できないようにする変数
    //StateFlowでFragmentに値が変化したことを通知する
    private val _settingUiState: MutableStateFlow<SettingUiState> =
        MutableStateFlow(SettingUiState.Normal)
    val settingUiState: StateFlow<SettingUiState> = _settingUiState

    val allButtonPositionData: StateFlow<List<ButtonPositionEntity>?> =
        flow {
            _settingUiState.value = SettingUiState.Loading
            try {//flow内でemitメソッドを使用して1つずつ送信される
                emit(repository.getAllButtonPositionData())
                _settingUiState.value = SettingUiState.Normal
            } catch (e: Exception) {
                _settingUiState.value = SettingUiState.Error(e)
            }
        }.stateIn(//stateInメソッドを使用してStateFlowに変換
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    fun insertButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _settingUiState.value = SettingUiState.Loading
                repository.insertButtonPosition(buttonPosition)
                _settingUiState.value = SettingUiState.Normal
            } catch (e: Exception) {
                _settingUiState.value = SettingUiState.Error(e)
            }
        }
    }

    fun updateButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _settingUiState.value = SettingUiState.Loading
                repository.updateButtonPosition(buttonPosition)
                _settingUiState.value = SettingUiState.Normal
            } catch (e: Exception) {
                _settingUiState.value = SettingUiState.Error(e)
            }
        }
    }

    fun deleteButtonPosition(buttonPosition: ButtonPositionEntity) {
        viewModelScope.launch {
            try {
                _settingUiState.value = SettingUiState.Loading
                repository.deleteButtonPosition(buttonPosition)
                _settingUiState.value = SettingUiState.Normal
            } catch (e: Exception) {
                _settingUiState.value = SettingUiState.Error(e)
            }
        }
    }
}

//ButtonPositionViewModelに引数があるため、Factoryを作成していく
class ButtonPositionViewModelFactory(private val repository: ButtonPositionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ButtonPositionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ButtonPositionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class SettingUiState {
    object Normal : SettingUiState()
    data class Error(val exception: Throwable) : SettingUiState()
    object Loading : SettingUiState()
}
