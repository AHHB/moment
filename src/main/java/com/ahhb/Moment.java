package com.ahhb;

import com.ahhb.exeption.MomentException;
import com.ahhb.statics.Lang;
import com.ahhb.statics.Locale;
import com.ahhb.statics.TimeZones;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import java.text.ParseException;
import java.util.Date;

/**
 * The `Moment` class provides functionality to convert dates between Jalali, Hijri, and Gregorian calendars.
 * It supports various constructors to initialize the date using epoch time, `Date` objects, or formatted strings.
 * The class also allows setting the locale and time zone for date operations.
 */
public class Moment {
    private Calendar calendar; // Calendar instance to hold the date
    private Locale theLocale; // Locale of the date, can be JALALI, GREGORIAN, or HIJRI
    private Lang lang = Lang.ENGLISH; // Language for output formatting, default is English

    private static final String GREGORIAN_LOCALE_ID = "GREGORIAN@calendar=gregorian";
    private static final String JALALI_LOCALE_ID = "JALALI@calendar=persian";
    private static final String HIJRI_LOCALE_ID = "HIJRI@calendar=islamic-civil";

    /**
     * Initializes the calendar with the specified locale, date, and time zone.
     * @param locale The locale of the date (JALALI, GREGORIAN, or HIJRI).
     * @param date The date to be set in the calendar.
     * @param timeZone The time zone to be used for the calendar.
     */
    protected void initialCalender(Locale locale, Date date, TimeZones timeZone) {
        ULocale uLocale = switch (locale) {
            case JALALI -> new ULocale(JALALI_LOCALE_ID);
            case GREGORIAN -> new ULocale(GREGORIAN_LOCALE_ID);
            case HIJRI -> new ULocale(HIJRI_LOCALE_ID);
        };

        this.calendar = Calendar.getInstance(uLocale);
        this.calendar.setLenient(false);
        this.calendar.clear();
        this.calendar.setTimeZone(timeZone != null ? TimeZone.getTimeZone(timeZone.asString()) : TimeZone.getDefault());
        this.calendar.setTime(date);
    }

    /**
     * date converter
     * get current time and set default time zone
     */
    public Moment(){
        initialCalender(Locale.GREGORIAN, new Date(), null);
    }

    /**
     * date converter
     * get current time and time zone
     * @param timeZone The time zone to be used.
     */
    public Moment(TimeZones timeZone){
        initialCalender(Locale.GREGORIAN, new Date(), timeZone);
    }

    /**
     * date converter
     * get epoch time and set default time zone
     * @param date for examole 1716195081761
     */
    public Moment(long date){
        initialCalender(Locale.GREGORIAN, new Date(date), null);
    }

    /**
     * date converter
     * get epoch time and time zone
     * @param date for examole 1716195081761
     * @param timeZone The time zone to be used.
     */
    public Moment(long date, TimeZones timeZone){
        initialCalender(Locale.GREGORIAN, new Date(date), timeZone);
    }

    /**
     * date converter
     * get instance of date and set default time zone
     * @param date
     */
    public Moment(Date date){
        initialCalender(Locale.GREGORIAN, date, null);
    }

    /**
     * date converter
     * get instance of date and time zone
     * @param date
     * @param timeZone The time zone to be used.
     */
    public Moment(Date date, TimeZones timeZone){
        initialCalender(Locale.GREGORIAN, date, timeZone);
    }

    /**
     * date converter
     * get string format of date and set default time zone
     * @param date for example 1403/02/31
     * @param format for example yyyy-MM-dd
     * @param locale locale of input date
     */
    public Moment(String date, String format, Locale locale) throws MomentException{
        if (date == null || date.isEmpty() || format == null || format.isEmpty()){
            throw new MomentException("input incorrect in moment method");
        }
        try {
            if (locale == Locale.GREGORIAN){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(GREGORIAN_LOCALE_ID));
                initialCalender(Locale.GREGORIAN, df.parse(date), null);
            }else if (locale == Locale.JALALI){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(JALALI_LOCALE_ID));
                initialCalender(Locale.JALALI, df.parse(date), null);
            } else if (locale == Locale.HIJRI) {
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(HIJRI_LOCALE_ID));
                initialCalender(Locale.HIJRI, df.parse(date), null);
            } else {
                throw new MomentException("locale not supported");
            }
        }catch (ParseException e){
            throw new MomentException("cant parse date") ;
        }
    }

    /**
     * date converter
     * get string format of date and time zone
     * @param date for example 1403/02/31
     * @param format for example yyyy-MM-dd
     * @param locale locale of input date
     * @param timeZone The time zone to be used.
     */
    public Moment(String date, String format, Locale locale, TimeZones timeZone) throws MomentException{
        if (date == null || date.isEmpty() || format == null || format.isEmpty()){
            throw new MomentException("input incorrect in moment method");
        }
        try {
            if (locale == Locale.GREGORIAN){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(GREGORIAN_LOCALE_ID));
                initialCalender(Locale.GREGORIAN, df.parse(date), timeZone);
            }else if (locale == Locale.JALALI){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(JALALI_LOCALE_ID));
                initialCalender(Locale.JALALI, df.parse(date), timeZone);
            } else if (locale == Locale.HIJRI) {
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, new ULocale(HIJRI_LOCALE_ID));
                initialCalender(Locale.HIJRI, df.parse(date), timeZone);
            } else {
                throw new MomentException("locale not supported");
            }
        }catch (ParseException e){
            throw new MomentException("cant parse date") ;
        }
    }

    /**
     * set result date locale
     * @param locale
     * @return
     */
    public Operation locale(Locale locale){
        this.theLocale = locale;
        return new Operation(this.calendar, this.theLocale, this.lang);
    }

    /**
     * set result date locale
     * @param locale
     * @param persianLang if local is JALALI and persianLang is true then result number and word is persian
     * @return
     */
    @Deprecated(since = "1.1.0")
    public Operation locale(Locale locale, boolean persianLang){
        this.theLocale = locale;
        this.lang = persianLang ? Lang.PERSIAN : Lang.ENGLISH;
        return new Operation(this.calendar, this.theLocale, this.lang);
    }
}
