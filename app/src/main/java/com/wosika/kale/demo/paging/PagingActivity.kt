package com.wosika.kale.demo.paging

import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewmodel.createViewModel

class PagingActivity : BaseActivity<PagingViewState, PagingIntent>(R.layout.activity_paging) {
    /**
     * 当重写viewModel时，请使用 by lazy 函数进行viewModel的初始化，
     * 否则在activity或者fragment生命周期尚未初始化时就初始化viewModel会出现崩溃
     */
    override val viewModel: IViewModel<PagingViewState, PagingIntent> by lazy {
        createViewModel<PagingViewModel>()
    }

    /**
     * viewModel需要处理Intent之后，会将viewState回调到这个方法上
     */
    override fun render(viewState: PagingViewState) {

    }
}