package source.util;


import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtils {

    public static boolean isNotNull(Object object) {
        return object != null && org.springframework.util.StringUtils.hasText(String.valueOf(object));
    }

    public static boolean isNull(Object object) {
        return object == null || !StringUtils.hasText(String.valueOf(object));
    }

    public static String convertCamelCaseToSnakeCase(String camelStr) {
        if (camelStr == null) {
            return null;
        }

        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return camelStr.replaceAll(regex, replacement).toLowerCase();
    }

    public static String convertSnakeCaseToCamelCase(String snakeCase) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, snakeCase);
    }

    public static String standardString(Object o) {
        String s = String.valueOf(o).trim();
        while (s.contains("  ")) {
            s = s.replace("  ", " ");
        }

        return s;
    }


    public static String getStringValue(Map<String, Object> map, String fieldName) {
        Object data = map.get(fieldName);
        return data == null ? null : String.valueOf(data);
    }

    @SneakyThrows
    public static Object getAttributeByName(String attribute, Object currentInfo) {
        PropertyDescriptor descriptor =
                BeanUtils.getPropertyDescriptor(currentInfo.getClass(), attribute);
        if (descriptor != null) {
            Method getMethod = descriptor.getReadMethod();
            return getMethod.invoke(currentInfo);
        }
        return null;
    }

    @SneakyThrows
    public static void setAttributeByName(Object target, String attribute, Object value) {
        if (value == null || attribute == null) {
            return;
        }

        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(target.getClass(), attribute);
        if (descriptor != null) {
            if (descriptor.getPropertyType() == BigDecimal.class && value != null) {
                value = new BigDecimal(value.toString());
            }
            if (descriptor.getPropertyType() != value.getClass()) {
                return;
            }

            Method setMethod = descriptor.getWriteMethod();
            if (setMethod != null) {
                setMethod.invoke(target, value);
            }
        }
    }

    public static String getRequestIdFromRequestString(String request) {
        Matcher matcher = Pattern.compile("request_id\":[ ]*\"([^\"]*)\"").matcher(request);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static void sleepTest(int seconds) {
        for (int i = 1; i <= seconds; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Sleep count: " + i);
        }
    }

    public static String getAttributeName(String fieldName) {
        if (fieldName == null) {
            return null;
        } else {
            String[] arr = fieldName.split("_");
            StringBuilder result = new StringBuilder(arr[0]);

            for (int i = 1; i < arr.length; ++i) {
                String str = arr[i];
                result.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            }

            return result.toString();
        }
    }

    public static boolean checkIsUpdateField(Object o1, Object o2) {
        return !ObjectUtils.isEmpty(o1) && !ObjectUtils.nullSafeEquals(o1, o2);
    }

    public static String formatCurrency(BigDecimal money) {
        Locale locale = new Locale.Builder().setLanguage("us").setRegion("US").build();
        String moneyFormat = NumberFormat.getNumberInstance(locale).format(money);
        return moneyFormat;
    }

    public static BigDecimal convertStringToBigDecimal(String value) {
        return new BigDecimal(value);
    }

    public static Map<String, String> reverseMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> reversedMap = new HashMap<>();
        for (String key : map.keySet()) {
            reversedMap.put(map.get(key), key);
        }

        return reversedMap;
    }

    public static boolean checkRegex(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.find();
    }

    public static String preventionSqlInjectionLike(String value) {
        if (!Strings.isBlank(value)) {
            return value.replace("_", "!_").replace("%", "!%");
        } else {
            return value;
        }
    }

    public static String fulfillNumber(String pattern, String sequence) {
        if (sequence.length() > pattern.length())
            return sequence;
        String fullID = pattern.substring(0, pattern.length() - sequence.length()) + sequence;
        return fullID;
    }
}