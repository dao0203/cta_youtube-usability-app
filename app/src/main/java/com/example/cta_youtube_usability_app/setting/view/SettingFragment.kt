package com.example.cta_youtube_usability_app.setting.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cta_youtube_usability_app.LAND_SELECTED_LAYOUT_ID_KEY
import com.example.cta_youtube_usability_app.PORT_SELECTED_LAYOUT_ID_KEY
import com.example.cta_youtube_usability_app.dataStore
import com.example.cta_youtube_usability_app.databinding.FragmentSettingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            lifecycleScope.launch {
                //データの更新
                withContext(Dispatchers.IO) {
                    onLandRadioButtonClicked(checkedId)
                }
            }
        }

        //縦向きレイアウトのラジオグループの動作
        binding.portRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            lifecycleScope.launch {
                //データの更新
                withContext(Dispatchers.IO) {
                    onPortRadioButtonClicked(checkedId)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //横向きレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private suspend fun onLandRadioButtonClicked(buttonId: Int) {
        when (buttonId) {
            binding.optionLandYoutubeLayout.id ->
                updateLandSelectedLayoutId(this.requireContext(), "youtube_layout")
            binding.optionLandRightHand.id ->
                updateLandSelectedLayoutId(this.requireContext(), "right_hand_layout")
            binding.optionLandLeftHand.id ->
                updateLandSelectedLayoutId(this.requireContext(), "left_hand_layout")
        }
    }

    //縦向きのレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private suspend fun onPortRadioButtonClicked(radioButtonId: Int) {
        when (radioButtonId) {
            binding.optionPortYoutubeLayout.id ->
                updatePortSelectedLayoutId(this.requireContext(), "youtube_layout")
            binding.optionPortRightHand.id ->
                updatePortSelectedLayoutId(this.requireContext(), "right_hand_layout")
            binding.optionPortLeftHand.id ->
                updatePortSelectedLayoutId(this.requireContext(), "left_hand_layout")
        }
    }

    //DataStoreの横向きレイアウトIDを更新するメソッド
    private suspend fun updateLandSelectedLayoutId(context: Context, landSelectedLayoutId: String) {
        context.dataStore.edit { layout ->
            layout[LAND_SELECTED_LAYOUT_ID_KEY] = landSelectedLayoutId
        }
    }

    //DataStoreの縦向きレイアウトIDを更新するメソッド
    private suspend fun updatePortSelectedLayoutId(context: Context, portSelectedLayoutId: String) {
        context.dataStore.edit { layout ->
            layout[PORT_SELECTED_LAYOUT_ID_KEY] = portSelectedLayoutId
        }
    }
}
