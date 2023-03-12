package com.example.cta_youtube_usability_app.movie

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.cta_youtube_usability_app.databinding.FragmentVideoBinding

//TODO:縦・横画面の向きを変更した時に動画が最初からにならないようにする

@UnstableApi
class VideoFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentVideoBinding.inflate(layoutInflater)
    }
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    //APIレベル24以上のAndroidだと分割ウィンドウモードではアクティブにならないので、
    //プレーヤを初期化する必要がある
    override fun onStart() {
        super.onStart()
        initializePlayer()
    }


    override fun onResume() {
        super.onResume()
        hideSystemUi()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }


    private fun hideSystemUi() {
        //APIレベルが30以上の時
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = binding.videoView.windowInsetsController
            controller?.hide(
                WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
            )
        } else {//APIレベルが29以下の時
            @Suppress("deprecation")
            binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }

    }

    //保存した状態情報を初期化時にプレーヤに提供するメソッド
    private fun releasePlayer() {
        player?.run {
            //現在の再生位置を保存
            this@VideoFragment.playbackPosition = currentPosition
            //ウィンドウインデックスを保存
            this@VideoFragment.currentWindow = currentMediaItemIndex
            //再生・一時停止状態を保存
            this@VideoFragment.playWhenReady = playWhenReady
            release()
        }
        player = null
    }

    //ExoPlayerの初期化をするメソッド
    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                //動画のURIを取得
                val mediaItem =
                    MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")

                //URIをexoPlayerにセット
                exoPlayer.setMediaItem(mediaItem)
                //スマホの向きが変わっても、既存のデータを代入して途中の箇所から再生されるようにする
                //リソースをすぐに取得したらすぐに再生されるようにする
                exoPlayer.playWhenReady = playWhenReady
                //指定されたMediaItemの中の指定された位置にシークする
                exoPlayer.seekTo(currentWindow, playbackPosition)
                //プレイヤーの準備
                exoPlayer.prepare()
            }
    }
}
