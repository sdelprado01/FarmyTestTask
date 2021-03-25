package com.example.farmytesttask

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class MainFragment : Fragment() {

    lateinit var recyclerViewCollection:RecyclerView
    val viewmodel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //Naviagtion to Camera Fragment
        val btnTakePictureNav = root.findViewById<View>(R.id.imageButtonOpenCamera)
        btnTakePictureNav.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_liveCameraFragment) }


        initRecyclerView(root)

        return root
    }


    fun initRecyclerView(view: View){
        recyclerViewCollection = view.findViewById(R.id.recyclerViewCollection)
        recyclerViewCollection.layoutManager = GridLayoutManager(context, 4)

        val dir = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Farmy")
        val adapter = CollectionAdapter(viewmodel.readGallery(dir))
        recyclerViewCollection.adapter = adapter


    }
}