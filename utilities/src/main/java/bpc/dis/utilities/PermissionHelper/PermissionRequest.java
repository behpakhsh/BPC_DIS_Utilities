package bpc.dis.utilities.PermissionHelper;

import androidx.fragment.app.FragmentManager;

import bpc.dis.utilities.PermissionHelper.Enum.Permission;
import bpc.dis.utilities.PermissionHelper.Enum.PermissionType;
import bpc.dis.utilities.PermissionHelper.Listener.PermissionResultListener;

public class PermissionRequest {

    private FragmentManager fragmentManager;
    private Permission permission;
    private PermissionType permissionType;
    private PermissionResultListener permissionResultListener;

    public static PermissionRequestBuilder Builder() {
        return new PermissionRequestBuilder();
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public PermissionResultListener getPermissionResultListener() {
        return permissionResultListener;
    }

    public void setPermissionResultListener(PermissionResultListener permissionResultListener) {
        this.permissionResultListener = permissionResultListener;
    }

}