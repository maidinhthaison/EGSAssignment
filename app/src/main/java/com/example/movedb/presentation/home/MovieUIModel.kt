package com.example.movedb.presentation.home

import com.example.movedb.base.BaseUIModel
import com.example.movedb.domain.model.MovieModel

data class MovieUIModel (
    override val data: MovieModel? = null,
    override val isLoading: Boolean = false,
    override val messageId: Int? = null
) : BaseUIModel() {
    override fun copyWith(data: Any?, isLoading: Boolean, messageId: Int?): BaseUIModel {
        return this.copy(
            data = (data ?: this.data) as MovieModel?,
            messageId = messageId ?: this.messageId,
            isLoading = isLoading
        )
    }
}