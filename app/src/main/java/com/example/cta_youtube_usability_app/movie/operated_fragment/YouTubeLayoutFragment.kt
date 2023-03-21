package com.example.cta_youtube_usability_app.movie.operated_fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.cta_youtube_usability_app.R
import com.example.cta_youtube_usability_app.databinding.FragmentYoutubeLayoutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

//TODO:縦・横画面の向きを変更した時に動画が最初からにならないようにする
//TODO: MediaSessionServiceを実装？（コメントアウトしてる）

@UnstableApi
class YouTubeLayoutFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentYoutubeLayoutBinding.inflate(layoutInflater)
    }
    private var player: ExoPlayer? = null

    //MediaControllerの取得
//    private val component = ComponentName(requireContext(),PlayerService::class.java)
//    val token = SessionToken(requireContext(),component)
//    val controllerFuture = MediaController.Builder(requireContext(),token).buildAsync()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("LeftHandedLayoutFragment","遷移しました")
        //コントロールビューが表示されている間はボトムナビゲーションビューを表示
        binding.videoView.setControllerVisibilityListener(PlayerView.ControllerVisibilityListener { visibility ->
            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            bottomNavigationView.visibility = when (visibility) {
                View.VISIBLE -> View.VISIBLE//コントロールビューが表示されてる時は表示
                else -> View.GONE//コントロールビューが非表示の時は非表示
            }
        })
        //MediaControllerの取得
//        controllerFuture.addListener({
//            val mediaController = controllerFuture.get()
//            binding.videoView.player = mediaController
//        }, MoreExecutors.directExecutor())
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }


    override fun onResume() {
        super.onResume()
        hideSystemUi()
    }

    override fun onPause() {
        super.onPause()
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
        player?.let { exoplayer ->
            //現在の再生位置を保存
            this@YouTubeLayoutFragment.playbackPosition = exoplayer.currentPosition
            //ウィンドウインデックスを保存
            this@YouTubeLayoutFragment.currentWindow = exoplayer.currentMediaItemIndex
            //再生・一時停止状態を保存
            this@YouTubeLayoutFragment.playWhenReady = exoplayer.playWhenReady
            exoplayer.release()
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
