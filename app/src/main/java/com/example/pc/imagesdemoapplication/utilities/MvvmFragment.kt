package com.example.pc.imagesdemoapplication.utilities

import android.support.v4.app.Fragment
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Hasan.Awada on 10/16/2018.
 */

//MVVM Fragment
open class MvvmFragment : Fragment() {

    private val subscriptions = CompositeDisposable()

    fun subscribe(disposable: Disposable?): Disposable? {
        subscriptions.add(disposable!!)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

    fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}