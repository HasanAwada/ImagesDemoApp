package com.example.pc.imagesdemoapplication.ui.image


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

    override fun onStart() {
        super.onStart()
        getImages()
    }

    private fun getImages() {
        subscribe(imageViewModel?.getImages()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            displayImages(it as MutableList<Image>)
        }))
    }

    private fun displayImages(images: MutableList<Image>) {
        context.let {
            if (images.size > 0) {
                var allImages: MutableList<Image> = images

                if (gvImages.adapter != null) {
                    allImages = (gvImages.adapter as ImagesAdapter).getItems()
                    allImages.addAll(images)
                    allImages = allImages.distinctBy {
                        it.Id
                    } as MutableList<Image>
                }

                gvImages.adapter = ImagesAdapter(context!!, allImages)
                gvImages.isNestedScrollingEnabled = true
                gvImages.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    openImageViewer(allImages[position].Id)
                }
            }
        }
    }

    private fun openImageViewer(imageId: Int) {
        val pop = ImageViewerFragment.newInstance(imageId)
        pop.show(activity?.supportFragmentManager, "image_viewer")
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
