package com.onefootball.ui.features.News.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.R
import com.onefootball.ui.features.News.model.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    val newsItems = ArrayList<News>()
    private lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newsItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsItems[position]
        holder.itemView.titleView.text = news.title
        holder.itemView.newsView.load(url = news.imageURL)
        holder.itemView.resourceImage.load(url = news.resourceURL)
        holder.itemView.resourceName.text = news.resourceName
        holder.itemView.setOnClickListener { listener.onClick(position, it) }
    }

    fun setNewsItems(newListOfNewsItems: List<News>) {
        newsItems.clear()
        newsItems.addAll(newListOfNewsItems)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}