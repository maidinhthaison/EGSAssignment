package com.example.movedb.presentation.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movedb.R
import com.example.movedb.base.BaseFragment
import com.example.movedb.config.PATH_IMAGE_URL
import com.example.movedb.data.api.model.response.MovieItem
import com.example.movedb.databinding.FragmentMovieDetailBinding
import com.example.movedb.di.GlideUtils
import com.example.movedb.presentation.home.HomeFragment
import com.example.movedb.utils.formatDateServer2DateGrid

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>() {
    override fun initBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieItem: MovieItem? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(
                    HomeFragment.MOVIE_ITEM_MODEL,
                    MovieItem::class.java
                )
            } else {
                arguments?.getSerializable(HomeFragment.MOVIE_ITEM_MODEL) as MovieItem

            }
        movieItem?.let { bindData(it) }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun bindData(movieItem: MovieItem){
        binding.tvTitle.text = String.format(getString(R.string.text_movie_title),movieItem.title)
        GlideUtils().loadImageFitCenter(
            requireContext(), movieItem.getBackDropPathURL(), binding.ivBackDrop
        )
        binding.rbAdult.isChecked = movieItem.adult!!

        binding.tvOverview.text = String.format(getString(R.string.text_movie_overview),movieItem.overview)

        binding.tvPopularity.text = String.format(getString(R.string.text_movie_popularity),movieItem.popularity)

        binding.tvReleaseDate.text =String.format(getString(R.string.text_movie_release_date),
            movieItem.release_date?.let { formatDateServer2DateGrid(it) })
        binding.tvVoteCount.text = movieItem.vote_average?.times(10)?.toInt().toString().plus("*")
        binding.pbVotePercent.progress = movieItem.vote_average?.times(10)?.toInt()!!
    }
}