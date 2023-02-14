package com.example.cta_youtube_usability_app.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPosition
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ButtonPositionViewModel(private val repository: ButtonPositionRepository) : ViewModel() {

    val allButtonPositionData: StateFlow<List<ButtonPosition>?> = repository.allButtonPositionData
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)


    fun insertButtonPosition(buttonPosition: ButtonPosition) = viewModelScope.launch {
        repository.insertButtonPosition(buttonPosition)
    }

    fun updateButtonPosition(buttonPosition: ButtonPosition) = viewModelScope.launch {
        repository.updateButtonPosition(buttonPosition)
    }

    fun deleteButtonPosition(buttonPosition: ButtonPosition) = viewModelScope.launch {
        repository.deleteButtonPosition(buttonPosition)
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