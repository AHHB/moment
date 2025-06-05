package com.ahhb.statics;

public enum Lang {
    PERSIAN("fa_IR"),
    ENGLISH("en"),
    ARABIC("ar");

    private final String locale;

    Lang(String locale) {
        this.locale = locale;
    }

    public String getLocale(){
        return this.locale;
    }
}
