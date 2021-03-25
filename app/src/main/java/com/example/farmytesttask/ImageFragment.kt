package com.example.farmytesttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels


class ImageFragment : Fragment() {

    val viewmodel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_live_camera, container, false)

        //Get image position and set the image to the imageView

        val imagePosition = arguments?.getInt("imagePosition")
        val imageView:ImageView = root.findViewById<View>(R.id.imageViewBigImage) as ImageView
        val imageList = viewmodel.returnImageList()
        imageView.setImageURI(imageList[imagePosition!!])

        return root
    }
}