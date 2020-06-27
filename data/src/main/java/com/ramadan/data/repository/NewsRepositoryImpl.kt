package com.ramadan.data.repository

import android.app.Application
import com.ramadan.domain.model.News
import com.ramadan.domain.repository.NewsRepository
import io.reactivex.Flowable
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class NewsRepositoryImpl (val app:Application) : NewsRepository{

    override fun getNews(): Flowable<List<News>> {
      var inputStream = app.assets.open("news.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val items = parseJsonString(buffer.toString(Charset.defaultCharset()))
        return Flowable.just(items)
    }

    private fun parseJsonString(jsonString: String): List<News> {
        val mainObject = JSONObject(jsonString)
        val newsItems = mutableListOf<News>()
        val newsArray = mainObject.getJSONArray("news")
        newsArray.forEach { newsObject ->
            val title = newsObject.getString("title")
            val imageURL = newsObject.getString("image_url")
            val resourceName = newsObject.getString("resource_name")
            val resourceURL = newsObject.getString("resource_url")
            val newsLink = newsObject.getString("news_link")
            newsItems.add(News(title = title, imageURL = imageURL, resourceName = resourceName, resourceURL = resourceURL, newsLink = newsLink))
        }
        return newsItems
    }

    private fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
        for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
    }

}