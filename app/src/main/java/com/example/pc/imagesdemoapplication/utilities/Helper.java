package com.example.pc.imagesdemoapplication.utilities;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.pc.imagesdemoapplication.R;
import com.example.pc.imagesdemoapplication.callbacks.OnRequestDoneListener;

import org.jetbrains.annotations.Nullable;

public class Helper {

    public static void loadImage(final Context context, final ImageView imageView, String imageURL) {
        try {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.images)
                    .error(R.drawable.images)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(500, 1000)
                    .priority(Priority.HIGH);

            GlideApp.with(context)
                    .load(imageURL)
                    .apply(options)
                    .into(imageView);

        } catch (Exception e) {
            Log.e("", "loadImage: ", e);
        }
    }


    public static void loadImageIntoLayout(Context context, final ViewGroup layout, String imageURL) {
        try {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.images)
                    .error(R.drawable.images)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .override(500, 1000)
                    .priority(Priority.HIGH);

            GlideApp.with(context)
                    .load(imageURL)
                    .apply(options)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                layout.setBackground(resource);
                            }
                        }
                    });

        } catch (Exception e) {
            Log.e("", "loadImage: ", e);
        }
    }

}
