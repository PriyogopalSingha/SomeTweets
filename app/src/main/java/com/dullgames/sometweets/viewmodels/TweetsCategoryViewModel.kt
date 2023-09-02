package com.dullgames.sometweets.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dullgames.sometweets.repository.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsCategoryViewModel @Inject constructor(private val tweetsRepository: TweetsRepository): ViewModel() {

    val categories : StateFlow<List<String>>
        get() = tweetsRepository.categoriesFlow

    init {
        viewModelScope.launch {
            tweetsRepository.getTweetsCategories()
        }
    }
}