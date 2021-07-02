package com.wosika.kale.demo.weather

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.databinding.ActivityOneBinding
import com.wosika.kale.viewmodel.createViewModel


class WeatherActivity : BaseActivity<WeatherViewState, WeatherIntent>() {

    val bind by lazy { ActivityOneBinding.inflate(layoutInflater) }


    override val viewModel: WeatherViewModel by lazy { createViewModel<WeatherViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bind.root)

        binds()
        viewModel.intent(WeatherIntent.InitIntent)

    }

    private fun binds() {
        bind.btnRetry.setOnClickListener {
            viewModel.intent(WeatherIntent.InitIntent)
        }

        bind.srlRefresh.setOnRefreshListener {
            viewModel.intent(WeatherIntent.InitIntent)
        }
    }


    override fun render(viewState: WeatherViewState) {
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

