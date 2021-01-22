package com.wosika.kale.demo.paging

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.demo.databinding.ActivityPagingBinding

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PagingActivity : BaseActivity<PagingViewState, PagingIntent>() {
    val bind by lazy {  ActivityPagingBinding.inflate(layoutInflater) }

    override val viewModel: PagingViewModel by viewModel()

    val adapter: PagingAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bind.root)

        initRecyclerView()
        bind.srlRefresh.isRefreshing = true
        binds()
    }

    private fun binds() {

        bind.srlRefresh.setOnRefreshListener {
            adapter.currentList?.dataSource?.invalidate()
        }

        viewModel.pageLiveData.observe(this, {
            Timber.d("加载数据的回调")
            adapter.submitList(it)
        })

    }

    private fun initRecyclerView() {
        bind.rvList.adapter = adapter
        bind.rvList.layoutManager = LinearLayoutManager(this)
    }

    /**
     * viewModel需要处理Intent之后，会将viewState回调到这个方法上
     */
    override fun render(viewState: PagingViewState) {
        bind.srlRefresh.isRefreshing = viewState.isRefreshing
    }

}

