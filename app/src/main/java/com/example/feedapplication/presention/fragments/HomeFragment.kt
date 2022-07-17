package com.example.feedapplication.presention.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedapplication.R
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.databinding.ActivityMainBinding
import com.example.feedapplication.databinding.FragmentHomeBinding
import com.example.feedapplication.presention.MainActivity
import com.example.feedapplication.presention.adapter.HomeAdapter
import com.example.feedapplication.presention.viewmodel.Homeviewmodel.HomeViewModel
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel
import com.example.foodapp.util.Resource


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var homeAdapter: HomeAdapter
    lateinit var homeViewModel: HomeViewModel
    lateinit var localViewModel: LocalViewModel

    lateinit var mainBinding: ActivityMainBinding
    private var current_page = 1
    private var isScroling = false
    private var isLoading = false
    private var isLastPage = false
    private var resultData: List<Result> = emptyList()
    private var isShow = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mainBinding = (activity as MainActivity).binding
        homeViewModel = (activity as MainActivity).homeViewModel
        homeAdapter = (activity as MainActivity).homeAdapter
        localViewModel = (activity as MainActivity).localViewModel
        homeAdapter.setLocalViewModel(localViewModel)

        initRecyclerView()
        onClickRecyclerViewItem()
        homeViewModel.getConnect()
        homeViewModel.connectData.observe(viewLifecycleOwner, Observer {
            if (it == "ok") {
                getData()
            } else {
                Toast.makeText(context, "No Internet.....", Toast.LENGTH_LONG).show()
            }
        })

        homeAdapter.setLifeCylOnwer(viewLifecycleOwner)
    }

    private fun onClickRecyclerViewItem() {
        homeAdapter.setItemClickListner {
            var bundle = Bundle().apply {
                putSerializable("HometData", it)
            }
            findNavController().navigate(R.id.newsDetailsFragment, bundle)
        }
    }

    private fun initRecyclerView() {
        binding.RecyclerHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(onScrollinsnerLisner)
        }
    }

    private fun getData() {
        showProgressBar()
        homeViewModel.getNews(current_page)
        homeViewModel.newsResponse.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Loading -> {

                    if(isShow){

                        showProgressBar()
                    }else{
                        hideProgressBar()
                    }

                }
                is Resource.Success -> {
                    binding.progressPagging.visibility = View.GONE
                    hideProgressBar()
                    response.data?.let {
                        resultData = resultData + it.response.results
                        homeAdapter.differ.submitList(resultData)

                        Log.i("T", homeAdapter.differ.currentList.size.toString())

                        var pages = 0
                        if (it.response.pages % 10 == 0) {
                            pages = it.response.pages / 10
                            Log.i("P", pages.toString())
                            Toast.makeText(context, pages.toString(), Toast.LENGTH_LONG).show()
                        } else {
                            pages = it.response.pages / 10 + 1
                            Log.i("P2", pages.toString())
                        }
                        isLastPage = current_page == pages

//                        Toast.makeText(context,isLastPage.toString(),Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Error -> {
                    binding.progressPagging.visibility = View.GONE
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(context, "${it}", Toast.LENGTH_LONG).show()

                    }
                }
            }
        })

    }

    fun showProgressBar() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar2.visibility = View.GONE

    }

    private val onScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = binding.RecyclerHome.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                isShow=false
                binding.progressPagging.visibility = View.VISIBLE
                current_page++
                homeViewModel.getNews(current_page)
                isScroling = false
            }
            Log.i("TAGG", sizeOfListItem.toString())
            Log.i("PAGE", current_page.toString())


        }
    }
}