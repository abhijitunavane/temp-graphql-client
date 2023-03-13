package com.example.demographql

sealed class Resource<out R>(
    var status: Status,
    val value: R?
) {

    class Success<out T>(data: T) : Resource<T>(Status.SUCCESS, data)
    class Error<T>: Resource<T>(Status.ERROR, null)
    class Loading<T> : Resource<T>(Status.LOADING, null)
}
