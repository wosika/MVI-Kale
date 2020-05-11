package com.wosika.kale.demo.two

import com.wosika.kale.viewstate.IViewState

data class TwoViewState(val isRefresh: Boolean, val data: String?, val error: Exception?) : IViewState