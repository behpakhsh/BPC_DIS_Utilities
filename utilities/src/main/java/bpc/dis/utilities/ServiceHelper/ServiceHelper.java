package bpc.dis.utilities.ServiceHelper;

import android.app.ActivityManager;
import android.content.Context;

public class ServiceHelper {

    public static boolean isServiceRunning(Context context, String ServicePackageName) {
        for (ActivityManager.RunningServiceInfo service : getActivityManager(context).getRunningServices(Integer.MAX_VALUE)) {
            if (ServicePackageName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

}
