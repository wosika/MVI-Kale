package com.wosika.kale.demo

import android.os.Bundle

import com.wosika.kale.base.BaseActivity


class MainActivity : BaseActivity<MainViewModel, MainViewState, MainIntent>() {

    override val viewModel: MainViewModel by lazy { createViewModel<MainViewModel>() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.intent(MainIntent.InitIntent)
    }


    override fun render(viewState: MainViewState) {

    }


}
