package com.wosika.kale.demo.two

import com.wosika.kale.intent.IIntent

sealed class TwoIntent : IIntent {
    object InitIntent : TwoIntent()
}