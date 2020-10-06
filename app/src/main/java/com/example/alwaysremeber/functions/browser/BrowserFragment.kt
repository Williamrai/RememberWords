package com.example.alwaysremeber.functions.browser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import com.example.alwaysremeber.R
import com.example.alwaysremeber.databinding.FragmentBrowserBinding
import kotlinx.android.synthetic.main.fragment_browser.*


class BrowserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBrowserBinding.inflate(inflater)
        val args = BrowserFragmentArgs.fromBundle(requireArguments())
        val webView = binding.wordsWebview

        webView.webViewClient = WebViewClient()
        webView.requestFocus()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com/search?q=${args.words} meaning")

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack()
                } else{
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })

        return binding.root
    }



}