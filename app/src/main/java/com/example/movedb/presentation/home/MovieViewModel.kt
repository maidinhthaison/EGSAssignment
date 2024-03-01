package com.example.movedb.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.movedb.base.BaseViewModel
import com.example.movedb.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : BaseViewModel() {

    private val _uiGetMovieModel = MutableStateFlow(MovieUIModel())
    val uiGetMovieModel = _uiGetMovieModel.asStateFlow()

    fun getListMovies() {
        viewModelScope.launch {
            getMovieUseCase(version = "3", include_adult = true, include_video = true, language = "en-US",
                page = 1, sort_by = "popularity.desc"
            ).collectAsState(_uiGetMovieModel)
        }
    }
}