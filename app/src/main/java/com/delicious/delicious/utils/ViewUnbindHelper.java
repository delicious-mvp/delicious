package com.delicious.delicious.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * Created by User on 2015-03-12.
 */
public class ViewUnbindHelper {
    /**
     * Removes the reference to the activity from every view in a view hierarchy
     * (listeners, images etc.). This method should be called in the onDestroy() method
     * of each activity.
     * This code may stinks, but better than worse - suspiciously, Android framework
     * does not free resources immediately which are consumed by Views and this leads to
     * OutOfMemoryError sometimes although there are no user mistakes.
     *
     * @param view View to free from memory
     * @see http://code.google.com/p/android/issues/detail?id=8488
     */
    public static void unbindReferences(View view) {
        try {
            if (view != null) {
                unbindViewReferences(view);
                if (view instanceof ViewGroup) {
                    unbindViewGroupReferences((ViewGroup)view);
                }
            }
        } catch (Exception ignore) {
            /* whatever exception is thrown just ignore it because a crash is
             * always worse than this method not doing what it's supposed to do
             */
        }
    }

    /**
     * Removes the reference to the activity from every view in a view hierarchy
     * (listeners, images etc.). This method should be called in the onDestroy() method
     * of each activity.
     * This code may stinks, but better than worse - suspiciously, Android framework
     * does not free resources immediately which are consumed by Views and this leads to
     * OutOfMemoryError sometimes although there are no user mistakes.
     *
     * @param view View to free from memory
     * @see http://code.google.com/p/android/issues/detail?id=8488
     */
    public static void unbindReferences(Activity activity, int viewID) {
        try {
            View view = activity.findViewById(viewID);
            if (view != null) {
                unbindViewReferences(view);
                if (view instanceof ViewGroup) {
                    unbindViewGroupReferences((ViewGroup)view);
                }
            }
        } catch (Exception ignore) {
            /* whatever exception is thrown just ignore it because a crash is
             * always worse than this method not doing what it's supposed to do.
             */
        }
    }

    private static void unbindViewGroupReferences(ViewGroup viewGroup) {
        int nrOfChildren = viewGroup.getChildCount();
        for (int i = 0; i < nrOfChildren; i++) {
            View view = viewGroup.getChildAt(i);
            unbindViewReferences(view);
            if (view instanceof ViewGroup) {
                unbindViewGroupReferences((ViewGroup)view);
            }
        }

        try {
            viewGroup.removeAllViews();
        } catch (Exception ignore) {
            // AdapterViews, ListViews and potentially other ViewGroups don't support the removeAllViews operation
        }
    }

    @SuppressWarnings("deprecation")
    private static void unbindViewReferences(View view) {
        // Set everything to null (API Level 8)
        try {
            view.setOnClickListener(null);
        } catch (Exception ignore) {}

        try {
            view.setOnCreateContextMenuListener(null);
        } catch (Exception ignore) {}

        try {
            view.setOnFocusChangeListener(null);
        } catch (Exception ignore) {}

        try {
            view.setOnKeyListener(null);
        } catch (Exception ignore) {}

        try {
            view.setOnLongClickListener(null);
        } catch (Exception ignore) {}

        try {
            view.setOnClickListener(null);
        } catch (Exception ignore) {}

        try {
            view.setTouchDelegate(null);
        } catch (Exception ignore) {}

        Drawable d = view.getBackground();
        if (d != null) {
            try {
                d.setCallback(null);
            } catch (Exception ignore) {}
        }

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView)view;
            d = imageView.getDrawable();
            if (d != null) {
                d.setCallback(null);
            }

            /*if (d instanceof BitmapDrawable) {
                Bitmap bm = ((BitmapDrawable)d).getBitmap();
                if(bm != null && !bm.isRecycled())
                    bm.recycle();
            }*/

            imageView.setImageDrawable(null);
        } else if (view instanceof WebView) {
            ((WebView)view).destroyDrawingCache();
            ((WebView)view).destroy();
        }

        try {
            view.setBackgroundDrawable(null);
        } catch (Exception ignore) {}

        try {
            view.setAnimation(null);
        } catch (Exception ignore) {}

        try {
            view.setContentDescription(null);
        } catch (Exception ignore) {}

        try {
            view.setTag(null);
        } catch (Exception ignore) {}
    }
}
