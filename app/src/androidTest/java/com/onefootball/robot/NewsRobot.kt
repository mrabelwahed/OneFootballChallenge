package com.onefootball.robot

import android.content.Intent
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.onefootball.R


fun news(func: NewsRobot.() -> Unit) = NewsRobot().apply { func() }

class NewsRobot : BaseRobot() {
    fun clickNewsItem(position: Int) = clickListItem(R.id.newsRecyclerView, position)
    fun checkNewsListDisplayed() = matchDisplay(R.id.newsRecyclerView)
    fun scrollTo(position: Int) = scrollTo(R.id.newsRecyclerView,position)
}