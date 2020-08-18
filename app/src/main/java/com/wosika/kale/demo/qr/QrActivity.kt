package com.wosika.kale.demo.qr


import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.R
import com.wosika.kale.demo.utils.ZXingUtils
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState
import kotlinx.android.synthetic.main.activity_qr.*
import timber.log.Timber

class QrActivity :
    BaseActivity<IViewState, IIntent>(R.layout.activity_qr) {

    override val viewModel: IViewModel<IViewState, IIntent>? = null

    val qrContent = "这是二维码的文本消息"


    override fun onStart() {
        super.onStart()
        ivQrCode.postDelayed({
            Timber.d("ivQrCode.width${ivQrCode.width}")
            Timber.d("ivQrCode.height${ivQrCode.height}")
            val qrBitmap = ZXingUtils.createQRImage(qrContent, ivQrCode.width, ivQrCode.height)
            qrBitmap?.let {
                ivQrCode.setImageBitmap(it)
            }
        }, 0)
    }


    override fun render(viewState: IViewState) {

    }


}