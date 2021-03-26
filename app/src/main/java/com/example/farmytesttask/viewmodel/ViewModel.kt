package com.example.farmytesttask.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.lang.Exception

class ViewModel(application: Application) : AndroidViewModel(application) {

    //Create imageList
    private var imageList: List<Uri> = ArrayList()

    //Read images from directory and return the list sorted
    fun readGallery(dir: File):List<Uri>{
        if(!dir.exists()) return ArrayList()
        imageList = dir.listFiles().map { f -> Uri.fromFile(f) }
        return imageList.sorted()
    }

    //Return the list with images
    fun returnImageList(): List<Uri>{
        return imageList.sorted()
    }

    fun deleteImage(file: File): Boolean{
            return file.delete()
    }
}