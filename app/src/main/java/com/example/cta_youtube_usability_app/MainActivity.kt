package com.example.cta_youtube_usability_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cta_youtube_usability_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
