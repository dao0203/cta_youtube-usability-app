package com.example.cta_youtube_usability_app.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cta_youtube_usability_app.setting.LandSelectedLayout
import com.example.cta_youtube_usability_app.setting.PortSelectedLayout
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class ChooseControllerLayoutViewModel(private val repository: SelectedLayoutIdRepository) :
    ViewModel() {

    private val _chooseControllerLayoutState =
        MutableStateFlow<ChooseControllerLayoutUiState>(ChooseControllerLayoutUiState.Loading)
    val chooseControllerLayoutUiState: StateFlow<ChooseControllerLayoutUiState> =
        _chooseControllerLayoutState

    //Ui状態の更新とそれぞれのレイアウトID取得メソッド
    fun getEachSelectedLayoutId() {
        viewModelScope.launch {
            try {
                val landSelectedLayoutIdFlow = repository.landSelectedLayoutFlow
                val portSelectedLayoutIdFlow = repository.portSelectedLayoutFlow
                landSelectedLayoutIdFlow.zip(portSelectedLayoutIdFlow) { landSelectedLayout: LandSelectedLayout, portSelectedLayout: PortSelectedLayout ->
                    Pair(landSelectedLayout, portSelectedLayout)
                }.collect {
                    _chooseControllerLayoutState.value =
                        ChooseControllerLayoutUiState.Success(it.first, it.second)
                }
            } catch (e: Exception) {
                _chooseControllerLayoutState.value =
                    ChooseControllerLayoutUiState.Error(e.toString())
            }
        }
    }
}

class ChooseControllerLayoutViewModelFactory(private val repository: SelectedLayoutIdRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseControllerLayoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChooseControllerLayoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class ChooseControllerLayoutUiState {
    object Loading : ChooseControllerLayoutUiState()
    data class Success(
        val landSelectedLayout: LandSelectedLayout,
        val portSelectedLayout: PortSelectedLayout
    ) : ChooseControllerLayoutUiState()

    data class Error(val e: String) : ChooseControllerLayoutUiState()
}
