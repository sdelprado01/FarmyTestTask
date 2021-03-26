package com.example.farmytesttask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.farmytesttask.R
import com.example.farmytesttask.viewmodel.ViewModel


class ImageFragment : Fragment() {

    //Take viewmodel
    private val viewmodel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_image, container, false)

        //Get image position and set the image to the imageView
        val image:ImageView = root.findViewById(R.id.imageViewImage)
        val imagePosition = requireArguments().getInt("imagePosition")
        val imageList = viewmodel.returnImageList()
        image.setImageURI(imageList[imagePosition])
        image.setBackgroundColor(resources.getColor(R.color.black))

        //Return button
        val btnReturn = root.findViewById<View>(R.id.fab_return)
        btnReturn.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        //Delete image -> Confirm delete dialog (image position as argument)
        val btnDeleteImage = root.findViewById<View>(R.id.fab_delete)
        btnDeleteImage.setOnClickListener {
            val action = ImageFragmentDirections.actionImageFragmentToConfirmDeleteDialog(imagePosition)
            Navigation.findNavController(root).navigate(action)
        }

        return root
    }

}