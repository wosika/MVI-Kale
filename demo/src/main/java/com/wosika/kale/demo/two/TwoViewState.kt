package com.wosika.kale.demo.two

import com.wosika.kale.viewstate.IViewState

sealed class TwoViewState:IViewState

data class  InitViewState(val isRefresh: Boolean = false, val data: String? = null, val error: Exception? =null) : TwoViewState()

