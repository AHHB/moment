# moment
[![](https://jitpack.io/v/AHHB/moment.svg)](https://jitpack.io/#AHHB/moment)

A Java library for converting between Jalali, Hijri or Gregorian dates, performing date operations, and formatting dates.

---

## Features

- Convert between Jalali, Hijri or Gregorian calendars.
- Perform date operations using `java.util.Calendar`.
- Format dates with customizable patterns.
- Support for multiple time zones and locales.

---

## Installation

### Maven:
Add the JitPack repository to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add the dependency:
```xml
<dependency>
    <groupId>com.github.AHHB</groupId>
    <artifactId>moment</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle:
Add the JitPack repository to your `settings.gradle`:
```java
allprojects {
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
    }
}
```
Add the dependency:
```java
dependencies {
    implementation 'com.github.AHHB:moment:1.0.0'
}
```

---

## Usage
Creating a `Moment` Instance
You can create a `Moment` instance in various ways:
```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;
import com.ahhb.statics.TimeZones;
import java.util.Date;

// Current time with default time zone
Moment moment = new Moment();

// Current time with a specific time zone
Moment moment = new Moment(TimeZones.Asia_Tehran);

// From epoch time (milliseconds)
Moment moment = new Moment(1725457652039L);

// From epoch time with a specific time zone
Moment moment = new Moment(1725457652039L, TimeZones.Asia_Tehran);

// From a Date object
Moment moment = new Moment(new Date());

// From a Date object with a specific time zone
Moment moment = new Moment(new Date(), TimeZones.Asia_Tehran);

// From a formatted date string
Moment moment = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI);

// From a formatted date string with a specific time zone
Moment moment = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI, TimeZones.Asia_Tehran);
```

### Setting Locale
You can set the locale of the `Moment` instance to either Jalali, Hijri or Gregorian:
```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;

// Set locale to Gregorian
Moment moment = new Moment().locale(Locale.GREGORIAN);

// Set locale to Jalali
Moment moment = new Moment().locale(Locale.JALALI);

// Set locale to Hijri
Moment moment = new Moment().locale(Locale.HIJRI);
```

### Performing Date Operations
You can perform operations on the `Moment` instance using `java.util.Calendar`:
```java
import com.ahhb.Moment;
import com.ahhb.Operation;
import com.ahhb.statics.Locale;
import com.ibm.icu.util.Calendar;

// Add 5 hours to a Gregorian date and convert to Jalali
Operation operation = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN)
        .locale(Locale.JALALI)
        .calenderOperation(calendar -> calendar.add(Calendar.HOUR, 5));
```

### Getting a `Date` Object
You can retrieve the `Date` object from a `Moment` instance:
```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;
import java.util.Date;

// Convert a Jalali date to Gregorian and get the Date object
Date date = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI)
        .locale(Locale.GREGORIAN)
        .getDate();
```

### Formatting Dates
Format the date using a custom pattern:

```java
import com.ahhb.Moment;
import com.ahhb.statics.Lang;
import com.ahhb.statics.Locale;
import com.ibm.icu.util.Calendar;

// Format a Gregorian date, add 5 hours, and convert to Jalali
String formattedDate = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN)
        .locale(Locale.JALALI)
        .calenderOperation(calendar -> calendar.add(Calendar.HOUR, 5))
        .format("yyyy/MM/dd - HH:mm");

// Format a Gregorian date, add 5 hours, and convert to Jalali (use persian language)
String formattedDate = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN)
        .locale(Locale.JALALI)
        .calenderOperation(calendar -> calendar.add(Calendar.HOUR, 5))
        .format("yyyy/MM/dd - HH:mm", Lang.PERSIAN);
```

### Pattern Syntax

You can use the following symbols in your formatting pattern:

| symol | description                                                                     |
|-------|---------------------------------------------------------------------------------|
| G     | Era designator (before christ, after christ)                                    |
| y     | Year (e.g. 12 or 2012). Use either yy or yyyy.                                  |
| M     | Month in year. Number of M's determine length of format (e.g. MM, MMM or MMMMM) |
| d     | Day in month. Number of d's determine length of format (e.g. d or dd)           |
| h     | Hour of day, 1-12 (AM / PM) (normally hh)                                       |
| H     | Hour of day, 0-23 (normally HH)                                                 |
| m     | Minute in hour, 0-59 (normally mm)                                              |
| s     | Second in minute, 0-59 (normally ss)                                            |
| S     | Millisecond in second, 0-999 (normally SSS)                                     |
| E     | Day in week (e.g Monday, Tuesday etc.)                                          |
| D     | Day in year (1-366)                                                             |
| F     | Day of week in month (e.g. 1st Thursday of December)                            |
| w     | Week in year (1-53)                                                             |
| W     | Week in month (0-5)                                                             |
| a     | AM / PM marker                                                                  |
| k     | Hour in day (1-24, unlike HH's 0-23)                                            |
| K     | Hour in day, AM / PM (0-11)                                                     |
| z     | Time Zone                                                                       |
