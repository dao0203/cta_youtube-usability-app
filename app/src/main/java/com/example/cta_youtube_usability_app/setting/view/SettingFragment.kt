package com.example.cta_youtube_usability_app.setting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cta_youtube_usability_app.databinding.FragmentSettingBinding
import com.example.cta_youtube_usability_app.setting.LandSelectedLayout
import com.example.cta_youtube_usability_app.setting.LayoutId
import com.example.cta_youtube_usability_app.setting.PortSelectedLayout
import com.example.cta_youtube_usability_app.setting.SelectedLayoutIdRepository
import com.example.cta_youtube_usability_app.setting.SettingViewModel
import com.example.cta_youtube_usability_app.setting.SettingViewModelFactory
import com.example.cta_youtube_usability_app.setting.SettingUiState
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val settingViewModel: SettingViewModel by viewModels {
        SettingViewModelFactory(SelectedLayoutIdRepository(requireContext()))
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
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {//CREATED時に実行されるようにする
                settingViewModel.getEachSelectedLayoutId()
                settingViewModel.settingsUiState.collect { value ->
                    when (value) {
                        is SettingUiState.Loading -> {//ローディング中
                            //プログレスバーを可視化
                            updateVisibility(value)
                        }
                        is SettingUiState.Success -> {//データ取得成功時
                            //ラジオグループを可視化
                            updateVisibility(value)
                            //横レイアウトの指定されたラジオボタンをチェック
                            selectDefaultLandRadioButton(value.landSelectedLayout.landSelectedLayoutId)
                            //縦レイアウトの指定されたラジオボタンがチェック
                            selectDefaultPortRadioButton(value.portSelectedLayout.portSelectedLayoutId)
                        }
                        is SettingUiState.Error -> {//データ取得失敗時
                            //エラーテキストを可視化
                            updateVisibility(value)
                        }
                    }
                }
            }
        }
        //横向きレイアウトのラジオグループの動作
        binding.landRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            updateLandSelectedLayoutId(checkedId)
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
    private fun updateLandSelectedLayoutId(buttonId: Int) {
        when (buttonId) {
            binding.optionLandYoutubeLayout.id ->
                settingViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.YOUTUBE.name))
            binding.optionLandRightHand.id ->
                settingViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.RIGHT_HANDED.name))
            binding.optionLandLeftHand.id ->
                settingViewModel.updateLandSelectedLayoutId(LandSelectedLayout(LayoutId.LEFT_HANDED.name))
        }
    }

    //縦向きのレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private fun updatePortSelectedLayoutId(radioButtonId: Int) {
        when (radioButtonId) {
            binding.optionPortYoutubeLayout.id ->
                settingViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.YOUTUBE.name))
            binding.optionPortRightHand.id ->
                settingViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.RIGHT_HANDED.name))
            binding.optionPortLeftHand.id ->
                settingViewModel.updatePortSelectedLayoutId(PortSelectedLayout(LayoutId.LEFT_HANDED.name))
        }
    }

    // データの通信状況によってUIの状態を更新するメソッド
    private fun updateVisibility(settingUiState: SettingUiState) {
        when (settingUiState) {
            is SettingUiState.Loading -> {
                //プログレスバー以外を不可視化
                binding.landscapeText.isVisible = false
                binding.landRadioGroup.isVisible = false
                binding.errorText.isVisible = false
                binding.portraitText.isVisible = false
                binding.portRadioGroup.isVisible = false
                binding.errorText.isVisible = false
            }
            is SettingUiState.Success -> {
                //プログレスバーを不可視化
                binding.loadingProgressBar.isVisible = false
                //プログレスバーとエラーテキスト以外を可視化
                binding.landscapeText.isVisible = true
                binding.landRadioGroup.isVisible = true
                binding.portraitText.isVisible = true
                binding.portRadioGroup.isVisible = true
            }
            is SettingUiState.Error -> {
                //プログレスバーを不可視化
                binding.loadingProgressBar.isVisible = false
                //エラーテキストを可視化
                binding.errorText.isVisible = true
            }
        }
    }

    //横レイアウトの指定さrたラジオボタンをチェックするメソッド
    private fun selectDefaultLandRadioButton(landSelectedLayoutId: String) {
        when (landSelectedLayoutId) {
            LayoutId.YOUTUBE.name -> binding.optionLandYoutubeLayout.isChecked = true
            LayoutId.RIGHT_HANDED.name -> binding.optionLandRightHand.isChecked = true
            LayoutId.LEFT_HANDED.name -> binding.optionLandLeftHand.isChecked = true
        }
    }

    //縦レイアウトの指定されたラジオボタンをチェックするメソッド
    private fun selectDefaultPortRadioButton(portSelectedLayoutId: String) {
        when (portSelectedLayoutId) {
            LayoutId.YOUTUBE.name -> binding.optionPortYoutubeLayout.isChecked = true
            LayoutId.RIGHT_HANDED.name -> binding.optionPortRightHand.isChecked = true
            LayoutId.LEFT_HANDED.name -> binding.optionPortLeftHand.isChecked = true
        }
    }
}
