package com.wosika.kale.demo

import com.wosika.kale.viewstate.IViewState

data class MainViewState(val isRefresh: Boolean, val data: String?, val error: Exception?) : IViewState