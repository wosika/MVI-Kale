package com.wosika.kale.demo.di

import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.wosika.kale.demo.entity.FeedItem
import com.wosika.kale.demo.paging.PagingActivity
import com.wosika.kale.demo.paging.PagingAdapter
import com.wosika.kale.demo.paging.PagingViewModel
import com.wosika.kale.demo.paging.PagingViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val pagingModule = module {
    single { PagingAdapter() } bind PagingActivity::class


    scope<PagingViewModel> {
        factory { (f: PagingViewModel.MyDataSourceFactory) ->
            LivePagedListBuilder(
                f,
                PagedList.Config.Builder()
                    .setPageSize(10) //配置分页加载的数量
                    .setEnablePlaceholders(false) //配置是否启动PlaceHolders
                    .setInitialLoadSizeHint(10) //初始化加载的数量
                    .build()
            ).build()
        }
    }



    viewModel { PagingViewModel() }

}


