package com.example.feedapplication.presention.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feedapplication.R
import com.example.feedapplication.databinding.ActivityMainBinding
import com.example.feedapplication.databinding.FragmentFavoritsBinding
import com.example.feedapplication.presention.MainActivity
import com.example.feedapplication.presention.adapter.FavoritAdapter
import com.example.feedapplication.presention.adapter.HomeAdapter
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel


class FavoritsFragment : Fragment() {
    lateinit var binding: FragmentFavoritsBinding
    lateinit var localViewModel: LocalViewModel
    lateinit var mainBinding: ActivityMainBinding
    lateinit var favoritAdapter: FavoritAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritsBinding.bind(view)
        mainBinding = (activity as MainActivity).binding
        favoritAdapter = (activity as MainActivity).favoritAdapter
        localViewModel = (activity as MainActivity).localViewModel
        favoritAdapter.setLocalViewModel(localViewModel)
        showTextEmpty()
        initRecyclerView()
        onClickRecyclerViewItem()
        localViewModel.getFavoritsFromDataBase().observe(viewLifecycleOwner, Observer {

            favoritAdapter.differ.submitList(it)
            if (it.isNotEmpty()) {
                binding.testEmpty.visibility = View.GONE
            }else{
                binding.testEmpty.visibility = View.VISIBLE
            }

        })


    }

    private fun showTextEmpty() {
        if (favoritAdapter.differ.currentList.size == 0) {
            binding.testEmpty.visibility = View.VISIBLE

        } else {
            binding.testEmpty.visibility = View.GONE
        }


    }


    private fun initRecyclerView() {
        binding.favoRecy.apply {
            adapter = favoritAdapter
            layoutManager = LinearLayoutManager(context)


        }
    }

    private fun onClickRecyclerViewItem() {
        favoritAdapter.setItemClickListner {
            var bundle = Bundle().apply {
                putSerializable("favoritData", it)
            }
            findNavController().navigate(R.id.newsDetailsFragment, bundle)
        }
    }
}