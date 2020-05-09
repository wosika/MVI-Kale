package com.wosika.kale.view


import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState

interface IView<VM : IViewModel<I, VS>, VS : IViewState, I : IIntent> {

    val viewModel: VM

    fun render(viewState: VS)
}
