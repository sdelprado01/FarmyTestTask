package com.example.farmytesttask.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.farmytesttask.R
import com.example.farmytesttask.viewmodel.ViewModel
import java.io.File


class ConfirmDeleteDialog : DialogFragment() {

    private val viewmodel: ViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.fragment_confirm_delete_dialog, null, false)

        //Get image position from arguments and locate the route
        val imagePosition = requireArguments().getInt("imagePositionDialog")
        val imageList = viewmodel.returnImageList()
        //Must take off string "file:" because it doesn't find route if so
        val imageLocation = File(imageList[imagePosition].toString().replace("file:",""))

        val btnConfirmDeleteImage = view.findViewById<View>(R.id.buttonConfirmDelete)
        val btnCancel = view.findViewById<View>(R.id.buttonCancelDelete)

        //Confirm delete button
        btnConfirmDeleteImage.setOnClickListener {
            if(viewmodel.deleteImage(imageLocation)) {
                Toast.makeText(context, "Image deleted", Toast.LENGTH_SHORT).show()
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_confirmDeleteDialog_to_mainFragment)

            }else{
                Toast.makeText(context, "Image not deleted", Toast.LENGTH_SHORT).show()
            }
        }

        //Cancel button
        btnCancel.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        return AlertDialog.Builder(activity).setView(view).create()
    }
}