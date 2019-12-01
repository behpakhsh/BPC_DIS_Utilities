package bpc.dis.utilities.ObjectUtilities;

import java.util.List;
import java.util.Map;

public class ObjectUtilities {

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(List objects) {
        return objects == null || objects.size() <= 0;
    }

    public static boolean isNullOrEmpty(Map objects) {
        return objects == null || objects.size() <= 0;
    }

    public static boolean isNotNullOrEmpty(Map objects) {
        if (objects == null) {
            return false;
        }
        return objects.size() > 0;
    }

    public static boolean isNotNullOrEmpty(List objects) {
        if (objects == null) {
            return false;
        }
        return objects.size() > 0;
    }

    public static boolean isNotEmpty(List objects) {
        return objects.size() > 0;
    }

    public static boolean isNotEmpty(Map objects) {
        return objects.size() > 0;
    }

}