package com.khalid.nyt.destinations

import kotlinx.serialization.Serializable

object RootDestination {
    @Serializable
    object ArticlesScreen

    @Serializable
    object ArticleDetailsScreen
}