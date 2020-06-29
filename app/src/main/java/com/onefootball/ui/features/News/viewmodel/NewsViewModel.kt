package com.onefootball.ui.features.News.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onefootball.error.Failure
import com.onefootball.state.ViewState
import com.onefootball.ui.features.News.mapper.NewsMapper
import com.ramadan.domain.interactor.GetNewsList
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val usecase: GetNewsList) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val uiState = MutableLiveData<ViewState>()
    val liveUIState: LiveData<ViewState>
        get() = uiState

    fun getNewsList() {
        compositeDisposable.add(usecase.execute(Unit).subscribe(
            { res -> uiState.value = ViewState.Success(NewsMapper.transform(res)) },
            { error -> uiState.value = setFailure(error) }
        ))
    }

    private fun setFailure(throwable: Throwable): ViewState {
        return when (throwable) {
            is UnknownHostException -> ViewState.Error(Failure.NetworkConnection)
            else -> ViewState.Error(Failure.UnExpectedError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}