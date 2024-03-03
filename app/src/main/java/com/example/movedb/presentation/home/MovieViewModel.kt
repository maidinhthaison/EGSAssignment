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

    fun getListMovies(include_adult: Boolean, include_video: Boolean,
                      language: String, page : Int, sort_by: String) {
        viewModelScope.launch {
            getMovieUseCase(include_adult = include_adult, include_video = include_video,
                language = language, page = page, sort_by = sort_by
            ).collectAsState(_uiGetMovieModel)
        }
    }
}