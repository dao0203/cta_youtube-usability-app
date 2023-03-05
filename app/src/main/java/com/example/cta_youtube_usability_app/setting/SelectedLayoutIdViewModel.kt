package com.example.cta_youtube_usability_app.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SelectedLayoutIdViewModel(private val selectedLayoutIdRepository: SelectedLayoutIdRepository) :
    ViewModel() {

    //横レイアウトIDの取得変数
    val landSelectedLayoutId: StateFlow<LandSelectedLayoutId?> =
        flow {
            try {
                emit(selectedLayoutIdRepository.getLandSelectedLayoutId())
            } catch (e: Exception) {
                Log.e("landSelectedLayoutIdViewModelError", e.toString())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),//値は一つしかないため、ずっと監視させないようにする
            initialValue = null
        )

    //縦レイアウトIDの取得変数
    val portSelectedLayoutId: StateFlow<PortSelectedLayoutId?> =
        flow {
            try {
                emit(selectedLayoutIdRepository.getPortSelectedLayoutId())
            } catch (e: Exception) {
                Log.e("portSelectedLayoutIdViewModelError", e.toString())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),//値は一つしかないため、ずっと監視させないようにする
            initialValue = null
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
