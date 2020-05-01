package com.zain.eovizandroid.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zain.eovizandroid.helper.ApiHelper
import com.zain.eovizandroid.model.ResponseResult

class MainViewModel : ViewModel() {

    val postResult = liveData {
        emit(true)
        val post = ApiHelper.getTodoRequest(1)
        emit(false)
        when (post) {
            is ResponseResult.Success -> { emit(post.data) }
            is ResponseResult.Error -> { emit(post.exception) }
        }
    }

}