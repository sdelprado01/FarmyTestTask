package com.example.farmytesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment


class CollectionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_collection, container, false)

        //Naviagtion to Camera Fragment
        val btnReturn = root.findViewById<View>(R.id.imageButtonReturnCollection)
         btnReturn.setOnClickListener { NavHostFragment.findNavController(this).popBackStack() }

        return root
    }

}