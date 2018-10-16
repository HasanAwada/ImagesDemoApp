package com.example.pc.imagesdemoapplication.ui.image


import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pc.imagesdemoapplication.R

class ImagesActivity : AppCompatActivity() {

    private var imagesList: MutableList<Image>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayImages()
    }


    private fun displayImages() {

        var fragment = ImagesFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment, "").disallowAddToBackStack().commit()
    }


}
