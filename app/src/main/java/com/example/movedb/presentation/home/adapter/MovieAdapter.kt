package com.example.movedb.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movedb.data.api.model.response.MovieItem
import com.example.movedb.data.api.model.response.PATH_IMAGE_URL
import com.example.movedb.databinding.ViewHolderMovieItemBinding
import com.example.movedb.di.GlideUtils
import com.example.movedb.utils.formatDateServer2DateGrid

internal class MovieAdapter (
    private var context: Context,
) : ListAdapter<MovieItem, MovieAdapter.MovieItemViewHolder>(
    DIFF_CALLBACK
) {

    var onClicked: ((ListMovieUIEvent) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieItemViewHolder {
        val binding = ViewHolderMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieAdapter.MovieItemViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        item?.let {
            holder.bind(item, position)
        }
    }

    internal inner class MovieItemViewHolder(private val binding: ViewHolderMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieItem, position: Int) {
            binding.tvTitle.text = item.title
            binding.tvOverview.text = item.overview
            binding.tvDate.text= item.release_date?.let { formatDateServer2DateGrid(it) }
            binding.tvVoteAverage.text =  "Vote: ${item.vote_average?.times(10)?.toInt().toString().plus("*")}"
            //Download image
            GlideUtils().loadPosterImage(
                context,
                PATH_IMAGE_URL.plus(item.poster_path), binding.ivMovieThumb
            )
            binding.ivMovieThumb.setOnClickListener {
                onClicked?.invoke(ListMovieUIEvent.OnItemClicked(item))
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

sealed class ListMovieUIEvent() {
    data class OnItemClicked(val movieItem: MovieItem) : ListMovieUIEvent()
}
