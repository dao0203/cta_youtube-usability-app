package com.example.cta_youtube_usability_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.example.cta_youtube_usability_app.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //アプリケーション起動時にDataStoreに初期値を設定（youtube_layout）
        lifecycleScope.launch{
            withContext(Dispatchers.IO){
                dataStore.edit { layout ->
                    layout[LAND_SELECTED_LAYOUT_ID_KEY] = "youtube_layout"
                    layout[PORT_SELECTED_LAYOUT_ID_KEY] = "youtube_layout"
                }
            }
        }
    }
}
