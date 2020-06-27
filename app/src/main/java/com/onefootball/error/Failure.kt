package com.onefootball.error

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnExpectedError : Failure()
}