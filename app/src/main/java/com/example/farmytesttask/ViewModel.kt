package com.example.farmytesttask

import android.app.Application
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.File

class ViewModel(application: Application) : AndroidViewModel(application) {

    //Create imageList
    var imageList: List<Uri> = ArrayList()


    //read Images from gallery
    fun readGallery(dir: File):List<Uri>{
        if(!dir.exists()) return ArrayList()
        imageList = dir.listFiles().map { f -> Uri.fromFile(f) }
        return imageList
    }

    //Return the list with images
    fun returnImageList(): List<Uri>{
        return imageList
    }

}