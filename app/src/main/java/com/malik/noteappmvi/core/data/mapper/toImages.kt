package com.malik.noteappmvi.core.data.mapper

import com.malik.noteappmvi.core.data.remote.dto.ImageListDto
import com.malik.noteappmvi.core.domain.model.Images

fun ImageListDto.toImages(): Images {
    return Images(
        images = hits?.map { imageDto ->
            imageDto.previewURL ?: ""
        } ?: emptyList()
    )
}