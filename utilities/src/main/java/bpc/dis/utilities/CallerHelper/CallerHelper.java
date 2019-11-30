package bpc.dis.utilities.CallerHelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.content.ContextCompat;

public class CallerHelper {

    public static void openCaller(Activity activity, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(number));
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            activity.startActivity(intent);
        }
    }

}