package com.wosika.kale.demo.one

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import kotlinx.android.synthetic.main.activity_one.*



class OneActivity : BaseActivity<OneViewModel, OneViewState, OneIntent>() {


    override val viewModel: OneViewModel by lazy { createViewModel<OneViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        binds()
        viewModel.intent(OneIntent.InitIntent)
    }

    private fun binds() {
        btnRetry.setOnClickListener {
            viewModel.intent(OneIntent.InitIntent)
        }

        srlRefresh.setOnRefreshListener {
            viewModel.intent(OneIntent.InitIntent)
        }
    }


    override fun render(viewState: OneViewState) {
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
