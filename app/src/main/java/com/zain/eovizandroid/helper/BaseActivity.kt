package com.zain.eovizandroid.helper

import androidx.appcompat.app.AppCompatActivity
import com.zain.eovizandroid.view.main.MainViewModel
import org.koin.android.ext.android.inject

open class BaseActivity: AppCompatActivity() {
    val mainViewModel: MainViewModel by inject()
}