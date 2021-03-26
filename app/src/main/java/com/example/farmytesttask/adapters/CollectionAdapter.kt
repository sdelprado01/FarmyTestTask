package com.example.farmytesttask.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.farmytesttask.fragments.MainFragmentDirections
import com.example.farmytesttask.R
import javax.security.auth.callback.Callback

class CollectionAdapter(private val imagesList: List<Uri>):RecyclerView.Adapter<CollectionAdapter.CollectionHolder>(){

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

    //Bind holder actions
    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        holder.imageView.setImageURI(imagesList[position])
        holder.cardView.setOnClickListener {
            //Add navigation to imageView with position argument
            val action = MainFragmentDirections.actionMainFragmentToImageFragment(position)
            Navigation.findNavController(holder.imageView).navigate(action)
        }
    }

    //Get list size
    override fun getItemCount(): Int {
        return imagesList.size
    }

    class CollectionHolder(view: View):RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageViewPicture)
        val cardView = view.findViewById<View>(R.id.cardViewImage)!!
    }




}
