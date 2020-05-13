package com.wosika.kale.demo.paging

import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewstate.IViewState

sealed class PagingIntent : IIntent {
    object InitIntent : PagingIntent()

    data class LoadMoreIntent(val page: Int) : PagingIntent()
}