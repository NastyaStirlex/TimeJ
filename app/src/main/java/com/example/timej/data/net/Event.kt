package com.example.timej.data.net

import androidx.annotation.StringRes

data class Event<T>(val status: Status, val data: T?, @StringRes val error: Int?) {
    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Event<T> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(@StringRes error: Int?): Event<T> {
            return Event(Status.ERROR, null, error)
        }

        fun <T> default(): Event<T> {
            return Event(Status.DEFAULT, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    DEFAULT
}

