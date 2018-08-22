package com.exc.louis.itunesapp.util;

import android.graphics.Bitmap;

public class EbCoverImage {
    private String message;
    private Bitmap bitmap;
    public EbCoverImage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
