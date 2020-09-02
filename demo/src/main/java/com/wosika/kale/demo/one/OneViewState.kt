package com.wosika.kale.demo.one

import com.wosika.kale.viewstate.IViewState

data class OneViewState(val isRefresh: Boolean = false, val data: String? = null, val error: Exception? =null) : IViewState