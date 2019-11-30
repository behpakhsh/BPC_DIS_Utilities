package bpc.dis.utilities.TextSizeHelper;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;

public class TextSizeHelper {

    public static void changeTextSizeWithDipUnit(Context context, TextView textView, float textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (textSize / context.getResources().getDisplayMetrics().density));
    }

}