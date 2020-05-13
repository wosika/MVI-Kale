package com.wosika.kale.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wosika.kale.intent.IIntent
import com.wosika.kale.view.IView
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*


abstract class BaseActivity<VS : IViewState, I : IIntent> :
    IView<VS, I>,
    AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //渲染数据
        viewModel?.viewStateObservable()?.observe(this, Observer { viewState ->
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