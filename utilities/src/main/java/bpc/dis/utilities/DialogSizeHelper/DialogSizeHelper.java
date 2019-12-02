package bpc.dis.utilities.DialogSizeHelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DialogSizeHelper {

    /**
     * @deprecated dont use activity
     */
    @Deprecated
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

    public static void setDialogSize(Dialog dialog, Context context, double width, double height) {
        if (dialog != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(metrics);
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

}