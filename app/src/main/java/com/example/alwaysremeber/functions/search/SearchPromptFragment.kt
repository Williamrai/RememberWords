package com.example.alwaysremeber.functions.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.alwaysremeber.R
import com.example.alwaysremeber.databinding.FragmentSearchPromptBinding


class SearchPromptFragment : DialogFragment() {

    companion object {
        fun newInstance(): SearchPromptFragment {
            return SearchPromptFragment()
        }
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchPromptBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_prompt, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        binding.okayBtn.setOnClickListener {
            val bookName = binding.enterWordEdtxt.text.toString()
            viewModel.sendBookName(bookName)
            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            resources.getDimensionPixelSize(R.dimen.dialog_width),
            resources.getDimensionPixelSize(R.dimen.dialog_height)
        )
    }

}