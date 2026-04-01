package com.explore.newsapp.model

sealed class ResourceWrapper<T>(
    val data:T ?= null,
    val message: String? = null) {
    class Success<T>(data:T): ResourceWrapper<T>(data)
    class Error<T>(message: String?, data: T?= null): ResourceWrapper<T>(data, message)
    class Loading<T>: ResourceWrapper<T>()
}