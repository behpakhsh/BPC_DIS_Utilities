package bpc.dis.utilities.KeyboardHelper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardHelper {

    public static void showKeyboard(Context context, EditText target) {
        if (context == null || target == null) {
            return;
        }
        getInputMethodManager(context).showSoftInput(target, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void showKeyboard(Activity activity, EditText target) {
        if (activity == null || target == null) {
            return;
        }
        getInputMethodManager(activity).showSoftInput(target, InputMethodManager.SHOW_IMPLICIT);
    }

    private static void hideKeyboard(Context context, View target) {
        if (context == null || target == null) {
            return;
        }
        getInputMethodManager(context).hideSoftInputFromWindow(target.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getWindow().getDecorView();
        hideKeyboard(activity, view);
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}