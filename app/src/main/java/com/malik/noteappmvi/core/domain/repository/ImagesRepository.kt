package com.malik.noteappmvi.core.domain.repository

import com.malik.noteappmvi.core.domain.model.Images

interface ImagesRepository {

    suspend fun searchImages(query: String): Images?

}