package bpc.dis.utilities.PermissionHelper;

import bpc.dis.utilities.PermissionHelper.Enum.Permission;
import bpc.dis.utilities.R;

public class PermissionTitle {

    public int getPermissionPersianTitleRes(Permission permission) {
        switch (permission) {
            case LOCATION:
                return R.string.disLocation;
            case SMS:
                return R.string.disSms;
            case STORAGE:
                return R.string.disStorage;
            case CALL_PHONE:
                return R.string.disCall;
            default:
                return R.string.disWhiteSpace;
        }
    }

}
