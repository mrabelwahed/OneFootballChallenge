package com.onefootball.ui.features.News.mapper

import com.ramadan.domain.model.News

object NewsMapper {
    private fun transform(news: News): com.onefootball.ui.features.News.model.News {
        return com.onefootball.ui.features.News.model.News(
            news.title,
            news.imageURL,
            news.resourceName,
            news.resourceURL,
            news.newsLink
        )
    }

    fun transform(newsCollection: Collection<News>): List<com.onefootball.ui.features.News.model.News> {
        val newsList = mutableListOf<com.onefootball.ui.features.News.model.News>()
        for (review in newsCollection) {
            val model = transform(review)
            if (model != null) {
                newsList.add(model)
            }
        }
        return newsList
    }
}