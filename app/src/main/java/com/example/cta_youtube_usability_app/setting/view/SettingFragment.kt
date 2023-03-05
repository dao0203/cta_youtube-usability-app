package com.example.cta_youtube_usability_app.setting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cta_youtube_usability_app.databinding.FragmentSettingBinding
import com.example.cta_youtube_usability_app.setting.LandSelectedLayoutId
import com.example.cta_youtube_usability_app.setting.PortSelectedLayoutId
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdViewModel
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdViewModelFactory


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val selectedLayoutIdViewModel: SelectedLayoutIdViewModel by viewModels {
        SelectedLayoutIdViewModelFactory(SelectedLayoutIdRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //横向きレイアウトのラジオグループの動作
        binding.landRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            updateRandSelectedLayoutId(checkedId)
        }

        //縦向きレイアウトのラジオグループの動作
        binding.portRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            updatePortSelectedLayoutId(checkedId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //横向きレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private fun updateRandSelectedLayoutId(buttonId: Int) {
        when (buttonId) {
            binding.optionLandYoutubeLayout.id ->
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayoutId("youtube_layout"))
            binding.optionLandRightHand.id ->
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayoutId("right_hand_layout"))
            binding.optionLandLeftHand.id ->
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayoutId("left_hand_layout"))
        }
    }

    //縦向きのレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private fun updatePortSelectedLayoutId(radioButtonId: Int) {
        when (radioButtonId) {
            binding.optionPortYoutubeLayout.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayoutId("youtube_layout"))
            binding.optionPortRightHand.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayoutId("right_hand_layout"))
            binding.optionPortLeftHand.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayoutId("left_hand_layout"))
        }
    }
}
