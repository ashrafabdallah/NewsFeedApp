package com.example.feedapplication.presention.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.feedapplication.R
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.databinding.ActivityMainBinding
import com.example.feedapplication.databinding.FragmentNewsDetailsBinding
import com.example.feedapplication.presention.MainActivity
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel


class NewsDetailsFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailsBinding
    lateinit var localViewModel: LocalViewModel
    lateinit var mainBinding: ActivityMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)
        mainBinding = (activity as MainActivity).binding
        localViewModel = (activity as MainActivity).localViewModel
        showDataFromNavArgs()
    }

    private fun showDataFromNavArgs() {
        val args: NewsDetailsFragmentArgs by navArgs()
        var result = args.favoritData
        var homeData = args.hometData
        if (result != null) {
            binding.apply {
                textCategory.text = result!!.sectionName
                textTitle.text = result!!.webTitle
                textDate.text = result!!.webPublicationDate
                btnViewPage.setOnClickListener {
                    val url = result!!.webUrl
                    var bundle = Bundle().apply {
                        putString("url", url)
                    }
                    findNavController().navigate(R.id.webPageFragment, bundle)
                }
                if (result!!.fav) {
                    floatingActionButton.setImageResource(R.drawable.favorit_selected)
                } else {
                    floatingActionButton.setImageResource(R.drawable.favorite_unselected)
                }
                floatingActionButton.setOnClickListener {
                    Log.i("RR", result.toString())
                    if (result!!.fav) {
                        localViewModel.deleteNewsById(result!!.id)
                        result = result!!.copy(fav = false)
                        floatingActionButton.setImageResource(R.drawable.favorite_unselected)
                    } else {
                        localViewModel.savedNews(result!!.copy(fav = true))
                        result = result!!.copy(fav = true)
                        floatingActionButton.setImageResource(R.drawable.favorit_selected)
                    }


                }


            }
        } else if (homeData != null) {
            binding.apply {
                textCategory.text = homeData!!.sectionName
                textTitle.text = homeData!!.webTitle
                textDate.text = homeData!!.webPublicationDate
                btnViewPage.setOnClickListener {
                    val url = homeData!!.webUrl
                    var bundle = Bundle().apply {
                        putString("url", url)
                    }
                    findNavController().navigate(R.id.webPageFragment, bundle)
                }

                var f = homeData!!.fav
                localViewModel.getNewsById(homeData!!.id).observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        if (it.fav) {
                            floatingActionButton.setImageResource(R.drawable.favorit_selected)
                        } else {
                            floatingActionButton.setImageResource(R.drawable.favorite_unselected)
                        }
                    } else {
                        floatingActionButton.setImageResource(R.drawable.favorite_unselected)
                    }
                })
                floatingActionButton.setOnClickListener {
                    Log.i("RR", homeData.toString())
                    if (homeData!!.fav) {
                        localViewModel.deleteNewsById(homeData!!.id)
                        homeData = homeData!!.copy(fav = false)
                        floatingActionButton.setImageResource(R.drawable.favorite_unselected)
                    } else {
                        localViewModel.savedNews(homeData!!.copy(fav = true))
                        homeData = homeData!!.copy(fav = true)
                        floatingActionButton.setImageResource(R.drawable.favorit_selected)
                    }


                }

            }
        }
    }


}