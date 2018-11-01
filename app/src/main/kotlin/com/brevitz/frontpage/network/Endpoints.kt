package com.brevitz.frontpage.network

import com.brevitz.frontpage.network.models.RedditPosts
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {
    @GET(".json")
    fun getFrontPage(@Query("after") after: String): Single<RedditPosts>

    @GET("r/{subreddit}/.json")
    fun getSubreddit(@Path("subreddit") subReddit: String, @Query("after") after: String): Single<RedditPosts>
}