package com.example.movedb.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movedb.R
import com.example.movedb.base.BaseFragment
import com.example.movedb.data.api.model.response.MovieItem
import com.example.movedb.databinding.FragmentHomeBinding
import com.example.movedb.presentation.home.adapter.ListMovieUIEvent
import com.example.movedb.presentation.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>() {
    override fun initBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
    private val movieViewModel by viewModels<MovieViewModel>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.btnToDetail.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentMovieDetail)
        }*/

        movieAdapter =  MovieAdapter(context = requireContext())
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }

        with(movieViewModel) {
            getListMovies()
        }

        movieViewModel.uiGetMovieModel.collectWhenStarted {
            binding.loadingProgress.isVisible = it.isLoading
            if (it.data != null) {
                movieAdapter.submitList(it.data.results)
            }
        }


        movieAdapter.onClicked = {
            when (it) {
                is ListMovieUIEvent.OnItemClicked -> {
                    gotoDetailScreen(it.movieItem)
                }
            }
        }

    }

    private fun gotoDetailScreen(movieItem: MovieItem) {
        val bundle = Bundle().apply {
            this.putSerializable(MOVIE_ITEM_MODEL, movieItem)
        }
        findNavController().navigate(R.id.action_homeFragment_to_fragmentMovieDetail, bundle)
    }

    companion object{
        const val MOVIE_ITEM_MODEL = "MovieItemModel"
    }
}