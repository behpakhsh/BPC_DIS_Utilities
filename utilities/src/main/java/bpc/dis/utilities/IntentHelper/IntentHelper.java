package bpc.dis.utilities.IntentHelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

public class IntentHelper {

    public static void runApkForInstall(Activity activity, String applicationId, File file, int reqCode) {
        Uri fileUri = Uri.fromFile(file);
        if (Build.VERSION.SDK_INT >= 24) {
            if (file != null) {
                fileUri = FileProvider.getUriForFile(activity, applicationId, file);
            }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, fileUri);
        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
        intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(intent, reqCode);
    }

}