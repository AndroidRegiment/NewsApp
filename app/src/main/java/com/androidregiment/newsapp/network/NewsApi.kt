package com.androidregiment.newsapp.network

import com.androidregiment.newsapp.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "87ab6bc8a9074f5f90645f91872f1f7e"

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadline(
        @Query("category")
        category : String,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("language")
        language: String = "en",
        @Query("country")
        country: String = "",
    ) : Response<News>

}