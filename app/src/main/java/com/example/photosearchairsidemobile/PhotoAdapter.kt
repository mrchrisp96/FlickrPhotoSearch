package com.example.photosearchairsidemobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

// RecyclerView adapter

class PhotoAdapter(var photoList: ArrayList<Photo>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var name: TextView? = null
        var image: ImageView? = null

        init {
            this.image = row?.findViewById<ImageView>(R.id.imageView)
            this.name = row?.findViewById(R.id.photoTitle)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if(convertView == null) {
            val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.photo_row, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var photoObj: Photo = photoList.get(position)
        viewHolder.name?.text = photoObj.photoTitle

        return view
    }

    override fun getItem(position: Int): Any {
        return photoList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        // max size for the GridView
        return photoList.size
    }
}

