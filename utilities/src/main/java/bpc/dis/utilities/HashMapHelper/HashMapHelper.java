package bpc.dis.utilities.HashMapHelper;

import java.util.HashMap;
import java.util.Map;

import bpc.dis.utilities.StringUtilities.StringUtilities;

public class HashMapHelper {

    public static HashMap<String, Object> convertFaNumberToEnglish(HashMap<String, Object> originalParams) {
        if (originalParams != null && !originalParams.isEmpty()) {
            HashMap<String, Object> newParams = new HashMap<>();
            for (Map.Entry<String, Object> entry : originalParams.entrySet()) {
                newParams.put(
                        entry.getKey(),
                        StringUtilities.convertPersianNumberToEnglishNumber(String.valueOf(entry.getValue()))
                );
            }
            return newParams;
        }
        return new HashMap<>();
    }

}
