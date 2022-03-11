package com.wosika.kale.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wosika.kale.intent.IIntent
import com.wosika.kale.view.IView
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

abstract class BaseFragment<VS : IViewState, I : IIntent>(@LayoutRes private val layoutId: Int? = null) :
    Fragment(), IView<VS, I>, CoroutineScope by MainScope() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (layoutId != null) {
            return inflater.inflate(layoutId, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //渲染数据
        launch(Dispatchers.Main) {
            viewModel?.viewStateObservable()?.collect { viewState ->
                render(viewState)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}