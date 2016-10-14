package home.skwmium.skilltest.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

public class BindingUtils {
    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter("android:src")
    public static void bindImageResource(ImageView imageView, @DrawableRes int drawableRes) {
        if (drawableRes > 0)
            imageView.setImageResource(drawableRes);
    }
}
