package bpc.dis.utilities.DialogSizeHelper;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;

public class DialogSizeHelper {

    public static void setDialogSize(Dialog dialog, Activity activity, double width, double height) {
        if (dialog != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            if (dialog.getWindow() != null) {
                if (width < 0 && height < 0) {
                    dialog.getWindow().setLayout((int) width, (int) height);
                } else if (width < 0) {
                    dialog.getWindow().setLayout((int) width, (int) (metrics.heightPixels * height));
                } else if (height < 0) {
                    dialog.getWindow().setLayout((int) (metrics.widthPixels * width), (int) height);
                } else {
                    dialog.getWindow().setLayout((int) (metrics.widthPixels * width), (int) (metrics.heightPixels * height));
                }
            }
        }
    }

}