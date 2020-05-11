package com.wosika.kale.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wosika.kale.intent.IIntent
import com.wosika.kale.view.IView
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*


abstract class BaseActivity<VM : BaseViewModel<VS, I>, VS : IViewState, I : IIntent> :
    IView<VM, VS, I>,
    AppCompatActivity(), CoroutineScope by MainScope() {

    protected inline fun <reified VM : BaseViewModel<VS, I>> createViewModel(): VM {
        return ViewModelProvider(
            this
            // , ViewModelProvider.AndroidViewModelFactory(this.application)
        )[VM::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //渲染数据
        viewModel.viewStateObservable().observe(this, Observer { viewState ->
            launch(Dispatchers.Main) {
                render(viewState)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        //结束协程
        cancel()
    }
}