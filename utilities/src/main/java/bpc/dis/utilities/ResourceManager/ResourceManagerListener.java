package bpc.dis.utilities.ResourceManager;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

public interface ResourceManagerListener {

    int getDimension(@DimenRes int id);

    int getColor(@ColorRes int id);

    Drawable getDrawable(@DrawableRes int resId);

}