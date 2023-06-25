package com.example.harsha_news_app

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class NewsAdapter(var articles: List<Article>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var isAscendingOrder = true

    fun updateSortingOrder(ascendingOrder: Boolean) {
        isAscendingOrder = ascendingOrder
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val dateTextView: TextView = view.findViewById(R.id.news_date)
        val description:TextView=view.findViewById(R.id.news_description)
        val imageView:ImageView=view.findViewById(R.id.news_image)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = if (isAscendingOrder) {
            articles[position]
        } else {
            articles[articles.size - position - 1]
        }

        //val article = articles[position]
        holder.titleTextView.text = article.title
        holder.dateTextView.text=article.publishedAt
        holder.description.text=article.description


        // Show the progress bar
        holder.progressBar.visibility = View.VISIBLE

        // Load the image using Glide
        Glide.with(holder.itemView)
            .load(article.urlToImage)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Hide the progress bar if image loading fails
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Hide the progress bar once the image is loaded
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(holder.imageView)
        //takes the user to the web page when the URL is clicked
        holder.itemView.setOnClickListener { onItemClick(article.url) }
        // the indeterminate tint color for the ProgressBar
        holder.progressBar.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.pink))


//        // Load the image using Glide
//        Glide.with(holder.itemView)
//            .load(article.urlToImage)
//            .into(holder.imageView)
//        holder.itemView.setOnClickListener { onItemClick(article.url) }
    }


    override fun getItemCount(): Int = articles.size
}

private fun Int.reversed() {
}

private fun <TranscodeType> RequestBuilder<TranscodeType>.into(imageView: TextView) {

}

