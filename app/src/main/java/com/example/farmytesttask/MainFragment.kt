package com.example.farmytesttask

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainFragment : Fragment() {

    lateinit var recyclerViewCollection:RecyclerView
    lateinit var imageList: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //Naviagtion to Camera Fragment
        val btnTakePictureNav = root.findViewById<View>(R.id.imageButtonOpenCamera)
        btnTakePictureNav.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_liveCameraFragment) }


        //initRecyclerView(root)


        return root
    }

    /*
    fun readGallery():List<ImageView>{

    }

     */

    fun initRecyclerView(view: View){
        recyclerViewCollection = view.findViewById(R.id.recyclerViewCollection)
        recyclerViewCollection.layoutManager = LinearLayoutManager(context)

        val adapter = CollectionAdapter(imageList)
        recyclerViewCollection.adapter = adapter


    }
}