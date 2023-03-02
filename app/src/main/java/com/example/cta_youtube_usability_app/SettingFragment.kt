package com.example.cta_youtube_usability_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment

import com.example.cta_youtube_usability_app.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    //RadioGroupのプロパティを作成

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //viewから受け取ったラジオボタンの動作でDataStoreのvalueを更新するメソッド
    suspend fun onRadioButtonClicked(view: View) {

        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                //横向きのレイアウト
                R.id.option_land_youtube_layout ->
                    if (checked) {
                        updateLandSelectedLayoutId(requireContext(), "1")
                    }
                R.id.option_land_right_hand ->
                    if (checked) {
                        updateLandSelectedLayoutId(requireContext(), "2")
                    }
                R.id.option_land_left_hand ->
                    if (checked) {
                        updateLandSelectedLayoutId(requireContext(), "3")
                    }
                //縦向きのレイアウト
                R.id.option_port_youtube_layout ->
                    if (checked) {
                        updatePortSelectedLayoutId(requireContext(), "1")
                    }
                R.id.option_port_right_hand ->
                    if (checked) {
                        updatePortSelectedLayoutId(requireContext(), "2")
                    }
                R.id.option_port_left_hand ->
                    if (checked) {
                        updatePortSelectedLayoutId(requireContext(), "3")
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

//    FIXME：初期設定で実装されていたものなのでコメントアウトしておきました。

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SettingFragment().apply {
//                arguments = Bundle().apply {
//
//                }
//            }
//    }
}
