package bpc.dis.utilities.StringUtilities;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DecimalFormat;

@SuppressLint("DefaultLocale")
public class StringUtilities {

    public static boolean stringContainsEnglish(String string) {
        for (char character : string.toCharArray()) {
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(character);
            if (unicodeBlock == Character.UnicodeBlock.BASIC_LATIN ||
                    unicodeBlock == Character.UnicodeBlock.LATIN_1_SUPPLEMENT ||
                    unicodeBlock == Character.UnicodeBlock.LATIN_EXTENDED_A ||
                    unicodeBlock == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNumber(String input) {
        return input.replaceAll("[0-9]", "").length() != 0;
    }

    public static boolean isCharacter(String input) {
        return input.replaceAll("[0-9]", "").length() == input.length();
    }

    public static String convertEnglishNumberToPersianNumber(String number) {
        String[] pNum = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
        number = number.replace("0", pNum[0]);
        number = number.replace("1", pNum[1]);
        number = number.replace("2", pNum[2]);
        number = number.replace("3", pNum[3]);
        number = number.replace("4", pNum[4]);
        number = number.replace("5", pNum[5]);
        number = number.replace("6", pNum[6]);
        number = number.replace("7", pNum[7]);
        number = number.replace("8", pNum[8]);
        number = number.replace("9", pNum[9]);
        return number;
    }

    public static String convertPersianNumberToEnglishNumber(String number) {
        String[] pNum = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        number = number.replace("۰", pNum[0]);
        number = number.replace("۱", pNum[1]);
        number = number.replace("۲", pNum[2]);
        number = number.replace("۳", pNum[3]);
        number = number.replace("۴", pNum[4]);
        number = number.replace("۵", pNum[5]);
        number = number.replace("۶", pNum[6]);
        number = number.replace("۷", pNum[7]);
        number = number.replace("۸", pNum[8]);
        number = number.replace("۹", pNum[9]);
        return number;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        return TextUtils.isEmpty(str.trim());
    }

    public static boolean isNullOrEmpty(CharSequence str) {
        if (str == null) {
            return true;
        }
        return TextUtils.isEmpty(str.toString().trim());
    }

    public static String floatToString(float value, int decimalCount) {
        if (decimalCount <= 0) {
            return String.format("%.0f", value);
        }
        return String.format("%." + decimalCount + "f", value);
    }

    public static String doubleToString(double value, int decimalCount) {
        if (decimalCount <= 0) {
            return String.format("%.0f", value);
        }
        return String.format("%." + decimalCount + "f", value);
    }

    public static String floatToPercentageString(float visitedCustomerPercentage) {
        String result = floatToString(visitedCustomerPercentage, 2);
        return result + "%";
    }

    public static String getCurrencyFormatter(Double s) {
        return new DecimalFormat("#,###").format(s);
    }

    public static String getCurrencyFormatter(float s) {
        return new DecimalFormat("#,###").format(s);
    }

    public static String getCurrencyFormatter(Object s) {
        return new DecimalFormat("#,###").format(s);
    }

    public static String getCurrencyFormatter(String s) {
        return new DecimalFormat("#,###").format(s);
    }

    public static double getCurrencyDouble(String numberString) {
        return Double.parseDouble(numberString.replaceAll(",", ""));
    }

    public static String convertCurrencyToString(String formattedValue) {
        return formattedValue.replace(",", "");
    }

    /**
     * @deprecated use PhoneNumberHelper
     */
    @Deprecated
    public static boolean isValidPhoneNumber(String phone) {
        return !StringUtilities.isNullOrEmpty(phone) && !StringUtilities.isNotNumber(phone) && phone.length() == 11 && phone.startsWith("09");
    }

}