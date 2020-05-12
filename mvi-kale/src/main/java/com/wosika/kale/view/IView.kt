package com.wosika.kale.view


import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState

interface IView<VS : IViewState, I : IIntent> {


    /**
     * 当重写viewModel时，请使用 by lazy 函数进行viewModel的初始化，
     * 否则在activity或者fragment生命周期尚未初始化时就初始化viewModel会出现崩溃
     */
      val viewModel: IViewModel<VS,I>?


    /**
     * viewModel需要处理Intent之后，会将viewState回调到这个方法上
     */
    fun render(viewState: VS)
}
