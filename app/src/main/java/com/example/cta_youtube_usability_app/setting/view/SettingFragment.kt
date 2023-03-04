package com.example.cta_youtube_usability_app.setting.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cta_youtube_usability_app.LAND_SELECTED_LAYOUT_ID_KEY
import com.example.cta_youtube_usability_app.PORT_SELECTED_LAYOUT_ID_KEY
import com.example.cta_youtube_usability_app.R
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
        setHasOptionsMenu(true)
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
            val radioButton = binding.root.findViewById<RadioButton>(checkedId)
            lifecycleScope.launch {
                //データの更新
                withContext(Dispatchers.IO) {
                    onRandRadioButtonClicked(radioButton)
                }
            }
        }

        //縦向きレイアウトのラジオグループの動作
        binding.portRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = binding.root.findViewById<RadioButton>(checkedId)
            lifecycleScope.launch {
                //データの更新
                withContext(Dispatchers.IO) {
                    onPortRadioButtonClicked(radioButton)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //横向きレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private suspend fun onRandRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                //横向きのレイアウト
                R.id.option_land_youtube_layout ->
                    if (checked) {
                        updateLandSelectedLayoutId(this.requireContext(), "1")
                    }
                R.id.option_land_right_hand ->
                    if (checked) {
                        updateLandSelectedLayoutId(this.requireContext(), "2")
                    }
                R.id.option_land_left_hand ->
                    if (checked) {
                        updateLandSelectedLayoutId(this.requireContext(), "3")
                    }
            }
        }
    }

    //縦向きのレイアウトのラジオボタンの動作でDataStoreのvalueを更新するメソッド
    private suspend fun onPortRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.option_port_youtube_layout ->
                    if (checked) {
                        updatePortSelectedLayoutId(this.requireContext(), "1")
                    }
                R.id.option_port_right_hand ->
                    if (checked) {
                        updatePortSelectedLayoutId(this.requireContext(), "2")
                    }
                R.id.option_port_left_hand ->
                    if (checked) {
                        updatePortSelectedLayoutId(this.requireContext(), "3")
                    }
            }
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
