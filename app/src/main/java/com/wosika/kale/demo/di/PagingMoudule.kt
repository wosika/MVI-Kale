package com.wosika.kale.demo.di

import com.wosika.kale.demo.paging.PagingActivity
import com.wosika.kale.demo.paging.PagingAdapter
import com.wosika.kale.demo.paging.PagingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val pagingModule = module {
    single { PagingAdapter() } bind PagingActivity::class
    viewModel { PagingViewModel() }
}