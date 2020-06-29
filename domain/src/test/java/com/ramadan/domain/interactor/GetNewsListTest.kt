package com.ramadan.domain.interactor


import com.ramadan.domain.model.News
import com.ramadan.domain.repository.NewsRepository
import com.ramadan.test_utils.RxSchedulerRule
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class GetNewsListTest {

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!
    lateinit var getNewsList: GetNewsList

    @Mock
    lateinit var newsRepo: NewsRepository

    @Rule
    @JvmField
    var testSchedulerRule: RxSchedulerRule =
        RxSchedulerRule()

    val testSubscriber = TestSubscriber<List<News>>()

    @Before
    fun setup() {
        getNewsList = GetNewsList(newsRepo)
    }

    @Test
    fun `getNews is ready for test`() {
        assertNotNull(getNewsList)
    }

    @Test
    fun `should get news when getNews called`() {
        //given
        val newsList = listOf<News>(
            News(
                "The 5 players who could be the next Messi or Ronaldo",
                "https://image-service.onefootball.com/crop/face?h=810&amp;image=https%3A%2F%2Fwp-images.onefootball.com%2Fwp-content%2Fuploads%2Fsites%2F10%2F2019%2F08%2FFIFA-Ballon-dOr-Gala-2014-1566312341-1024x683.jpg&amp;q=25&amp;w=1080",
                "Onefootball",
                "https://images.onefootball.com/blogs_logos/circle_onefootball.png",
                "https://onefootball.com/en/news/the-5-players-who-could-be-the-next-messi-or-ronaldo-en-26880141?variable=20190822"
            ),
            News(
                "How will Juventus line up this season?",
                "https://oneftbl-cms.imgix.net/https%3A%2F%2Fwp-images.onefootball.com%2Fwp-content%2Fuploads%2Fsites%2F10%2F2019%2F08%2FFBL-ICC-2019-MADRID-JUVENTUS-1566054314-1024x768.jpg?crop=faces&fit=crop&h=810&q=25&w=1080&s=eecf0b15917c1c4af6bc16ecb63ccc1e",
                "Onefootball",
                "https://images.onefootball.com/blogs_logos/circle_onefootball.png",
                "https://onefootball.com/en/news/how-will-juventus-line-up-this-season-en-26881454?variable=20190822"
            ),
            News(
                "Impact of VAR on the Premier League so far",
                "https://image-service.onefootball.com/crop/face?h=810&amp;image=https%3A%2F%2Fwww.elartedf.com%2Fwp-content%2Fuploads%2F2019%2F08%2FManchester-City-v-Tottenham-Hotspur-Premier-League-1566473944.jpg&amp;q=25&amp;w=1080",
                "El Arte Del Futbol",
                "https://filebucket.onefootball.com/2019/8/1564679170544-EADF_90.png",
                "https://onefootball.com/en/news/impact-of-var-on-the-premier-league-so-far-en-26880789?variable=20190822"
            )
        )
        given(newsRepo.getNews()).willReturn(Flowable.just(newsList))
        //when
        getNewsList.execute(Unit).subscribe(testSubscriber)
        //then
        testSubscriber.assertNoErrors().assertComplete()
        assertEquals("news list should have three news items",testSubscriber.values()[0].size,3)
    }
}