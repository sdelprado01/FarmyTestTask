package com.example.farmytesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Sleep to let the user see the splash screen
        Thread.sleep(1000)

        setTheme(R.style.Theme_FarmyTestTask)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}