package com.ramadan.domain.interactor

import com.ramadan.domain.model.News
import com.ramadan.domain.repository.NewsRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetNewsList(private val newsRepo: NewsRepository) : Usecase<Unit, List<News>> {
    override fun execute(param: Unit): Flowable<List<News>> {
        return newsRepo.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}