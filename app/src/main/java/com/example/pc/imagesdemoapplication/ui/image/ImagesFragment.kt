package com.example.pc.imagesdemoapplication.ui.image


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pc.imagesdemoapplication.ImagesDemoApplication

import com.example.pc.imagesdemoapplication.R
import com.example.pc.imagesdemoapplication.database.images.Image
import com.example.pc.imagesdemoapplication.utilities.MvvmFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_images.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ImagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagesFragment : MvvmFragment() {

    private var imagesList: MutableList<Image>? = null
    private var imageViewModel: ImageViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageViewModelFactory = ImagesViewModelFactory(ImagesDemoApplication.injectImageRepository())
        imageViewModel = ViewModelProviders.of(this, imageViewModelFactory).get(ImageViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        getImages()
    }

    private fun getImages() {

        subscribe(imageViewModel?.getImages()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            imagesList = it as MutableList<Image>
            displayImages()
        }))

    }

    private fun displayImages() {
        context.let {
            gvImages.adapter = ImagesAdapter(context!!, imagesList as List<Image>)
            gvImages.isNestedScrollingEnabled = true
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ImagesFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): ImagesFragment {
            return ImagesFragment()
        }
    }
}// Required empty public constructor
