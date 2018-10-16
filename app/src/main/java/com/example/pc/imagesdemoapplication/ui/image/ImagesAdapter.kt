package com.example.pc.imagesdemoapplication.ui.image

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pc.imagesdemoapplication.R
import com.example.pc.imagesdemoapplication.database.images.Image
import com.example.pc.imagesdemoapplication.utilities.Helper

/**
 * Created by Hasan.Awada on 10/15/2018.
 */
class ImagesAdapter(private val mContext: Context, private val images: List<Image>) : BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val imageModel = images[position]
        val mInflater = mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (view == null) {
            view = mInflater.inflate(R.layout.custom_image_view, null)
        }

        Helper.loadImage(mContext, view?.findViewById(R.id.ivImage), imageModel.link)

        return view!!
    }
}