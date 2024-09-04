package com.ahhb;

import com.ahhb.exeption.MomentException;
import com.ahhb.statics.Locale;
import com.ahhb.statics.TimeZones;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import java.text.ParseException;
import java.util.Date;

public class Moment {
    private Calendar calendar;
    private Locale theLocale;
    private String jalaliLang = "fa_IR";

    private static final String GREGORIAN_LOCALE_ID = "GREGORIAN@calendar=gregorian";
    private static final String JALALI_LOCALE_ID = "JALALI@calendar=persian";

    private void gregorianToJalali (int year, int month, int day) {
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getDefault());
        gregorianCalendar.set(year, month, day);

        this.calendar = gregorianCalendar;
    }

    private void jalaliToGregorian(int year, int month, int day) {
        ULocale persianLocale = new ULocale(this.jalaliLang + "@calendar=persian");

        Calendar persianCal = Calendar.getInstance(persianLocale);
        persianCal.clear();
        persianCal.setTimeZone(TimeZone.getDefault());
        persianCal.set(year, month, day);

        this.calendar = persianCal;
    }

    /**
     * date converter
     * get current time and default time zone
     */
    public Moment(){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getDefault());
        gregorianCalendar.setTime(new Date());

        this.calendar = gregorianCalendar;
    }

    /**
     * date converter
     * get current time
     * @param timeZone
     */
    public Moment(TimeZones timeZone){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone(timeZone.asString()));
        gregorianCalendar.setTime(new Date());

        this.calendar = gregorianCalendar;
    }

    /**
     * date converter
     * get epoch time and set default time zone
     * @param date for examole 1716195081761
     */
    public Moment(long date){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getDefault());
        gregorianCalendar.setTime(new Date(date));

        this.calendar = gregorianCalendar;
    }

    /**
     * date converter
     * get epoch time and time zone
     * @param date for examole 1716195081761
     * @param timeZone
     */
    public Moment(long date, TimeZones timeZone){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone(timeZone.asString()));
        gregorianCalendar.setTime(new Date(date));

        this.calendar = gregorianCalendar;
    }

    /**
     * date converter
     * get instance of date and set default time zone
     * @param date
     */
    public Moment(Date date){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getDefault());
        gregorianCalendar.setTime(date);

        this.calendar = gregorianCalendar;
    }

    /**
     * date converter
     * get instance of date and time zone
     * @param date
     * @param timeZone
     */
    public Moment(Date date, TimeZones timeZone){
        ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);

        Calendar gregorianCalendar = Calendar.getInstance(gregorianLocale);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone(timeZone.asString()));
        gregorianCalendar.setTime(date);

        this.calendar = gregorianCalendar;
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
            ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);
            ULocale persianLocale = new ULocale(JALALI_LOCALE_ID);
            format = format.replace("YYYY", "yyyy");

            if (locale == Locale.GREGORIAN){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, gregorianLocale);

                Calendar cal = Calendar.getInstance(gregorianLocale);
                cal.setLenient(false);
                cal.clear();
                cal.setTimeZone(TimeZone.getDefault());
                cal.setTime(df.parse(date));

                this.gregorianToJalali(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }else {

                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, persianLocale);

                Calendar cal = Calendar.getInstance(persianLocale);
                cal.setLenient(false);
                cal.clear();
                cal.setTimeZone(TimeZone.getDefault());
                cal.setTime(df.parse(date));

                this.jalaliToGregorian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
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
     * @param timeZone
     */
    public Moment(String date, String format, Locale locale, TimeZones timeZone) throws MomentException{
        if (date == null || date.isEmpty() || format == null || format.isEmpty()){
            throw new MomentException("input incorrect in moment method");
        }
        try {
            ULocale gregorianLocale = new ULocale(GREGORIAN_LOCALE_ID);
            ULocale persianLocale = new ULocale(JALALI_LOCALE_ID);
            format = format.replace("YYYY", "yyyy");

            if (locale == Locale.GREGORIAN){
                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, gregorianLocale);

                Calendar cal = Calendar.getInstance(gregorianLocale);
                cal.setLenient(false);
                cal.clear();
                cal.setTimeZone(TimeZone.getTimeZone(timeZone.asString()));
                cal.setTime(df.parse(date));

                this.gregorianToJalali(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }else {

                com.ibm.icu.text.SimpleDateFormat df = new SimpleDateFormat(format, persianLocale);

                Calendar cal = Calendar.getInstance(persianLocale);
                cal.setLenient(false);
                cal.clear();
                cal.setTimeZone(TimeZone.getTimeZone(timeZone.asString()));
                cal.setTime(df.parse(date));

                this.jalaliToGregorian(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }
        }catch (ParseException e){
            throw new MomentException("cant parse date") ;
        }
    }

    /**
     * set result date local
     * @param locale
     * @return
     */
    public Operation locale(Locale locale){
        this.theLocale = locale;
        return new Operation(this.calendar, this.theLocale, this.jalaliLang);
    }

    /**
     * set result date local
     * @param locale
     * @param persianLang if local is JALALI and persianLang is true then result number and word is persian
     * @return
     */
    public Operation locale(Locale locale, boolean persianLang){
        this.theLocale = locale;
        this.jalaliLang = persianLang ? "fa_IR" : "";
        return new Operation(this.calendar, this.theLocale, this.jalaliLang);
    }
}
