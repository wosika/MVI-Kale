package com.wosika.kale.demo.paging

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewmodel.createViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PagingActivity : BaseActivity<PagingViewState, PagingIntent>(R.layout.activity_paging) {
    /**
     * 当重写viewModel时，请使用 by lazy 函数进行viewModel的初始化，
     * 否则在activity或者fragment生命周期尚未初始化时就初始化viewModel会出现崩溃
     */
    override val viewModel: IViewModel<PagingViewState, PagingIntent> by viewModel<PagingViewModel>()

    val adapter: PagingAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        binds()
    }

    private fun binds() {

        viewModel.intent(PagingIntent.InitIntent)
        srlRefresh.setOnRefreshListener {
            viewModel.intent(PagingIntent.InitIntent)
        }
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

