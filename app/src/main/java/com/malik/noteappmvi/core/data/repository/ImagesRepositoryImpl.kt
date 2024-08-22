package com.malik.noteappmvi.core.data.repository

import com.malik.noteappmvi.core.data.mapper.toImages
import com.malik.noteappmvi.core.data.remote.api.ImagesApi
import com.malik.noteappmvi.core.domain.model.Images
import com.malik.noteappmvi.core.domain.repository.ImagesRepository
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
) : ImagesRepository {

    override suspend fun searchImages(
        query: String
    ): Images? {
        return imagesApi.searchImages(query)?.toImages()
    }

}