package com.wosika.kale.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewstate.IViewState

//他要知道他处理的意图是什么
interface IViewModel<VS : IViewState, I : IIntent> {
    //将intent 传输给Viewmodel去处理，处理完之后 回调
    fun intent(intent: I)

    fun viewStateObservable(): LiveData<VS>


}

//viewmodel的创建
inline fun <reified VM : ViewModel> ViewModelStoreOwner.createViewModel(): VM {
    return ViewModelProvider(this)[VM::class.java]
}
