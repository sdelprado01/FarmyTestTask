package com.example.farmytesttask

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //Sleep to let the user see the splash screen
        Thread.sleep(1000)

        setTheme(R.style.Theme_FarmyTestTask)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    /*
    //Create options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> Log.d("::Itema", "Seleccion de buscar")
            R.id.action_settings -> Log.d("::Itema", "Seleccion de settings")

        }
        return super.onOptionsItemSelected(item)
    }

     */

}