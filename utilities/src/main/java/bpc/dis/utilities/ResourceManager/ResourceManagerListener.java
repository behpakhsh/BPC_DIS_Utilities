package bpc.dis.utilities.ResourceManager;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

public interface ResourceManagerListener {

    float getDimension(@DimenRes int resId);

    int getColor(@ColorRes int resId);

    Drawable getDrawable(@DrawableRes int resId);

}