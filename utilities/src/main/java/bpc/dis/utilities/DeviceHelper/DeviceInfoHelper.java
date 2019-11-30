package bpc.dis.utilities.DeviceHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

@SuppressLint("HardwareIds")
public class DeviceInfoHelper {

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

    public static String getDeviceUniqueId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID) + "_" + Build.SERIAL;
    }

}