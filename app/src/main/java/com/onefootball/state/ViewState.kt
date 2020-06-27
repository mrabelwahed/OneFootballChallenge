package com.onefootball.state

import com.onefootball.error.Failure

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val failure: Failure?) : ViewState()
    data class Success<T>(val item: T) : ViewState()
}