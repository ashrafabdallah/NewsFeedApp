package com.example.feedapplication.presention.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.feedapplication.R
import com.example.feedapplication.databinding.ActivityMainBinding
import com.example.feedapplication.databinding.FragmentWebPageBinding
import com.example.feedapplication.presention.MainActivity
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WebPageFragment : Fragment() {
    lateinit var binding: FragmentWebPageBinding
    lateinit var localViewModel: LocalViewModel
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebPageBinding.bind(view)

        mainBinding = (activity as MainActivity).binding
        localViewModel = (activity as MainActivity).localViewModel
        binding.prodressBar.visibility=View.VISIBLE

        showDataFromNavArgs()


    }

    private fun showDataFromNavArgs() {

        val args: WebPageFragmentArgs by navArgs()
        var url = args.url

        if (url != null) {
            binding.webView.webViewClient = WebViewClient()

            // this will load the url of the website
            binding.webView.loadUrl(url)
            // this will enable the javascript settings
            binding.webView.settings.javaScriptEnabled = true

            // if you want to enable zoom feature
            binding.webView.settings.setSupportZoom(true)
        }

    }
    // Overriding WebViewClient functions
   inner class WebViewClient : android.webkit.WebViewClient() {

        // Load the URL
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.prodressBar .visibility = View.GONE
        }
    }

}