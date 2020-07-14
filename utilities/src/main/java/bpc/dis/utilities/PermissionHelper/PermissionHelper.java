package bpc.dis.utilities.PermissionHelper;

import android.app.Activity;

import androidx.fragment.app.FragmentManager;

import com.androidisland.ezpermission.EzPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bpc.dis.alertdialog.AlertButton;
import bpc.dis.alertdialog.AlertClickListener;
import bpc.dis.alertdialog.AlertDialog;
import bpc.dis.utilities.IntentHelper.IntentHelper;
import bpc.dis.utilities.PermissionHelper.Enum.Permission;
import bpc.dis.utilities.PermissionHelper.Enum.PermissionType;
import bpc.dis.utilities.PermissionHelper.Listener.PermissionResultListener;
import bpc.dis.utilities.R;

public class PermissionHelper {

    private List<Object> permissionResultListeners;

    public PermissionHelper() {
        permissionResultListeners = new ArrayList<>();
    }

    public void checkPermission(Activity activity, PermissionRequest permissionRequest) {
        requestPermission(
                activity,
                permissionRequest.getPermission(),
                getPermissionResultListener(
                        activity,
                        permissionRequest.getFragmentManager(),
                        permissionRequest.getPermission(),
                        permissionRequest.getPermissionType(),
                        permissionRequest.getPermissionResultListener()
                )
        );
    }

    public void removeCallbacks() {
        if (permissionResultListeners != null && !permissionResultListeners.isEmpty()) {
            for (int i = 0; i < permissionResultListeners.size(); i++) {
                permissionResultListeners.remove(i);
            }
        }
    }

    private PermissionResultListener getPermissionResultListener(Activity activity, FragmentManager fragmentManager, Permission permission, PermissionType permissionType, PermissionResultListener userPermissionResultListener) {
        PermissionResultListener permissionResultListener = new PermissionResultListener() {

            @Override
            public void onPermissionGranted() {
                if (userPermissionResultListener != null) {
                    userPermissionResultListener.onPermissionGranted();
                }
            }

            @Override
            public void onPermissionDenied() {
                if (permissionType == PermissionType.NO_ALERT) {
                    if (userPermissionResultListener != null) {
                        userPermissionResultListener.onPermissionDenied();
                    }
                } else {
                    showAlertDialogDenied(activity, fragmentManager, tag -> {
                        requestPermission(activity, permission, this);
                    }, tag -> {
                        if (userPermissionResultListener != null) {
                            userPermissionResultListener.onPermissionDenied();
                        }
                    });
                }
            }

            @Override
            public void onPermissionDeniedForEver() {
                if (permissionType == PermissionType.NO_ALERT) {
                    if (userPermissionResultListener != null) {
                        userPermissionResultListener.onPermissionDeniedForEver();
                    }
                } else {
                    showAlertDialogDeniedForEver(activity, fragmentManager, tag ->
                                    IntentHelper.openAppPermissionSetting(activity)
                            , tag -> {
                                if (userPermissionResultListener != null) {
                                    userPermissionResultListener.onPermissionDeniedForEver();
                                }
                            }
                    );
                }
            }

        };
        permissionResultListeners.add(userPermissionResultListener);
        return permissionResultListener;
    }

    private void requestPermission(Activity activity, Permission permission, PermissionResultListener permissionResultListener) {
        EzPermission.Companion.with(activity)
                .permissions(new PermissionList().getPermissionList(permission))
                .request((granted, denied, permanentlyDenied) -> {
                    checkPermissionResult(granted, denied, permanentlyDenied, permissionResultListener);
                    return null;
                });
    }

    private void checkPermissionResult(Set<String> granted, Set<String> denied, Set<String> permanentlyDenied, PermissionResultListener permissionResultListener) {
        if (granted != null && !granted.isEmpty()) {
            if (permissionResultListener != null) {
                permissionResultListener.onPermissionGranted();
            }
        } else if (denied != null && !denied.isEmpty()) {
            if (permissionResultListener != null) {
                permissionResultListener.onPermissionDenied();
            }
        } else if (permanentlyDenied != null && !permanentlyDenied.isEmpty()) {
            if (permissionResultListener != null) {
                permissionResultListener.onPermissionDeniedForEver();
            }
        }
    }

    private void showAlertDialogDenied(Activity activity, FragmentManager fragmentManager, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        AlertDialog alertDialog = new AlertDialog.Builder()
                .setMessageText("for use application, you must allow this permission")
                .setCancelable(false)
                .setCloseEnable(false)
                .setHeight(0.2)
                .setWidth(0.7)
                .setBackgroundRes(R.drawable.permission_helper_alert_background)
                .setMessageTextSize(activity.getResources().getDimension(R.dimen.medium))
                .setMessageTextColor(activity.getResources().getColor(R.color.permission_helper_alert_text_color))
                .setAlertButtons(getAlertButtonsDenied(activity, alertClickListener1, alertClickListener2))
                .build();
        alertDialog.show(fragmentManager);
    }

    private void showAlertDialogDeniedForEver(Activity activity, FragmentManager fragmentManager, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        AlertDialog alertDialog = new AlertDialog.Builder()
                .setMessageText("please goto setting page and allowed permissions")
                .setCancelable(false)
                .setCloseEnable(false)
                .setHeight(0.2)
                .setWidth(0.7)
                .setBackgroundRes(R.drawable.permission_helper_alert_background)
                .setMessageTextSize(activity.getResources().getDimension(R.dimen.medium))
                .setMessageTextColor(activity.getResources().getColor(R.color.permission_helper_alert_text_color))
                .setAlertButtons(getAlertButtonsDeniedForEver(activity, alertClickListener1, alertClickListener2))
                .build();
        alertDialog.show(fragmentManager);
    }

    private List<AlertButton> getAlertButtonsDenied(Activity activity, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        List<AlertButton> alertButtons = new ArrayList<>();
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("ask again")
                        .setButtonTextColor(activity.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(activity.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener1)
                        .build()
        );
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("exit")
                        .setButtonTextColor(activity.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(activity.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener2)
                        .build()
        );
        return alertButtons;
    }

    private List<AlertButton> getAlertButtonsDeniedForEver(Activity activity, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        List<AlertButton> alertButtons = new ArrayList<>();
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("Setting")
                        .setButtonTextColor(activity.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(activity.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener1)
                        .build()
        );
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("exit")
                        .setButtonTextColor(activity.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(activity.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener2)
                        .build()
        );
        return alertButtons;
    }

}