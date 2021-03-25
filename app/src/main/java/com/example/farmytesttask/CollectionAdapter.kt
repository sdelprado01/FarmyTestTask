package com.example.farmytesttask

import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs

class CollectionAdapter(val imagesList: List<Uri>):RecyclerView.Adapter<CollectionAdapter.CollectionHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CollectionHolder(
            layoutInflater.inflate(
                R.layout.item_list_collection,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        holder.imageView.setImageURI(imagesList[position])
        holder.imageView.setOnClickListener{
            //Add navigation to imageView with position argument
        }
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class CollectionHolder(val view: View):RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageViewPicture)
    }


}
