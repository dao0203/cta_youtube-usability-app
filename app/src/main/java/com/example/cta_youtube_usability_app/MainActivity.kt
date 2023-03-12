package com.example.cta_youtube_usability_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import com.example.cta_youtube_usability_app.databinding.ActivityMainBinding
import com.example.cta_youtube_usability_app.movie.VideoFragment
import com.example.cta_youtube_usability_app.setting.view.SettingFragment

@UnstableApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ボトムナビゲーションビューの設定
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.video_fragment -> replaceFragment(VideoFragment())
                R.id.setting_fragment -> replaceFragment(SettingFragment())
            }
            true
        }
    }

    // 引数のフラグメントを置き換えて画面に表示するメソッド
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.navHostFragment.id, fragment)
            .commit()
    }
}
