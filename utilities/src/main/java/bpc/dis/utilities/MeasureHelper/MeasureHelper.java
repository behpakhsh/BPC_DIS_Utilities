package bpc.dis.utilities.MeasureHelper;

import android.content.Context;
import android.util.TypedValue;

public class MeasureHelper {

    public static float pixelToDip(Context context, float pixel) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, context.getResources().getDisplayMetrics());
    }

}