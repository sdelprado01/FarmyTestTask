package com.example.farmytesttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(val imagesList:List<ImageView>):RecyclerView.Adapter<CollectionAdapter.CollectionHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CollectionHolder(layoutInflater.inflate(R.layout.item_list_collection, parent, false))
    }


    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        //
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class CollectionHolder(val view: View):RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageViewPicture)
        val cardView:CardView = view.findViewById(R.id.cardViewID)
    }


}
