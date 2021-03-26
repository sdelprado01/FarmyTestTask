package com.example.farmytesttask.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmytesttask.adapters.CollectionAdapter
import com.example.farmytesttask.R
import com.example.farmytesttask.viewmodel.ViewModel
import java.io.File


class MainFragment : Fragment() {

    lateinit var recyclerViewCollection:RecyclerView
    private val viewmodel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //Naviagtion to Camera Fragment
        val btnTakePictureNav = root.findViewById<View>(R.id.imageButtonOpenCamera)
        btnTakePictureNav.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_liveCameraFragment) }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Thread(Runnable {
            activity?.runOnUiThread {
                initRecyclerView(view)
            }
        }).start()
    }

    //Create recyclerview with images from directory
    private fun initRecyclerView(view: View){
        //Create recyclerView
        recyclerViewCollection = view.findViewById(R.id.recyclerViewCollection)
        recyclerViewCollection.layoutManager = GridLayoutManager(context, 3)

        val dir = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Farmy")
        val adapter = CollectionAdapter(viewmodel.readGallery(dir))
        recyclerViewCollection.adapter = adapter

    }
}