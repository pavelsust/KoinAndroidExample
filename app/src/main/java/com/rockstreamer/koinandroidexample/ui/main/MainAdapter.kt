package com.rockstreamer.koinandroidexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rockstreamer.koinandroidexample.R
import com.rockstreamer.koinandroidexample.data.model.MovieItem
import com.rockstreamer.koinandroidexample.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_movie.view.*


class MainAdapter(var onClick : OnClickCallback) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    var movieList: MutableList<MovieItem> = arrayListOf()

    fun addAll(item: List<MovieItem>){
        movieList.addAll(item)
        notifyDataSetChanged()
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
        fun bind(movie: MovieItem) {
            itemView.textViewUserName.text = movie.title
            itemView.textViewUserEmail.text = movie.release_date
            Glide.with(itemView.imageViewAvatar.context)
                .load(IMAGE_BASE_URL+movie.poster_path)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent,
                false
            )
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.view.setOnClickListener {
            onClick.onClickItem()
        }
    }

    interface OnClickCallback{
        fun onClickItem()
    }

}