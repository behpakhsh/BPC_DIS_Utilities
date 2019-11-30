package bpc.dis.utilities.DoubleBackHelper;

import android.os.Handler;

public class DoubleBackHelper {

    private static boolean doubleBackPressedOnce = false;

    public static void backButtonPress(DoubleBackHelperListener doubleBackHelperListener, int doubleBackDelayTime) {
        if (doubleBackPressedOnce) {
            if (doubleBackHelperListener != null) {
                doubleBackHelperListener.exit();
            }
            return;
        }
        doubleBackPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressedOnce = false;
            }
        }, doubleBackDelayTime);
        if (doubleBackHelperListener != null) {
            doubleBackHelperListener.showMessage();
        }
    }

}