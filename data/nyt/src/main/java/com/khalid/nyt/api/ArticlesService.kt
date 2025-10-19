package com.khalid.nyt.api

import com.khalid.nyt.dto.PopularArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesService {
    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getMostPopularArticles(@Path("period") period: Int): PopularArticlesResponse
}