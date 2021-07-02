package com.wosika.kale.demo.qr


import android.os.Bundle
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.databinding.ActivityQrBinding
import com.wosika.kale.demo.utils.ZXingUtils
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState

import timber.log.Timber

class QrActivity : BaseActivity<IViewState, IIntent>() {

    val bind by lazy {  ActivityQrBinding.inflate(layoutInflater) }

    override val viewModel: IViewModel<IViewState, IIntent>? = null

    val qrContent = "这是二维码的文本消息"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
    }
    override fun onStart() {
        super.onStart()
        bind.ivQrCode.postDelayed({
            Timber.d("ivQrCode.width${bind.ivQrCode.width}")
            Timber.d("ivQrCode.height${bind.ivQrCode.height}")
            val qrBitmap = ZXingUtils.createQRImage(qrContent, bind.ivQrCode.width, bind.ivQrCode.height)
            qrBitmap?.let {
                bind.ivQrCode.setImageBitmap(it)
            }
        }, 0)
    }


    override fun render(viewState: IViewState) {

    }


}