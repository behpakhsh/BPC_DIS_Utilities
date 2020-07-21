package bpc.dis.utilities.PermissionHelper;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.androidisland.ezpermission.EzPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bpc.dis.alertdialog.AlertButton;
import bpc.dis.alertdialog.AlertClickListener;
import bpc.dis.alertdialog.AlertDialog;
import bpc.dis.alertdialog.AlertType;
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

    public void checkPermission(Context context, PermissionRequest permissionRequest) {
        requestPermission(
                context,
                permissionRequest.getPermission(),
                getPermissionResultListener(
                        context,
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

    private PermissionResultListener getPermissionResultListener(Context context, FragmentManager fragmentManager, Permission permission, PermissionType permissionType, PermissionResultListener userPermissionResultListener) {
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
                    showAlertDialogDenied(context, fragmentManager, permissionType,
                            tag ->
                                    requestPermission(context, permission, this),
                            tag -> {
                                if (userPermissionResultListener != null) {
                                    userPermissionResultListener.onPermissionDenied();
                                }
                            }
                    );
                }
            }

            @Override
            public void onPermissionDeniedForEver() {
                if (permissionType == PermissionType.NO_ALERT) {
                    if (userPermissionResultListener != null) {
                        userPermissionResultListener.onPermissionDeniedForEver();
                    }
                } else {
                    showAlertDialogDeniedForEver(context, fragmentManager, permissionType, tag ->
                                    IntentHelper.openAppPermissionSetting(context)
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

    private void requestPermission(Context context, Permission permission, PermissionResultListener permissionResultListener) {
        EzPermission.Companion.with(context)
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

    private void showAlertDialogDenied(Context context, FragmentManager fragmentManager, PermissionType permissionType, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        AlertDialog alertDialog = new AlertDialog.Builder()
                .setMessageText("For use application, you must allow this permission")
                .setCancelable(false)
                .setCloseEnable(false)
                .setHeight(0.2)
                .setWidth(0.7)
                .setAlertType(AlertType.PERMISSION_ALERT)
                .setBackgroundRes(R.drawable.permission_helper_alert_background)
                .setMessageTextSize(context.getResources().getDimension(R.dimen.medium))
                .setMessageTextColor(context.getResources().getColor(R.color.permission_helper_alert_text_color))
                .setAlertButtons(getAlertButtonsDenied(context, permissionType, alertClickListener1, alertClickListener2))
                .build();
        alertDialog.show(fragmentManager);
    }

    private void showAlertDialogDeniedForEver(Context context, FragmentManager fragmentManager, PermissionType permissionType, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        AlertDialog alertDialog = new AlertDialog.Builder()
                .setMessageText("please goto setting page and allowed permissions")
                .setCancelable(false)
                .setCloseEnable(false)
                .setHeight(0.2)
                .setWidth(0.7)
                .setAlertType(AlertType.PERMISSION_ALERT)
                .setBackgroundRes(R.drawable.permission_helper_alert_background)
                .setMessageTextSize(context.getResources().getDimension(R.dimen.medium))
                .setMessageTextColor(context.getResources().getColor(R.color.permission_helper_alert_text_color))
                .setAlertButtons(getAlertButtonsDeniedForEver(context, permissionType, alertClickListener1, alertClickListener2))
                .build();
        alertDialog.show(fragmentManager);
    }

    private List<AlertButton> getAlertButtonsDenied(Context context, PermissionType permissionType, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        List<AlertButton> alertButtons = new ArrayList<>();
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("ask again")
                        .setButtonTextColor(context.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(context.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener1)
                        .build()
        );
        String buttonText;
        if (permissionType == PermissionType.FORCE_ALERT) {
            buttonText = "exit";
        } else {
            buttonText = "cancel";
        }
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText(buttonText)
                        .setButtonTextColor(context.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(context.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener2)
                        .build()
        );
        return alertButtons;
    }

    private List<AlertButton> getAlertButtonsDeniedForEver(Context context, PermissionType permissionType, AlertClickListener alertClickListener1, AlertClickListener alertClickListener2) {
        List<AlertButton> alertButtons = new ArrayList<>();
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText("Setting")
                        .setButtonTextColor(context.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(context.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener1)
                        .build()
        );
        String buttonText;
        if (permissionType == PermissionType.FORCE_ALERT) {
            buttonText = "exit";
        } else {
            buttonText = "cancel";
        }
        alertButtons.add(
                new AlertButton.Builder()
                        .setButtonBackgroundRes(R.drawable.permission_helper_alert_button_background)
                        .setButtonText(buttonText)
                        .setButtonTextColor(context.getResources().getColor(R.color.permission_helper_alert_button_text_color))
                        .setButtonTextSize(context.getResources().getDimension(R.dimen.small))
                        .setAlertClickListener(alertClickListener2)
                        .build()
        );
        return alertButtons;
    }

}