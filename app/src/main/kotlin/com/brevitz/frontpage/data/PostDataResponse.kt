package com.brevitz.frontpage.data

import com.brevitz.frontpage.network.models.RedditPostData

sealed class PostDataResponse {
    data class Success(val data: ArrayList<RedditPostData>, val after: String) : PostDataResponse()
    data class Error(val throwable: Throwable) : PostDataResponse()
}