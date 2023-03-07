package com.example.cta_youtube_usability_app.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class SelectedLayoutIdViewModel(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow<SettingUiState>(SettingUiState.Loading)
    val settingsUiState: StateFlow<SettingUiState> = _settingsUiState

    //Ui状態の更新とそれぞれのレイアウトID取得メソッド
    fun getEachSelectedLayoutId() {
        viewModelScope.launch {
            try {
                val landSelectedLayoutIdFlow = selectedLayoutIdRepository.landSelectedLayoutIdFlow
                val portSelectedLayoutIdFlow = selectedLayoutIdRepository.portSelectedLayoutId
                landSelectedLayoutIdFlow.zip(portSelectedLayoutIdFlow) { landSelectedLayoutId: LandSelectedLayoutId, portSelectedLayoutId: PortSelectedLayoutId ->
                    Pair(landSelectedLayoutId, portSelectedLayoutId)
                }.collect {
                    _settingsUiState.value =
                        SettingUiState.Success(it.first, it.second)
                }
            } catch (e: Exception) {
                _settingsUiState.value = SettingUiState.Error(e)
            }
        }
    }

    //横レイアウトID updateメソッド
    fun updateLandSelectedLayoutId(landSelectedLayoutId: LandSelectedLayoutId) {
        viewModelScope.launch {
            try {
                selectedLayoutIdRepository.updateLandSelectedLayoutId(landSelectedLayoutId)
            } catch (e: Exception) {
                Log.e("updateLandSelectedLayoutIdViewModelError", e.toString())
            }
        }
    }

    //縦レイアウトID updateメソッド
    fun updatePortSelectedLayoutId(portSelectedLayoutId: PortSelectedLayoutId) {
        viewModelScope.launch {
            try {
                selectedLayoutIdRepository.updatePortSelectedLayoutId(portSelectedLayoutId)
            } catch (e: Exception) {
                Log.e("updatePortLandSelectedLayoutIdViewModelError", e.toString())
            }
        }
    }
}

//SelectedLayoutIdViewModelに引数があるので生成していく
class SelectedLayoutIdViewModelFactory(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectedLayoutIdViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SelectedLayoutIdViewModel(selectedLayoutIdRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class SettingUiState {
    object Loading : SettingUiState()
    data class Success(
        val landSelectedLayoutId: LandSelectedLayoutId,
        val portSelectedLayoutId: PortSelectedLayoutId
    ) : SettingUiState()

    data class Error(val e: Exception) : SettingUiState()
}
