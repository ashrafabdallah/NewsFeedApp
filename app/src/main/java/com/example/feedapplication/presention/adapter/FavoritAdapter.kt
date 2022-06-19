package com.example.feedapplication.presention.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feedapplication.R
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.databinding.FavoritItemBinding
import com.example.feedapplication.databinding.HomeItemBinding
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel

class FavoritAdapter : RecyclerView.Adapter<FavoritAdapter.MyFavoritViewHolder>() {

    private lateinit var localViewModel: LocalViewModel
    fun setLocalViewModel(localViewModel: LocalViewModel) {
        this.localViewModel = localViewModel

    }


    private val callBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoritViewHolder {
        val view = FavoritItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyFavoritViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyFavoritViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyFavoritViewHolder(private val binding: FavoritItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {

            binding.root.setOnClickListener {
                    OnItemClickListener?.let {
                        it(result)

                    }
                }

            binding.title.text = result.webTitle
            binding.category.text = result.sectionName
            binding.btnFavorit.setOnClickListener {


                    if (result.fav) {

                        // delete news

                        localViewModel.deleteNewsById(result.id)
//                        localViewModel.savedNews(result.copy(fav = false))
                        binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
                    } else {

                        localViewModel.savedNews(result.copy(fav = true))
                        binding.btnFavorit.setImageResource(R.drawable.favorit_selected)

                    }

                }

                if (result.fav) {
                    binding.btnFavorit.setImageResource(R.drawable.favorit_selected)
                } else {
                    binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
                }

        }
    }

    private var OnItemClickListener: ((Result) -> Unit)? = null
    fun setItemClickListner(listner: (Result) -> Unit) {
        OnItemClickListener = listner
    }
}