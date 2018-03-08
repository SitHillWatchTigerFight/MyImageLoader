package com.fran.test_imageloader.loder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by Fran~ on 2018/3/3 0003.
 */

public class ImageResizer {

    private static final String TAG = "ImageResizer";

    public ImageResizer() {
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int
            reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    public Bitmap decodeSampleFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }


    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0)
            return 1;

        final int width = options.outWidth;
        final int height = options.outHeight;

        Log.d(TAG, "origin, w=" + width + " h=" + height);
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >=
                    reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.d(TAG, "inSampleSize"+inSampleSize);
        return inSampleSize;
    }
}
