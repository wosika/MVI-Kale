package com.wosika.kale.demo.one

import com.wosika.kale.intent.IIntent

sealed class OneIntent : IIntent {
    object InitIntent : OneIntent()

}