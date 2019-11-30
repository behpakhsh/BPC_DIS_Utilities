package bpc.dis.utilities.DrawableStringGenerator;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;

public class DrawableStringGenerator {

    public static SpannableString getDrawableString(int direction, String string, Drawable drawable) {
        SpannableString spannableString;
        if (direction == View.LAYOUT_DIRECTION_LTR) {
            spannableString = new SpannableString(string + " @ ");
        } else {
            spannableString = new SpannableString(" @ " + string);
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(span, spannableString.toString().indexOf("@"), spannableString.toString().indexOf("@") + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString getDrawableString(String startString, String endString, Drawable drawable) {
        SpannableString spannableString;
        spannableString = new SpannableString(startString + " @ " + endString);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(span, spannableString.toString().indexOf("@"), spannableString.toString().indexOf("@") + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}