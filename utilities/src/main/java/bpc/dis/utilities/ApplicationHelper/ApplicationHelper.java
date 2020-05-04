package bpc.dis.utilities.ApplicationHelper;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class ApplicationHelper {

    public static boolean isApplicationRunning(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
                    if (runningAppProcess.processName.equals(packageName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
