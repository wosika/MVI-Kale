package com.wosika.kale.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wosika.kale.intent.IIntent
import com.wosika.kale.view.IView
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*

abstract class BaseFragment<VS : IViewState, I : IIntent> : Fragment(),
    IView< VS, I>, CoroutineScope by MainScope() {

  /*  protected inline fun <reified VM : BaseViewModel<VS, I>> createViewModel(): VM {
        return ViewModelProvider(
            this
            // , ViewModelProvider.AndroidViewModelFactory(this.application)
        )[VM::class.java]
    }*/

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //渲染数据
        viewModel?.viewStateObservable()?.observe(this, Observer { viewState ->
            launch(Dispatchers.Main) {
                render(viewState)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}