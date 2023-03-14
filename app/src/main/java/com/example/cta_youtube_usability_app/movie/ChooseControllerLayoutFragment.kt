package com.example.cta_youtube_usability_app.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cta_youtube_usability_app.databinding.FragmentChooseControllerLayoutBinding


class ChooseControllerLayoutFragment : Fragment() {
    private var binding: FragmentChooseControllerLayoutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseControllerLayoutBinding.inflate(layoutInflater)
        return binding!!.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
