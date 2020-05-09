package com.wosika.kale.demo

import com.wosika.kale.intent.IIntent

sealed class MainIntent : IIntent {
    object InitIntent : MainIntent()
}