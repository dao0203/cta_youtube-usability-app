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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            //動画画面かつ横画面の時、ボトムナビゲーションバーが削除
            if (destination.id == R.id.video_destination &&
                resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            ) {
                //ボトムナビゲーションビューの削除
                binding.bottomNavigationView.isVisible = false
                //アクションバーの削除
                supportActionBar?.hide()
                //いずれも、この状態以外では表示されるっぽい
            }
        }
    }
}
