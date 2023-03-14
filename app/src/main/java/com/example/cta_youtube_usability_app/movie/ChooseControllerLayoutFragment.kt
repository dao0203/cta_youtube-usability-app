package com.example.cta_youtube_usability_app.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cta_youtube_usability_app.databinding.FragmentChooseControllerLayoutBinding
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import kotlinx.coroutines.launch

class ChooseControllerLayoutFragment : Fragment() {
    private var binding: FragmentChooseControllerLayoutBinding? = null
    private val chooseControllerLayoutViewModel: ChooseControllerLayoutViewModel by viewModels {
        ChooseControllerLayoutViewModelFactory(SelectedLayoutIdRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseControllerLayoutBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                chooseControllerLayoutViewModel.getEachSelectedLayoutId()
                chooseControllerLayoutViewModel.chooseControllerLayoutUiState.collect { value ->
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
