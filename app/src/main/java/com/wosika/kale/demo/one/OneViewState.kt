package com.wosika.kale.demo.one

import com.wosika.kale.viewstate.IViewState

data class OneViewState(val isRefresh: Boolean, val data: String?, val error: Exception?) : IViewState