package bpc.dis.utilities.DeviceHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

@SuppressLint("HardwareIds")
public class DeviceInfoHelper {

    @Deprecated
    public static DeviceInfo getDeviceInfo(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceName(Build.BRAND + "_" + Build.MODEL);
        deviceInfo.setOsVersion(Build.VERSION.SDK_INT);
        deviceInfo.setResolution((int) (displayMetrics.density * 160f));
        deviceInfo.setScreenSize(displayMetrics.widthPixels + "*" + displayMetrics.heightPixels);
        deviceInfo.setUniqueId(getDeviceUniqueId(activity));
        deviceInfo.setBuildSerial(Build.SERIAL);
        return deviceInfo;
    }

    public static DeviceInfo getDeviceInfo(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceName(Build.BRAND + "_" + Build.MODEL);
        deviceInfo.setOsVersion(Build.VERSION.SDK_INT);
        deviceInfo.setResolution((int) (displayMetrics.density * 160f));
        deviceInfo.setScreenSize(displayMetrics.widthPixels + "*" + displayMetrics.heightPixels);
        deviceInfo.setUniqueId(getDeviceUniqueId(context));
        deviceInfo.setBuildSerial(Build.SERIAL);
        return deviceInfo;
    }

    private static String getDeviceUniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID) + "_" + Build.SERIAL;
    }

}