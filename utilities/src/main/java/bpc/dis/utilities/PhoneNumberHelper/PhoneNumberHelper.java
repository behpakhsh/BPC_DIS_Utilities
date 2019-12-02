package bpc.dis.utilities.PhoneNumberHelper;

import bpc.dis.utilities.StringUtilities.StringUtilities;

public class PhoneNumberHelper {

    public static String getStandardPhoneNumber(String phoneNumber) {
        if ((phoneNumber.length() == 10 && phoneNumber.startsWith("9")) || (phoneNumber.length() == 11 && phoneNumber.startsWith("0"))) {
            if (!phoneNumber.startsWith("0") && phoneNumber.length() == 10) {
                return "0" + phoneNumber;
            }
            return phoneNumber;
        }
        return null;
    }

    public static boolean isValidPhoneNumber(String phone) {
        return !StringUtilities.isNullOrEmpty(phone) && !StringUtilities.isNotNumber(phone) && phone.length() == 11 && phone.startsWith("09");
    }

}