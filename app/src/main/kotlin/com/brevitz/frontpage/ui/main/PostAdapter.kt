package com.brevitz.frontpage.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brevitz.frontpage.R
import com.brevitz.frontpage.network.models.RedditPostData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PostAdapter(val postList: ArrayList<RedditPostData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.main_card_layout, parent, false
        )
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList[position]

        holder.apply {
            title.text = item.title
            author.text = itemView.context.getString(R.string.posted_by, item.author)
            subreddit.text = item.subredditPrefix
            preview.text = "${item.preview}"
            upvotes.text = "${item.upVotes}"

            val imageWidth = image.resources.getDimension(R.dimen.image_width)

            Glide.with(image.context)
                .load(item.thumbnail)
                .apply(RequestOptions()
                        .override(image.resources.getDimension(R.dimen.image_width).toInt(), image.resources.getDimension(R.dimen.image_height).toInt())
                        .skipMemoryCache(true)
                )
                .into(image)
        }
    }

    override fun getItemCount() = postList.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.postImage)
        val title = itemView.findViewById<TextView>(R.id.postTitle)
        val author = itemView.findViewById<TextView>(R.id.postAuthor)
        val subreddit = itemView.findViewById<TextView>(R.id.postSubReddit)
        val preview = itemView.findViewById<TextView>(R.id.postPreview)
        val upvotes = itemView.findViewById<TextView>(R.id.postUpvotes)
    }
}