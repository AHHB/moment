package com.ahhb;

import com.ahhb.statics.Lang;
import com.ahhb.statics.Locale;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

import java.util.Date;

public class Operation {
    private final Calendar calendar;
    private final Locale thrLocale;
    private Lang lang;

    public Operation(Calendar calendar, Locale thrLocale, Lang lang) {
        this.calendar = calendar;
        this.thrLocale = thrLocale;
        this.lang = lang;
    }

    /**
     * get result date string formatted
     * @param format for examole yyyy/MM/dd
     * @return
     */
    public String format(String format){
        ULocale locale = switch (this.thrLocale) {
            case JALALI -> new ULocale(this.lang.getLocale() + "@calendar=persian");
            case GREGORIAN -> new ULocale("GREGORIAN@calendar=gregorian");
            case HIJRI -> new ULocale(this.lang.getLocale() + "@calendar=islamic-civil");
        };

        SimpleDateFormat df = new SimpleDateFormat (format, locale);
        return df.format(this.calendar.getTime());
    }

    /**
     * get result date string formatted
     * @param format for examole yyyy/MM/dd
     * @param lang select output language
     * @return
     */
    public String format(String format, Lang lang){
        this.lang = lang;
        ULocale locale = switch (this.thrLocale) {
            case JALALI -> new ULocale(this.lang.getLocale() + "@calendar=persian");
            case GREGORIAN -> new ULocale("GREGORIAN@calendar=gregorian");
            case HIJRI -> new ULocale(this.lang.getLocale() + "@calendar=islamic-civil");
        };

        SimpleDateFormat df = new SimpleDateFormat (format, locale);
        return df.format(this.calendar.getTime());
    }

    /**
     * get result date
     * @return Java.Util.Date
     */
    public Date getDate(){
        return this.calendar.getTime();
    }


    /**
     * operating in result date
     * @param operation
     * @return Operation
     */
    public Operation calenderOperation(CalendarOperation operation) {
        operation.perform(calendar);
        return this;
    }

    @FunctionalInterface
    public interface CalendarOperation {
        void perform(Calendar calendar);
    }
}
