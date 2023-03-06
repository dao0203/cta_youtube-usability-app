package com.example.cta_youtube_usability_app.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SelectedLayoutIdViewModel(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow<SettingUiState>(SettingUiState.Loading)
    val settingsUiState: StateFlow<SettingUiState> = _settingsUiState

    //Ui状態の更新とそれぞれのレイアウトID取得メソッド
    fun getEachSelectedLayoutId() {
        viewModelScope.launch {
            try {
                val landSelectedLayoutId = getLandSelectedLayoutId()
                val portSelectedLayoutId = getPortSelectedLayoutId()
                _settingsUiState.value =
                    SettingUiState.Success(landSelectedLayoutId, portSelectedLayoutId)
            } catch (e: Exception) {
                _settingsUiState.value = SettingUiState.Error(e)
            }
        }
    }

    //横レイアウトIDの取得メソッド
    private fun getLandSelectedLayoutId(): StateFlow<LandSelectedLayoutId> =
        selectedLayoutIdRepository.landSelectedLayoutIdFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
        initialValue = LandSelectedLayoutId("youtube_layout")//初期値はYouTubeレイアウト
        )

    //縦レイアウトIDの取得メソッド
    private fun getPortSelectedLayoutId(): StateFlow<PortSelectedLayoutId?> =
        selectedLayoutIdRepository.portSelectedLayoutId.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,//値は一つしかないため、ずっと監視させないようにする
            initialValue = PortSelectedLayoutId("youtube_layout")//初期値はYouTubeレイアウト
        )

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
        val landSelectedLayoutId: StateFlow<LandSelectedLayoutId>,
        val portSelectedLayoutId: StateFlow<PortSelectedLayoutId?>
    ) : SettingUiState()

    data class Error(val e: Exception) : SettingUiState()
}
