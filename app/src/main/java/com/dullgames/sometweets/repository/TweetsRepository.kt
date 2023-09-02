package com.dullgames.sometweets.repository

import com.dullgames.sometweets.api.ApiService
import com.dullgames.sometweets.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsRepository @Inject constructor(private val tweetsApi: ApiService) {

    private val _categoriesFlow = MutableStateFlow<List<String>>(emptyList())
    val categoriesFlow: StateFlow<List<String>>
        get() = _categoriesFlow

    private val _tweetsListFlow = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweetsListFlow : StateFlow<List<TweetListItem>>
        get() = _tweetsListFlow

    suspend fun getTweetsCategories() {
        val response = tweetsApi.getCategories()
        if(response.isSuccessful && response.body() != null){
            _categoriesFlow.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null){
            _tweetsListFlow.emit(response.body()!!)
        }
    }


}