package com.onefootball.ui.features.News.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.App
import com.onefootball.R
import com.onefootball.di.component.DaggerAppComponent
import com.onefootball.di.component.DaggerNewsActivityComponent
import com.onefootball.di.module.GetNewsListModule
import com.onefootball.di.module.NewsActivityModule
import com.onefootball.di.module.NewsRepositoryModule
import com.onefootball.di.vmfactory.ViewModelFactory
import com.onefootball.state.ViewState
import com.onefootball.ui.features.News.model.News
import com.onefootball.ui.features.News.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_my_news.*
import javax.inject.Inject

class MyNewsActivity : AppCompatActivity(), OnClickListener{

    @Inject
    lateinit var newsAdapter: NewsAdapter
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_news)
        initDI()
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        newsViewModel.uiState.value = ViewState.Loading
        setupNewsRecyclerview()
        observeNewsList()
    }

    private fun initDI() {
        val appComponent = DaggerAppComponent.create()
        val newsActivityComponent = DaggerNewsActivityComponent.builder()
            .appComponent(appComponent)
            .newsActivityModule(NewsActivityModule(application as App))
            .newsRepositoryModule(NewsRepositoryModule(application as App))
            .getNewsListModule(GetNewsListModule())
            .build()
        newsActivityComponent.inject(this)
    }

    private fun observeNewsList() {
        newsViewModel.liveUIState.observeForever {
            when (it) {
                is ViewState.Success<*> -> {
                    val newsList = (it.item as ArrayList<News>)
                    handleUISuccess()
                    newsAdapter.setNewsItems(newsList)
                }
                is ViewState.Error -> {
                    handleUIError()
                }

                is ViewState.Loading -> {
                    handleUILoading()
                }
            }
        }
    }

    private fun handleUISuccess() {
        progressBar.visibility = View.GONE
        newsRecyclerView.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    private fun handleUIError() {
        progressBar.visibility = View.GONE
        newsRecyclerView.visibility = View.GONE
        errorText.visibility = View.VISIBLE
    }

    private fun handleUILoading() {
        progressBar.visibility = View.VISIBLE
        newsRecyclerView.visibility = View.GONE
        errorText.visibility = View.GONE
    }

    private fun setupNewsRecyclerview() {
        with(newsRecyclerView) {
            adapter = newsAdapter
            layoutManager = linearLayoutManager
        }
        newsAdapter.setClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.getNewsList()
    }

    override fun onClick(position: Int, view: View) {
       startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsAdapter.newsItems[position].newsLink)))
    }
}

