package com.zain.eovizandroid.model

sealed class ResponseResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : ResponseResult<T>()
    data class Error(val exception: String) : ResponseResult<Nothing>()
}