package com.example.cta_youtube_usability_app.setting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cta_youtube_usability_app.databinding.FragmentSettingBinding
import com.example.cta_youtube_usability_app.setting.LandSelectedLayout
import com.example.cta_youtube_usability_app.setting.LayoutId
import com.example.cta_youtube_usability_app.setting.PortSelectedLayout
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdViewModel
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdViewModelFactory
import com.example.cta_youtube_usability_app.setting.SettingUiState
import kotlinx.coroutines.flow.collectLatest

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
        lifecycleScope.launchWhenStarted {
            //TODO: initialValueがラジオボタンのチェックに関係しているので、ここをDataStore自体のデータで使えるようにしたい
            //      最低限、ここはデフォルトでYouTubeレイアウトならこのままで大丈夫
            selectedLayoutIdViewModel.getEachSelectedLayoutId()
            selectedLayoutIdViewModel.settingsUiState.collectLatest { value ->
                when (value) {
                    is SettingUiState.Loading -> {//ローディング中
                        //ラジオグループを不可視化
                        binding.landRadioGroup.isVisible = false
                        binding.portRadioGroup.isVisible = false
                        //プログレスバーを可視化
                        binding.landscapeProgress.isVisible = true
                        binding.portraitProgress.isVisible = true
                    }
                    is SettingUiState.Success -> {//データ取得成功時
                        //ラジオグループを可視化
                        binding.landRadioGroup.isVisible = true
                        binding.portRadioGroup.isVisible = true
                        //プログレスバーを不可視化
                        binding.landscapeProgress.isVisible = false
                        binding.portraitProgress.isVisible = false
                        //横レイアウトの指定されたラジオボタンをチェック
                        when (value.landSelectedLayout.landSelectedLayoutId) {
                            LayoutId.YOUTUBE.name -> binding.optionLandYoutubeLayout.isChecked =
                                true
                            LayoutId.RIGHT.name -> binding.optionLandRightHand.isChecked = true
                            LayoutId.LEFT.name -> binding.optionLandLeftHand.isChecked = true
                        }
                        //縦レイアウトの指定されたラジオボタンがチェック
                        when (value.portSelectedLayout.portSelectedLayoutId) {
                            LayoutId.YOUTUBE.name -> binding.optionPortYoutubeLayout.isChecked =
                                true
                            LayoutId.RIGHT.name -> binding.optionPortRightHand.isChecked = true
                            LayoutId.LEFT.name -> binding.optionPortLeftHand.isChecked = true
                        }
                    }
                    is SettingUiState.Error -> {
                        binding.landRadioGroup.isVisible = false
                        binding.portRadioGroup.isVisible = false
                    }
                }
            }
        }
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
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.YOUTUBE.name))
            binding.optionLandRightHand.id ->
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.RIGHT.name))
            binding.optionLandLeftHand.id ->
                selectedLayoutIdViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.LEFT.name))
        }
    }

    //縦向きのレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private fun updatePortSelectedLayoutId(radioButtonId: Int) {
        when (radioButtonId) {
            binding.optionPortYoutubeLayout.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.YOUTUBE.name))
            binding.optionPortRightHand.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.RIGHT.name))
            binding.optionPortLeftHand.id ->
                selectedLayoutIdViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.LEFT.name))
        }
    }
}
