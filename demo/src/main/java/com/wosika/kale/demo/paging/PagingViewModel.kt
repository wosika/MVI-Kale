package com.wosika.kale.demo.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.wosika.kale.base.BaseViewModel
import com.wosika.kale.demo.entity.FeedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.experimental.property.inject
import org.koin.ext.scope
import timber.log.Timber


class PagingViewModel :
    BaseViewModel<PagingViewState, PagingIntent>() {


    val pageLiveData: LiveData<PagedList<FeedItem>> by scope.inject(parameters = {
        parametersOf(
            MyDataSourceFactory()
        )
    })


    override fun processIntent(intent: PagingIntent) {

    }


    private suspend fun loadData(startPosition: Int, count: Int): List<FeedItem> {
        Timber.d("加载数据" + startPosition)
        delay(2000)
        val list: MutableList<FeedItem> = ArrayList()
        for (i in 0 until count) {
            val data = FeedItem((startPosition + i).toString())
            list.add(data)
        }
        Timber.d("加载数据完毕" + startPosition)
        return list
    }

    inner class MyDataSourceFactory : DataSource.Factory<Int, FeedItem>() {
        override fun create(): DataSource<Int, FeedItem> {
            return MyDataSource()
        }
    }


    inner class MyDataSource : PositionalDataSource<FeedItem>() {
        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<FeedItem>) {
            viewModelScope.launch(Dispatchers.Main) {
                val loadData = loadData(params.startPosition, 10)
                callback.onResult(loadData);
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<FeedItem>
        ) {
            viewModelScope.launch(Dispatchers.Main) {
                val loadData = loadData(0, 10)
                callback.onResult(loadData, 0, 10);
                viewStateLiveData.postValue(PagingViewState(false))
            }
        }
    }
}