package com.wosika.kale.demo.paging

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import kotlinx.android.synthetic.main.activity_paging.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PagingActivity : BaseActivity<PagingViewState, PagingIntent>(R.layout.activity_paging) {

    override val viewModel: PagingViewModel by viewModel<PagingViewModel>()

    val adapter: PagingAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        srlRefresh.isRefreshing = true
        binds()

    }

    private fun binds() {

        srlRefresh.setOnRefreshListener {
            adapter.currentList?.dataSource?.invalidate()
        }
        viewModel.pageLiveData.observe(this, Observer {
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
        srlRefresh.isRefreshing = viewState.isRefreshing
    }

}

