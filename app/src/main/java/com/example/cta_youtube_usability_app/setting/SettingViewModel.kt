package com.example.cta_youtube_usability_app.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class SettingViewModel(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow<SettingUiState>(SettingUiState.Loading)
    val settingsUiState: StateFlow<SettingUiState> = _settingsUiState

    //Ui状態の更新とそれぞれのレイアウトID取得メソッド
    fun getEachSelectedLayoutId() {
        viewModelScope.launch {
            try {
                val landSelectedLayoutIdFlow = selectedLayoutIdRepository.landSelectedLayoutFlow
                val portSelectedLayoutIdFlow = selectedLayoutIdRepository.portSelectedLayoutFlow
                landSelectedLayoutIdFlow.zip(portSelectedLayoutIdFlow) { landSelectedLayout: LandSelectedLayout, portSelectedLayout: PortSelectedLayout ->
                    Pair(landSelectedLayout, portSelectedLayout)
                }.collect {
                    _settingsUiState.value =
                        SettingUiState.Success(it.first, it.second)
                }
            } catch (e: Exception) {
                _settingsUiState.value = SettingUiState.Error(e.toString())
            }
        }
    }

    //横レイアウトID updateメソッド
    fun updateLandSelectedLayoutId(landSelectedLayout: LandSelectedLayout) {
        viewModelScope.launch {
            try {
                selectedLayoutIdRepository.updateLandSelectedLayoutId(landSelectedLayout)
            } catch (e: Exception) {
                Log.e("updateLandSelectedLayoutIdViewModelError", e.toString())
            }
        }
    }

    //縦レイアウトID updateメソッド
    fun updatePortSelectedLayoutId(portSelectedLayout: PortSelectedLayout) {
        viewModelScope.launch {
            try {
                selectedLayoutIdRepository.updatePortSelectedLayoutId(portSelectedLayout)
            } catch (e: Exception) {
                Log.e("updatePortLandSelectedLayoutIdViewModelError", e.toString())
            }
        }
    }
}

//SelectedLayoutIdViewModelに引数があるので生成していく
class SettingViewModelFactory(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingViewModel(selectedLayoutIdRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class SettingUiState {
    object Loading : SettingUiState()
    data class Success(
        val landSelectedLayout: LandSelectedLayout,
        val portSelectedLayout: PortSelectedLayout
    ) : SettingUiState()

    data class Error(val e: String) : SettingUiState()
}
