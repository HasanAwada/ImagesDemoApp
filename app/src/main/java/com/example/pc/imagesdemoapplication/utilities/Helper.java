package com.example.pc.imagesdemoapplication.utilities;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.pc.imagesdemoapplication.R;
import com.example.pc.imagesdemoapplication.callbacks.OnRequestDoneListener;

import org.jetbrains.annotations.Nullable;

public class Helper {

    public static void loadImage(final Context context, final ImageView imageView, String imageURL, final OnRequestDoneListener onRequestDoneListener) {
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
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // log exception
                            Log.e("TAG", "Error loading image", e);
                            return false; // important to return false so the error placeholder can be placed
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            onRequestDoneListener.run();
                            return false;
                        }
                    })
                    .apply(options)
                    .into(imageView);
        } catch (Exception e) {
            Log.e("", "loadImage: ", e);
        }
    }

}
