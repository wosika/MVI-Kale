package com.wosika.kale.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wosika.kale.demo.one.OneActivity
import com.wosika.kale.demo.paging.PagingActivity
import com.wosika.kale.demo.two.EmptyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToActivity.setOnClickListener {
            startActivity(Intent(this, OneActivity::class.java))
        }

        btnToFragment.setOnClickListener {
            startActivity(Intent(this, EmptyActivity::class.java))
        }

        btnToPaging.setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }


        val macAddress = UniqueIdUtils.getUniqueId(this)

        //  val androidId = UniqueIdUtils.getSerialNumber()
        Log.d("tag", "androidid: $macAddress")
    }
}