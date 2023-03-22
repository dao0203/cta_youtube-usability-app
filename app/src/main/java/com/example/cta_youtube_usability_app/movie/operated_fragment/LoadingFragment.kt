package com.example.cta_youtube_usability_app.movie.operated_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.cta_youtube_usability_app.R
import com.example.cta_youtube_usability_app.databinding.FragmentLoadingBinding
import com.example.cta_youtube_usability_app.movie.ChooseControllerLayoutUiState
import com.example.cta_youtube_usability_app.movie.ChooseControllerLayoutViewModel
import com.example.cta_youtube_usability_app.movie.ChooseControllerLayoutViewModelFactory
import com.example.cta_youtube_usability_app.setting.LayoutId
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import kotlinx.coroutines.launch

class LoadingFragment : Fragment() {
    private var binding: FragmentLoadingBinding? = null
    private val chooseControllerLayoutViewModel: ChooseControllerLayoutViewModel by viewModels {
        ChooseControllerLayoutViewModelFactory(SelectedLayoutIdRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("LoadingFragment","遷移しました")
        val navController = view.findNavController()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                chooseControllerLayoutViewModel.getEachSelectedLayoutId()
                chooseControllerLayoutViewModel.chooseControllerLayoutUiState.collect { value ->
                    when (value) {
                        is ChooseControllerLayoutUiState.Loading -> {

                        }
                        is ChooseControllerLayoutUiState.Success -> {
                            // FIXME:Successきてるし、ViewModelからのデータの受け取りも成功してるど、画面遷移しない
                            when (value.portSelectedLayout.portSelectedLayoutId) {
                                LayoutId.YOUTUBE.name -> navController.navigate(R.id.action_loadingFragment_to_youTubeLayoutFragment)
                                LayoutId.LEFT_HANDED.name -> navController.navigate(R.id.action_loadingFragment_to_leftHandedLayoutFragment)
                                LayoutId.RIGHT_HANDED.name -> navController.navigate(R.id.action_loadingFragment_to_rightHandedLayoutFragment)
                            }
                        }
                        is ChooseControllerLayoutUiState.Error -> {

                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
