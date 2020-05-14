package com.wosika.kale.demo.paging

import com.wosika.kale.demo.entity.FeedItem
import com.wosika.kale.viewstate.IViewState

data class PagingViewState(val data: List<FeedItem>) : IViewState