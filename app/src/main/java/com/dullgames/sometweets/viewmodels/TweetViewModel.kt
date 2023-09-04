package com.dullgames.sometweets.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dullgames.sometweets.models.TweetListItem
import com.dullgames.sometweets.repository.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(private val tweetsRepository: TweetsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val tweets : StateFlow<List<TweetListItem>>
        get() = tweetsRepository.tweetsListFlow

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "android"
            tweetsRepository.getTweets(category)
        }
    }
}