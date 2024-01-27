package com.toscano.test1.data.network.entities.jikan.top

data class TopAnimes(
    val `data`: List<Data> = listOf(),
    val pagination: Pagination? = null
)