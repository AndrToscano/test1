package com.toscano.test1.core

import com.toscano.test1.data.network.entities.jikan.top.Data
import com.toscano.test1.data.network.entities.jikan.anime.FullInfoAnime
import com.toscano.test1.logic.entities.FullInfoAnimeLG

    fun FullInfoAnime.getFullInfoAnimeLG() = FullInfoAnimeLG (

        this.data.mal_id,
        this.data.title_english,
        this.data.images.jpg.small_image_url,
        this.data.images.jpg.large_image_url,
        this.data.synopsis
        )

    fun Data.getFullInfoAnimeLG() = FullInfoAnimeLG (

        this.mal_id,
        this.title_english,
        this.images.jpg.small_image_url,
        this.images.jpg.large_image_url,
        this.synopsis
    )