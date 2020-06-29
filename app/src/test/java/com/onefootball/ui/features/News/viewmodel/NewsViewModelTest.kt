package com.onefootball.ui.features.News.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.onefootball.error.Failure
import com.onefootball.state.ViewState
import com.ramadan.domain.interactor.GetNewsList
import com.ramadan.domain.model.News
import com.ramadan.domain.repository.NewsRepository
import com.ramadan.test_utils.RxSchedulerRule
import com.ramadan.test_utils.mock
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.net.UnknownHostException

class NewsViewModelTest{
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!

    private lateinit var newsViewModel: NewsViewModel

    lateinit var getNewsList: GetNewsList

    @Mock
    var observer: Observer<ViewState> = mock()

    @Rule
    @JvmField
    var testSchedulerRule: RxSchedulerRule =
        RxSchedulerRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        getNewsList = GetNewsList(newsRepository)
        newsViewModel = NewsViewModel(getNewsList)
        newsViewModel.liveUIState.observeForever(observer)
    }

    @Test
    fun `view model is ready for test`() {
        assertNotNull(newsViewModel)
    }

    @Test
    fun `should news list loaded successfully`() {
        // Given
        given(newsRepository.getNews()).willReturn(Flowable.just(createNewsList()))
        // when
        newsRepository.getNews()
        // then
        verify(newsRepository).getNews()
        verifyNoMoreInteractions(newsRepository)
    }

    @Test
    fun `should return list of news`() {
        // given
        given(newsRepository.getNews()).willReturn(Flowable.just(createNewsList()))
        // when
        newsRepository.getNews()
        newsViewModel.uiState.value = ViewState.Success(Flowable.just(createNewsList()))
        // then
        val captor = ArgumentCaptor.forClass(ViewState::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(newsViewModel.uiState.value, value)
        }
    }

    @Test
    fun `no internet error case`() {
        // given
        given(newsRepository.getNews())
            .willReturn(Flowable.error(UnknownHostException()))
        // when
        newsRepository.getNews()
        newsViewModel.uiState.value = ViewState.Error(Failure.NetworkConnection)
        // then
        val captor = ArgumentCaptor.forClass(ViewState::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(value, ViewState.Error(Failure.NetworkConnection))
        }
    }



    private fun createNewsList(): List<News> {
        return listOf<News>(
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
    }

}