package com.example.cta_youtube_usability_app.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ButtonPositionViewModel(private val repository: ButtonPositionRepository) : ViewModel() {

    val allButtonPositionData: StateFlow<List<ButtonPositionEntity>?> =
        flow<List<ButtonPositionEntity>> {
            //flow内でemitメソッドを使用して1つずつ送信される
            emit(repository.getAllButtonPositionData())
        }.stateIn(//stateInメソッドを使用してStateFlowに変換
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun insertButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        //emit関数で値を送信することが出来r
        emit(repository.insertButtonPosition(buttonPosition))
    }

    fun updateButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        emit(repository.updateButtonPosition(buttonPosition))
    }

    fun deleteButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        emit(repository.deleteButtonPosition(buttonPosition))
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
