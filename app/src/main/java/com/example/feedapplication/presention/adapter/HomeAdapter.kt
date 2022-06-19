package com.example.feedapplication.presention.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feedapplication.R
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.databinding.HomeItemBinding
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel


class HomeAdapter :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var isFavo = false


    lateinit var lifecycleOnwer: LifecycleOwner
    private lateinit var localViewModel: LocalViewModel
    fun setLocalViewModel(localViewModel: LocalViewModel) {
        this.localViewModel = localViewModel
    }

    fun setLifeCylOnwer(lifecycleOnwer: LifecycleOwner) {
        this.lifecycleOnwer = lifecycleOnwer
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class HomeViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(result: Result) {
            binding.root.setOnClickListener {
                OnItemClickListener?.let {
                    it(result)

                }
            }
            var f = result.fav
            binding.title.text = result.webTitle
            binding.category.text = result.sectionName
//            binding.btnFavorit.setOnClickListener {
//
//                if (result.fav) {
//
//                    // delete news
//                    localViewModel.deleteNewsById(result.id)
//                    binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
//                } else {
//
//                    localViewModel.savedNews(result.copy(fav = true))
//                    binding.btnFavorit.setImageResource(R.drawable.favorit_selected)
//
//                }
//
//            }

            binding.btnFavorit.setOnClickListener {

                if (f) {
                    // delete news
                    localViewModel.deleteNewsById(result.id)
                    f = false
                   // localViewModel.savedNews(result.copy(fav = false))
                    binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
                } else {


                    localViewModel.savedNews(result.copy(fav = true))
                    binding.btnFavorit.setImageResource(R.drawable.favorit_selected)

                }

            }


            localViewModel.getNewsById(result.id).observe(lifecycleOnwer, Observer {
                if (it != null) {
                    f = it.fav
                    if (it.fav) {
                        binding.btnFavorit.setImageResource(R.drawable.favorit_selected)
                    } else {
                        binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
                    }
                } else {
                    binding.btnFavorit.setImageResource(R.drawable.favorite_unselected)
                }
            })


        }
    }

    private var OnItemClickListener: ((Result) -> Unit)? = null
    fun setItemClickListner(listner: (Result) -> Unit) {
        OnItemClickListener = listner
    }
}