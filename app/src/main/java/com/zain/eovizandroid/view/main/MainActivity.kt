package com.zain.eovizandroid.view.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.zain.eovizandroid.R
import com.zain.eovizandroid.helper.BaseActivity
import com.zain.eovizandroid.model.Post
import com.zain.eovizandroid.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeData()

        setupEvent()
    }

    private fun setupEvent() {
        buttonChangePage.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeData() {
        mainViewModel.postResult.observe(this, Observer {
            when(it) {
                is Boolean -> {
                    progressCircular.visibility = if (it) View.VISIBLE else View.GONE
                    layoutData.visibility = if (it) View.GONE else View.VISIBLE
                }

                is String -> {
                    textError.text = it
                }

                is Post -> {
                    textTitle.text = it.title ?: ""
                    textBody.text = it.body ?: ""
                }
            }
        })
    }
}
