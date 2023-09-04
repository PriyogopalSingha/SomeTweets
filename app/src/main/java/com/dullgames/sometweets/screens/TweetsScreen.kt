package com.dullgames.sometweets.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dullgames.sometweets.models.TweetListItem
import com.dullgames.sometweets.viewmodels.TweetViewModel

@Composable
fun TweetsScreen() {
    val tweetViewModel: TweetViewModel = hiltViewModel()
    val tweet: State<List<TweetListItem>> = tweetViewModel.tweets.collectAsState()
    if(tweet.value.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else{
        LazyColumn {
            items(tweet.value) {
                TweetItemScreen(tweet = it.text)
            }
        }
    }

}


@Composable
fun TweetItemScreen(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        border = BorderStroke(
            1.dp, Color(0xffeeeeee))
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}