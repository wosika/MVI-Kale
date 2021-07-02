package com.wosika.kale.demo

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wosika.kale.demo.databinding.ActivityMainBinding
import com.wosika.kale.demo.one.OneActivity
import com.wosika.kale.demo.qr.QrActivity
import com.wosika.kale.demo.two.EmptyActivity
import com.wosika.kale.demo.utils.UniqueIdUtils

import java.lang.reflect.Method
import java.math.BigDecimal


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val bind = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bind.root)

        bind.btnToActivity.setOnClickListener {
           startActivity(Intent(this, OneActivity::class.java))
          /*  val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)*/
        }

        bind.btnToFragment.setOnClickListener {
            startActivity(Intent(this, EmptyActivity::class.java))
        }



        bind.btnToQr.setOnClickListener {
            startActivity(Intent(this, QrActivity::class.java))
        }


        val macAddress = UniqueIdUtils.getUniqueId(this)

        //  val androidId = UniqueIdUtils.getSerialNumber()
        Log.d("tag", "androidid: $macAddress")


        // Log.d("tag", "屏幕尺寸: ${getPingMuSize(this)}")
        Log.d("tag", "屏幕尺寸: ${getScreenInch(this)}")

        getScreenPX()
    }


    fun getScreenPX() {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = Math.round(dm.widthPixels * dm.density)

        val height = Math.round(dm.heightPixels * dm.density)

        Log.d("tag", "屏幕尺寸 宽: ${getScreenInch(this)}")
        Log.d("tag", "屏幕尺寸 高: ${getScreenInch(this)}")

        Toast.makeText(this, "屏幕尺寸：$mInch,屏幕宽$width，屏幕高$height", Toast.LENGTH_LONG).show()

    }


    private var mInch = 0.0

    /**
     * 获取屏幕尺寸
     * @param context
     * @return
     */
    fun getScreenInch(context: Activity): Double {
        if (mInch != 0.0) {
            return mInch
        }
        try {
            var realWidth = 0
            var realHeight = 0
            val display = context.windowManager.defaultDisplay
            val metrics = DisplayMetrics()
            display.getMetrics(metrics)
            if (Build.VERSION.SDK_INT >= 17) {
                val size = Point()
                display.getRealSize(size)
                realWidth = size.x
                realHeight = size.y
            } else if (Build.VERSION.SDK_INT < 17
                && Build.VERSION.SDK_INT >= 14
            ) {
                val mGetRawH: Method = Display::class.java.getMethod("getRawHeight")
                val mGetRawW: Method = Display::class.java.getMethod("getRawWidth")
                realWidth = mGetRawW.invoke(display) as Int
                realHeight = mGetRawH.invoke(display) as Int
            } else {
                realWidth = metrics.widthPixels
                realHeight = metrics.heightPixels
            }
            mInch = formatDouble(
                Math.sqrt(realWidth / metrics.xdpi * (realWidth / metrics.xdpi) + realHeight / metrics.ydpi * (realHeight / metrics.ydpi).toDouble()),
                1
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mInch
    }

    /**
     * Double类型保留指定位数的小数，返回double类型（四舍五入）
     * newScale 为指定的位数
     */
    private fun formatDouble(d: Double, newScale: Int): Double {
        val bd = BigDecimal(d)
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).toDouble()
    }


}