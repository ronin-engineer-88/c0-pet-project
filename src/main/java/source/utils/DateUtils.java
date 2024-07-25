package source.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private final static DateFormat VN_DATE_FORMAT_DDMMYYYY = new SimpleDateFormat("ddMMyyyy");

    public static String DATE_TIME_DD_MM_YYY_1 = "dd/MM/yyyy HH:mm:ss";
    public static String DATE_TIME_YYYY_MM_DD_HH_MM = "yyyyMMddHHmm";
    public static String DATE_TIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_SQL = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MONTH = "MM-yyyy";
    public static final String DATE_FORMAT_ISO_8610 = "yyyyMMdd'T'HHmmss";
    public static final String PATTERN_DATE = "MMdd HHmmss";
    public static String DDMMYYYY = "dd/MM/yyyy";
    public static String MM_YYYY = "MM/yyyy";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYYMM = "yyyyMM";
    public static String MMYYYY = "MMyyyy";
    private final static DateFormat VN_SHORT_DATE_FORMAT = new SimpleDateFormat("ddMMyy");
    private final static DateFormat DATE_FORMAT_SQL_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat ISO_DTF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final static DateFormat VN_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private final static DateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private final static DateFormat DATE_TIME_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

    public static Calendar cal = Calendar.getInstance();

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Integer getMonth(Date date) {
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Integer getYear(Date date) {
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static String formatVnShortDateFormat(Date date) {
        try {
            return VN_SHORT_DATE_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDateFormatDDMMYYYY(Date date) {
        try {
            return VN_DATE_FORMAT_DDMMYYYY.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatVnShortDateTimeFormat(Date date) {
        try {
            return DATE_TIME_YYYYMMDDHHMMSS.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDateFile(Date date) {
        try {
            return DATE_FORMAT_YYYYMMDD.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatVNDateFormat(Date date) {
        try {
            return VN_DATE_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }


    public static String getISODateTimeString(Date date) {
        try {
            return ISO_DTF.format(date);
        } catch (Exception e) {
            return null;
        }
    }


    public static String getDateSQLString(Date date) {
        try {
            return DATE_FORMAT_SQL_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateAfter(Date currentDate, int days) {
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Long hourToSeconds(Long hour) {
        return hour * 60 * 60;
    }

    public static Long minutesToSeconds(Long minutes) {
        return minutes * 60;
    }

    public static String format(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date getStartOfDay() {
        return Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }

    public static long getCurrentDayMilliseconds() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        return cal.getTimeInMillis();
    }

    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atStartOfMonth(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = YearMonth.from(localDateTime).atDay(1).atStartOfDay();
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX).withNano(0);
        return localDateTimeToDate(endOfDay);
    }

    public static Date atEndOfMonth(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfMonth = YearMonth.from(localDateTime).atEndOfMonth().atStartOfDay().with(LocalTime.MAX).withNano(0);;
        return localDateTimeToDate(endOfMonth);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static long convertMinuteToMillis(long minute) {
        return minute * 60 * 1000;
    }

    public static long convertHoursToMillis(long hours) {
        return hours * 60 * 60 * 1000;
    }

    public static long convertDayToMillis(long day) {
        return day * 24 * 60 * 60 * 1000;
    }

    public static long convertWeekToMillis(long week) {
        return week * 7 * 24 * 60 * 60 * 1000;
    }

    public static long convertMonthToMillis(long month) {
        return month * 30 * 7 * 24 * 60 * 60 * 1000;
    }

    public static long convertSecondToMillis(long second) {
        return second * 1000;
    }


    public static String getTimeWithFormat(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }

    public static boolean isBefore(Date date, Date compareDate) {
        return date.before(compareDate);
    }

    public static boolean isAfter(Date date, Date compareDate) {
        return date.after(compareDate);
    }

    public static boolean isWithinRange(Date date, Date startDate, Date endDate) {
        return date.after(startDate) && date.before(endDate);
    }

    public static boolean isEqual(Date date, Date compareDate) {
        return date.compareTo(compareDate) == 0;
    }

    public static boolean compareDate(Long millisDate1, Long millisDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date1 = sdf.format(new Date(millisDate1));
        String date2 = sdf.format(new Date(millisDate2));
        return StringUtils.isEqual(date1, date2);
    }

    public static Date getDateFromRequest(String requestDate) {
        if (requestDate == null || requestDate.trim().isEmpty()) {
            return null;
        }
        requestDate = requestDate.trim();
        try {
            // Check if the date string is in milliseconds
            if (requestDate.matches("\\d{13}")) {
                long timestampInMillis = Long.parseLong(requestDate);
                return new Date(timestampInMillis);
            }

            // Check if the date string is in seconds
            if (requestDate.matches("\\d{10}")) {
                long timestampInSeconds = Long.parseLong(requestDate);
                return new Date(timestampInSeconds * 1000L);
            }

            // Check if the date string is in date format (e.g., yyyy-MM-dd)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            if (dateFormat.parse(requestDate) != null) {
                return dateFormat.parse(requestDate);
            }

            // Check if the date string is in datetime format (e.g., yyyy-MM-dd HH:mm:ss)
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTimeFormat.setLenient(false);
            if (dateTimeFormat.parse(requestDate) != null) {
                return dateTimeFormat.parse(requestDate);
            }
        } catch (Exception ex) {
            return null;
        }

        return null;
    }

    public static Date plusDays(Date date, long days) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateAfter = localDate.plusDays(days);
        Date result = Date.from(localDateAfter.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return result;
    }

    public static Date minusDays(Date date, long days) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateBefore = localDate.minusDays(days);
        Date result = Date.from(localDateBefore.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return result;
    }

    public static Date plusMonth(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static java.sql.Date toSqlDate(Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    public static java.sql.Timestamp toTimeStamp(Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

    public static boolean isFirstOfMonth(Date date) {
        LocalDate localDate = LocalDate.from(dateToLocalDateTime(date));
        return localDate.getDayOfMonth() == 1;
    }

    public static boolean isEndOfMonth(Date date) {
        LocalDate localDate = LocalDate.from(dateToLocalDateTime(date));
        return localDate.getDayOfMonth() == localDate.lengthOfMonth();
    }
}