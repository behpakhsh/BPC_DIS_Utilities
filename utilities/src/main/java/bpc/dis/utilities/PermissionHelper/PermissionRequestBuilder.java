package bpc.dis.utilities.PermissionHelper;

import androidx.fragment.app.FragmentManager;

import bpc.dis.utilities.PermissionHelper.Enum.Permission;
import bpc.dis.utilities.PermissionHelper.Enum.PermissionType;
import bpc.dis.utilities.PermissionHelper.Listener.PermissionResultListener;

public class PermissionRequestBuilder {

    private FragmentManager fragmentManager;
    private Permission permission;
    private PermissionType permissionType;
    private PermissionResultListener permissionResultListener;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public PermissionRequestBuilder setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public Permission getPermission() {
        return permission;
    }

    public PermissionRequestBuilder setPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public PermissionRequestBuilder setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public PermissionResultListener getPermissionResultListener() {
        return permissionResultListener;
    }

    public PermissionRequestBuilder setPermissionResultListener(PermissionResultListener permissionResultListener) {
        this.permissionResultListener = permissionResultListener;
        return this;
    }

    public PermissionRequest build() {
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setFragmentManager(getFragmentManager());
        permissionRequest.setPermission(getPermission());
        permissionRequest.setPermissionResultListener(getPermissionResultListener());
        permissionRequest.setPermissionType(getPermissionType());
        return permissionRequest;
    }

}