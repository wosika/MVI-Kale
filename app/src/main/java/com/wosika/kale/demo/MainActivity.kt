package com.wosika.kale.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wosika.kale.demo.one.OneActivity
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
    }
}