package com.ahhb;

import com.ahhb.statics.Locale;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

import java.util.Date;

public class Operation {
    private final Calendar calendar;
    private final Locale thrLocale;
    private String jalaliLang = "JALALI";

    public Operation(Calendar calendar, Locale thrLocale, String jalaliLang) {
        this.calendar = calendar;
        this.thrLocale = thrLocale;
        this.jalaliLang = jalaliLang;
    }

    /**
     * get result date string formatted
     * @param format for examole yyyy/MM/dd
     * @return
     */
    public String format(String format){
        if (this.thrLocale == Locale.JALALI){
            ULocale persianLocale = new ULocale(this.jalaliLang + "@calendar=persian");
            SimpleDateFormat df = new SimpleDateFormat (format, persianLocale );
            return df.format(this.calendar.getTime());
        }else {
            ULocale gregorianLocale = new ULocale("GREGORIAN@calendar=gregorian");
            SimpleDateFormat df = new SimpleDateFormat (format, gregorianLocale);
            return df.format(calendar.getTime());
        }
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
