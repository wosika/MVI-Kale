package com.wosika.kale.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wosika.kale.intent.IIntent
import com.wosika.kale.view.IView
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*


abstract class BaseActivity<VS : IViewState, I : IIntent>(@LayoutRes private val layoutId: Int? = null) :
    AppCompatActivity(),
    IView<VS, I>, CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onSetContentView()

        //渲染数据
        viewModel?.viewStateObservable()?.observe(this, Observer { viewState ->
            launch(Dispatchers.Main) {
                render(viewState)
            }
        })
    }

    //在oncreate的开头执行，可以在此初始化 view操作
    protected open fun onSetContentView() {
        if (layoutId != null) {
            setContentView(layoutId)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //结束协程
        cancel()
    }
}