package com.corelib.base;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.corelib.util.BitmapUtil;

/**
 * Created by GodXj on 2015/12/14.
 */
public class DefaultImageListener implements ImageLoader.ImageListener {

    private int numKB = 1024 * 3;//如果大于3M就压缩
    private ImageView imageView;
    private int defaultResId;

    public DefaultImageListener(ImageView imageView, int defaultResId) {
        this.imageView = imageView;
        this.defaultResId = defaultResId;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        Bitmap bp = response.getBitmap();
        if (imageView == null) {
            return;
        }
        if (bp != null) {
            Bitmap comBp = BitmapUtil.compressImage(bp, numKB);
            if (comBp != null) {
                imageView.setImageBitmap(comBp);
            } else {
                imageView.setImageResource(defaultResId);
            }
        } else {
            imageView.setImageResource(defaultResId);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        imageView.setImageResource(defaultResId);
    }
}
