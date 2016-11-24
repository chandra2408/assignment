package assignment.internet.com.assignment.config;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class ConfigVolley {

    private static ConfigVolley mInstance;
    private static Context mContext;

    public static final String TAG = ConfigVolley.class
            .getSimpleName();
    private static final int MY_SOCKET_TIMEOUT_MS = 5000;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized ConfigVolley getInstance(){
        if(mInstance == null){
            mInstance = new ConfigVolley();
        }
        return mInstance;
    }

    public static void init(Context context) {
        mContext = context;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new ConfigLRUBitmapCache(mContext));
        }
        return this.mImageLoader;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}


