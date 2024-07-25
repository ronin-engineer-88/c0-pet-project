package source.utils;


import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils {

    public  final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String wrapDoubleQuote(String str) {
        String result = "\"" + str + "\"";
        return (str == null) ? null : result;
    }

    public static String validateParamString(String param) {
        if (param == null || param.isEmpty())
            return null;
        return param.trim();
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if(str == null) return false;
        else return pattern.matcher(str).matches();
    }

    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(str == null) return false;
        else return pattern.matcher(str).matches();
    }

    public static boolean isEqual(String s1, String s2) {
        if(s1 == null && s2 == null) return true;
        else if(s1 == null || s2 == null) return false;
        else return s1.equals(s2);
    }

    public static boolean isEquals(String s1, String s2, String... sn) {
        if(isEqual(s1, s2)) return true;
        else if(sn == null) return false;
        for(int i = 0; i < sn.length; i++) if(isEqual(s1, sn[i])) return true;
        return false;
    }

    public static boolean isEquals(boolean ic, String s1, String s2, String... sn) {
        if(isEquals(s1, s2)) return true; else if(sn == null) return false;
        for(int i = 0; i < sn.length; i++) if(isEquals(s1, sn[i], ic)) return true;
        return false;
    }

    public static boolean isEquals(String s1, String s2, boolean ic) {
        if(s1 == s2) return true;
        else if(s1 == null && s2 == null) return true;
        else if(s1 == null || s2 == null) return false;
        else return ic ? s1.equalsIgnoreCase(s2) : s1.equals(s2); // i
    }


    public static boolean isEmpty(String str) {
        return str == null || length(str.trim()) == 0;
    }

    public static int length(String str) {
        return str == null ? 0 : str.length();
    }

    public static <T> String validateValueNotNull(T value) {
        return value != null ? value.toString() : "";
    }

    public static String generateId(Date dateInitial, String prefix) {
        String date = DateUtils.formatVnShortDateFormat(dateInitial);
        Random random = new Random();
        return String.format("%s%s%04d%s", prefix, date, random.nextInt(100000), generateRandomString(4));
    }

    public static String generateRandomString(int length) {

        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
