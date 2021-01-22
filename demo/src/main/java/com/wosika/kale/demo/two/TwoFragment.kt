package com.wosika.kale.demo.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.wosika.kale.base.BaseFragment
import com.wosika.kale.demo.R
import com.wosika.kale.demo.databinding.ActivityQrBinding
import com.wosika.kale.demo.databinding.FragmentTwoBinding
import com.wosika.kale.viewmodel.createViewModel





class TwoFragment : BaseFragment<TwoViewState, TwoIntent>() {


    override val viewModel: TwoViewModel by lazy { createViewModel<TwoViewModel>() }

    lateinit var bind:FragmentTwoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind= FragmentTwoBinding.inflate(layoutInflater,container,false)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
        viewModel.intent(TwoIntent.InitIntent)

    }

    private fun binds() {

        bind.btnRetry.setOnClickListener {
            viewModel.intent(TwoIntent.InitIntent)
        }

        bind.srlRefresh.setOnRefreshListener {
            viewModel.intent(TwoIntent.InitIntent)
        }
    }

    override fun render(viewState: TwoViewState) {
        bind.srlRefresh.isRefreshing = viewState.isRefresh
        if (!viewState.data.isNullOrBlank()) {
            bind.tvContent.text = viewState.data
            bind.tvContent.visibility = View.VISIBLE
        } else {
            bind.tvContent.visibility = View.GONE
        }

        if (viewState.error != null) {
            Snackbar.make(bind.root, "出现错误", Snackbar.LENGTH_SHORT).show()
            bind.btnRetry.visibility = View.VISIBLE
        } else {
            bind.btnRetry.visibility = View.GONE
        }
    }


}