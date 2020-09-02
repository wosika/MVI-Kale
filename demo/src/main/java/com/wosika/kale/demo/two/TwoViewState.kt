package com.wosika.kale.demo.two

import com.wosika.kale.viewstate.IViewState

data class TwoViewState(val isRefresh: Boolean = false, val data: String? = null, val error: Exception? =null) : IViewState