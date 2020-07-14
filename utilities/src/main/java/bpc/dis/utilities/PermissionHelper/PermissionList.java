package bpc.dis.utilities.PermissionHelper;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;

import bpc.dis.utilities.PermissionHelper.Enum.Permission;

public class PermissionList {

    public ArrayList<String> getPermissionList(Permission permission) {
        switch (permission) {
            case LOCATION:
                return getLocationPermissionList();
            case SMS:
                return geSmsPermissionList();
            case CALL_PHONE:
                return geCallPhonePermissionList();
            case STORAGE:
                return geStoragePermissionList();
        }
        return new ArrayList<>();
    }

    private ArrayList<String> getLocationPermissionList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(Manifest.permission.ACCESS_FINE_LOCATION);
        strings.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        return strings;
    }

    private ArrayList<String> geSmsPermissionList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(Manifest.permission.READ_SMS);
        strings.add(Manifest.permission.RECEIVE_SMS);
        return strings;
    }

    private ArrayList<String> geCallPhonePermissionList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(Manifest.permission.CALL_PHONE);
        return strings;
    }

    private ArrayList<String> geStoragePermissionList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            strings.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        return strings;
    }

}