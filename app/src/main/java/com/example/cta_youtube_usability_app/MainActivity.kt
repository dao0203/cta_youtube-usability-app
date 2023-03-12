package com.example.cta_youtube_usability_app

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cta_youtube_usability_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ボトムナビゲーションビューの設定
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id)
        val navController = navHostFragment?.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController!!)

        //ボトムナビゲーションビューを非表示にするかどうかのリスナー設定
        navController.addOnDestinationChangedListener { _, destination, _ ->

            //動画画面の時、アクションバーを削除
            if (destination.id == R.id.video_destination) {
                supportActionBar?.hide()
                //いずれも、この状態以外では表示されるっぽい
            }
        }
    }
}
