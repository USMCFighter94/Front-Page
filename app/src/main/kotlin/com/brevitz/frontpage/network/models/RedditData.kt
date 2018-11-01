package com.brevitz.frontpage.network.models

data class RedditData(val children: List<RedditChildren> = arrayListOf(),
                      val before: String = "",
                      val after: String = "")