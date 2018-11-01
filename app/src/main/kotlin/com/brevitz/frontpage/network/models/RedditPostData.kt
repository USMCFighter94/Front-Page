package com.brevitz.frontpage.network.models

import com.squareup.moshi.Json

data class RedditPostData(val author: String = "",
                          val title: String = "",
                          @Json(name = "sub_reddit_name_prefixed") val subredditPrefix: String = "",
                          @Json(name = "num_comments") val numberOfComments: Int = 0,
                          val created: Long = 0L,
                          val thumbnail: String = "",
                          val url: String = "")