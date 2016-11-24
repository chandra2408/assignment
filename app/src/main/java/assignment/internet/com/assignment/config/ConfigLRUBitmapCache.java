package assignment.internet.com.assignment.config;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class ConfigLRUBitmapCache extends LruCache<String, Bitmap> implements ImageCache {

    public ConfigLRUBitmapCache(int maxSize) {
        super(maxSize);
        //Log.e("VOLLEY_IMAGE_CACHE",Integer.toString(maxSize));
    }

    public ConfigLRUBitmapCache(Context ctx) {
        this(getDefaultLruCacheSize(ctx));
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        //Log.e("VOLLEY_IMAGE_CACHE", "Row : " + Integer.toString(value.getRowBytes()));
        //Log.e("VOLLEY_IMAGE_CACHE", "Height : " + Integer.toString(value.getHeight()));
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);

    }

    //FIXME - Revisit - Check this size
    public static int getDefaultLruCacheSize(Context ctx) {
        final DisplayMetrics displayMetrics = ctx.getResources().
                getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        // 4 bytes per pixel
        final int screenBytes = screenWidth * screenHeight * 4 * 3;
        return screenBytes;
    }

}

