package com.zain.eovizandroid.view.detail

import android.os.Bundle
import com.zain.eovizandroid.R
import com.zain.eovizandroid.helper.BaseActivity
import com.zain.eovizandroid.model.Post
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        observeData()
    }

    private fun observeData() {
        mainViewModel.postResult.observe(this, androidx.lifecycle.Observer {
            when(it) {
                is Post -> {
                    textTitle.text = it.title ?: ""
                    textBody.text = it.body ?: ""
                    textId.text = "${it.id ?: 0}"
                }
            }
        })
    }
}
