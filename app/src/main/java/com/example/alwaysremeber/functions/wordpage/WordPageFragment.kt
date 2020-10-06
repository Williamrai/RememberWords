package com.example.alwaysremeber.functions.wordpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.alwaysremeber.R
import com.example.alwaysremeber.databinding.FragmentWordPageBinding


class WordPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentWordPageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_word_page, container, false)

        return binding.root
    }

}