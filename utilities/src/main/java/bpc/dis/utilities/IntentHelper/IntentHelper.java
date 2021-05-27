package bpc.dis.utilities.IntentHelper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

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

    public static void runApkForInstallSafe(Activity activity, String applicationId, File file, int reqCode) {
        Uri fileUri = Uri.fromFile(file);
        if (Build.VERSION.SDK_INT >= 24) {
            if (file != null) {
                fileUri = FileProvider.getUriForFile(activity, applicationId, file);
            }
        }
        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE, fileUri);
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
        intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(intent, reqCode);
    }

    public static void openDataUsageSetting(Activity activity) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void openWifiSetting(Activity activity) {
        try {
            activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void openAppPermissionSetting(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openAppPermissionSetting(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openCaller(Activity activity, String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void openUrl(Activity activity, String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Activity activity, String message) {
        sendMessage(activity,
                "لطفا پیام رسان خود را انتخاب کنید",
                "",
                message
        );
    }

    public static void sendMessage(Activity activity, String title, String message) {
        sendMessage(activity,
                "لطفا پیام رسان خود را انتخاب کنید",
                title,
                message
        );
    }

    public static void sendMessage(Activity activity, String choicerTitle, String title, String message) {
        try {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
            intent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            activity.startActivity(Intent.createChooser(intent, choicerTitle));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

}