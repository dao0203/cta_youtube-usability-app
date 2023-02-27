package com.example.cta_youtube_usability_app.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cta_youtube_usability_app.setting.model.local_data.ButtonPositionEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ButtonPositionViewModel(private val repository: ButtonPositionRepository) : ViewModel() {
    companion object {
        //エラー用
        const val TAG = "ButtonPositionViewModel"
    }

    val allButtonPositionData: StateFlow<List<ButtonPositionEntity>?> =
        flow {
            //flow内でemitメソッドを使用して1つずつ送信される
            emit(repository.getAllButtonPositionData())
        }.stateIn(//stateInメソッドを使用してStateFlowに変換
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun insertButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        try {
            //emit関数で値を送信することが出来る
            emit(repository.insertButtonPosition(buttonPosition))
        } catch (e: Exception) {
            Log.e(TAG, "$e")
        }
    }

    fun updateButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        try {
            emit(repository.updateButtonPosition(buttonPosition))
        } catch (e: Exception) {
            Log.e(TAG, "$e")
        }

    }

    fun deleteButtonPosition(buttonPosition: ButtonPositionEntity) = flow {
        try {
            emit(repository.deleteButtonPosition(buttonPosition))
        } catch (e: Exception) {
            Log.e(TAG, "$e")
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
