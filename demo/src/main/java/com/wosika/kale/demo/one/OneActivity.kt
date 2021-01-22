package com.wosika.kale.demo.one

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.demo.databinding.ActivityOneBinding
import com.wosika.kale.viewmodel.createViewModel





class OneActivity : BaseActivity<OneViewState, OneIntent>() {

    val bind by lazy { ActivityOneBinding.inflate(layoutInflater) }


    override val viewModel: OneViewModel by lazy { createViewModel<OneViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(bind.root)

        binds()
        viewModel.intent(OneIntent.InitIntent)

    }

    private fun binds() {
        bind.btnRetry.setOnClickListener {
            viewModel.intent(OneIntent.InitIntent)
        }

        bind.srlRefresh.setOnRefreshListener {
            viewModel.intent(OneIntent.InitIntent)
        }
    }


    override fun render(viewState: OneViewState) {
        bind.srlRefresh.isRefreshing = viewState.isRefresh
        if (!viewState.data.isNullOrBlank()) {
            bind.tvContent.text = viewState.data
            bind.tvContent.visibility = View.VISIBLE
        } else {
            bind.tvContent.visibility = View.GONE
        }

        if (viewState.error != null) {
            Snackbar.make(bind.srlRefresh, "出现错误", Snackbar.LENGTH_SHORT).show()
            bind.btnRetry.visibility = View.VISIBLE
        } else {
            bind.btnRetry.visibility = View.GONE
        }
    }


}

