package com.onefootball.ui.features.News.view

import android.content.Intent
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import com.onefootball.robot.news
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyNewsActivityTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule(MyNewsActivity::class.java)

    @Test
    fun should_launch_MyNewsActivity() {
        news {
            checkNewsListDisplayed()
        }
    }

    @Test
    fun should_open_details_screen_when_click_news_item() {
        news {
            checkNewsListDisplayed()
            clickNewsItem(0)
            val receivedIntent: Intent = Iterables.getOnlyElement(Intents.getIntents())
            assertThat(receivedIntent).hasAction(Intent.ACTION_VIEW)
        }
    }

    @Test
    fun should_scroll_to_the_end_of_news_list(){
        news {
            checkNewsListDisplayed()
            val newsCount =  intentsTestRule.activity.newsAdapter.newsItems.size
            scrollTo(newsCount-1)
            clickNewsItem(newsCount-1)
            val receivedIntent: Intent = Iterables.getOnlyElement(Intents.getIntents())
            assertThat(receivedIntent).hasAction(Intent.ACTION_VIEW)
        }
    }

}