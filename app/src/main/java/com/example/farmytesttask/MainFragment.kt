package com.example.farmytesttask

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment


class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //Naviagtion to Camera Fragment
        val btnTakePictureNav = root.findViewById<View>(R.id.buttonTakePicture)
        btnTakePictureNav.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_cameraFragment) }

        //Navigation to collection Fragment
        val btnOpenCollectionNav = root.findViewById<View>(R.id.buttonOpenCollection)
        btnOpenCollectionNav.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_collectionFragment) }


        return root
    }

}