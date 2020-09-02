package com.wosika.kale.demo.two

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.wosika.kale.base.BaseFragment
import com.wosika.kale.demo.R
import com.wosika.kale.viewmodel.createViewModel

import kotlinx.android.synthetic.main.activity_one.*



class TwoFragment : BaseFragment<TwoViewState, TwoIntent>(R.layout.fragment_two) {


    override val viewModel: TwoViewModel by lazy { createViewModel<TwoViewModel>() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
        viewModel.intent(TwoIntent.InitIntent)
    }

    private fun binds() {

        btnRetry.setOnClickListener {
            viewModel.intent(TwoIntent.InitIntent)
        }

        srlRefresh.setOnRefreshListener {
            viewModel.intent(TwoIntent.InitIntent)
        }
    }

    override fun render(viewState: TwoViewState) {
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