package com.dullgames.sometweets.api
import com.dullgames.sometweets.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {

    @GET("/v3/b/64f15b73d972192679bced99?meta=false") // for dynamic header use header
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/64f15b73d972192679bced99?meta=false") // for static header use headers
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories():Response<List<String>>

}