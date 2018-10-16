package com.example.pc.imagesdemoapplication.ui.image

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pc.imagesdemoapplication.ImagesDemoApplication

import com.example.pc.imagesdemoapplication.R
import com.example.pc.imagesdemoapplication.utilities.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_image_viewer.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImageViewerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageViewerFragment : DialogFragment() {

    private var imageViewModel: ImageViewModel? = null
    private var imageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageViewModelFactory = ImagesViewModelFactory(ImagesDemoApplication.injectImageRepository())
        imageViewModel = ViewModelProviders.of(this, imageViewModelFactory).get(ImageViewModel::class.java)

        arguments.let {
            imageId = arguments?.getInt(IMAGE_ID)

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_image_viewer
                , container, false)
    }

    override fun onStart() {
        super.onStart()
        imageViewModel.let {
            imageId.let {
                imageViewModel?.getImageById(imageId)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                    it.let {
                        Helper.loadImageIntoLayout(context, rlImage, it.link)
                    }
                })
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog.window
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    companion object {

        private val IMAGE_ID = "IMAGE_ID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ImagesFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(imageId: Int): ImageViewerFragment {
            var fragment = ImageViewerFragment()
            val args = Bundle()
            args.putInt(IMAGE_ID, imageId)
            fragment.arguments = args
            return fragment
        }
    }

}