package com.wosika.kale.demo.paging

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.paging.PagedList

import androidx.recyclerview.widget.LinearLayoutManager
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.demo.entity.FeedItem

import com.wosika.kale.viewmodel.IViewModel
import kotlinx.android.synthetic.main.activity_paging.*

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PagingActivity : BaseActivity<PagingViewState, PagingIntent>(R.layout.activity_paging) {
    /**
     * 当重写viewModel时，请使用 by lazy 函数进行viewModel的初始化，
     * 否则在activity或者fragment生命周期尚未初始化时就初始化viewModel会出现崩溃
     */
    override val viewModel: PagingViewModel by viewModel<PagingViewModel>()

    val adapter: PagingAdapter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        binds()

    }

    private fun binds() {

        viewModel.intent(PagingIntent.InitIntent)

        srlRefresh.setOnRefreshListener {
/*
            viewModel.getRefreshLiveData().observe(this,
                Observer {
                    adapter.submitList(it)
                    srlRefresh.isRefreshing = false
                })
            adapter.submitList(null)*/
            //adapter.currentList?.clear()
            Timber.d("去刷新")
            adapter.currentList?.dataSource?.addInvalidatedCallback {
                Timber.d("刷新了")
                srlRefresh.isRefreshing = false
            }
            adapter.currentList?.dataSource?.invalidate()
        }
        viewModel.getLiveData().observe(this, Observer {
            Timber.d("加载数据的回调")
            adapter.submitList(it)
        })

    }

    private fun initRecyclerView() {

        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    /**
     * viewModel需要处理Intent之后，会将viewState回调到这个方法上
     */
    override fun render(viewState: PagingViewState) {

    }

}

