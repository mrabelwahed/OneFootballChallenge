package com.ramadan.domain.repository

import com.ramadan.domain.model.News
import io.reactivex.Flowable

interface NewsRepository{
    fun getNews(): Flowable<List<News>>
}