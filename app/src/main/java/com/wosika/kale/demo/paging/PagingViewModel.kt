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
import timber.log.Timber


class PagingViewModel : BaseViewModel<PagingViewState, PagingIntent>() {


    private var liveData: LiveData<PagedList<FeedItem>> = getRefreshLiveData()

    fun getRefreshLiveData(): LiveData<PagedList<FeedItem>> {
        liveData = LivePagedListBuilder(
            MyDataSourceFactory(), PagedList.Config.Builder()
                .setPageSize(10) //配置分页加载的数量
                .setEnablePlaceholders(false) //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10) //初始化加载的数量

                .build()
        ).build()
        return liveData
    }


    fun getLiveData(): LiveData<PagedList<FeedItem>> {
        return liveData
    }


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
                callback.onResult(loadData(params.startPosition, 10))
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<FeedItem>
        ) {
            viewModelScope.launch(Dispatchers.Main) {
                callback.onResult(loadData(0, 10), 0, 10);
            }
        }


    }
}