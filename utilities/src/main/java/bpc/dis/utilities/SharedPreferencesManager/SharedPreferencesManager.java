package bpc.dis.utilities.SharedPreferencesManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {

    private String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int applicationName = applicationInfo.labelRes;
        if (applicationName == 0) {
            return applicationInfo.nonLocalizedLabel.toString();
        } else {
            return context.getString(applicationName);
        }
    }

    public String get(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    public int get(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void set(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean get(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public double get(Context context, String key, double defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String string = sharedPreferences.getString(key, String.valueOf(defaultValue));
        if (string == null) {
            return defaultValue;
        }
        return Double.parseDouble(string);
    }

    public void set(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void set(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void set(Context context, String key, double value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(getApplicationName(context), MODE_PRIVATE);
    }

    public void reset(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

}