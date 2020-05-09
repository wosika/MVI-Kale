package com.wosika.kale.demo

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

import com.wosika.kale.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel, MainViewState, MainIntent>() {

    override val viewModel: MainViewModel by lazy { createViewModel<MainViewModel>() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binds()
        viewModel.intent(MainIntent.InitIntent)
    }

    private fun binds() {
        btnRetry.setOnClickListener {
            viewModel.intent(MainIntent.InitIntent)
        }

        srlRefresh.setOnRefreshListener {
            viewModel.intent(MainIntent.InitIntent)
        }
    }


    override fun render(viewState: MainViewState) {
        srlRefresh.isRefreshing = viewState.isRefresh
        if (!viewState.data.isNullOrBlank()) {
            tvContent.text = viewState.data
            tvContent.visibility = View.VISIBLE
        } else {
            tvContent.visibility = View.GONE
        }

        if (viewState.error != null) {
            Snackbar.make(srlRefresh, "出现错误", Snackbar.LENGTH_SHORT).show()
            btnRetry.visibility = View.VISIBLE
        } else {
            btnRetry.visibility = View.GONE
        }
    }


}
