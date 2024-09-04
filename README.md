# moment
[![](https://jitpack.io/v/AHHB/moment.svg)](https://jitpack.io/#AHHB/moment)

convert jalali and gregorian date + date utils

## Installation

### To use it in your Maven build add:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

and the dependency:

```xml
<dependency>
    <groupId>com.github.AHHB</groupId>
    <artifactId>moment</artifactId>
    <version>1.0.0</version>
</dependency>
```

### To use it in your Gradle build add:

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

and the dependency:

```java
dependencies {
    implementation 'com.github.AHHB:moment:1.0.0'
}
```

## Document

### definition

```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;
import com.ahhb.statics.TimeZones;

import java.util.Date;

Moment moment = new Moment(); // now time
Moment moment = new Moment(TimeZones.Asia_Tehran); // now time and set time zone
Moment moment = new Moment(1725457652039L);
Moment moment = new Moment(1725457652039L, TimeZones.Asia_Tehran);
Moment moment = new Moment(new Date());
Moment moment = new Moment(new Date(), TimeZones.Asia_Tehran);
Moment moment = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI);
Moment moment = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN);
Moment moment = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI, TimeZones.Asia_Tehran);
```

### locale

set result locale (jalali or gregorian)

```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;

Moment moment = new Moment().locale(Locale.GREGORIAN);
Moment moment = new Moment().locale(Locale.JALALI);
Moment moment = new Moment().locale(Locale.JALALI, false /*  Default is true. When true, the result of 'format()' is a Persian number. */);
```

### operations

Performing time operations using java.util.Calendar:

```java
import com.ahhb.Moment;
import com.ahhb.Operation;
import com.ahhb.statics.Locale;
import com.ibm.icu.util.Calendar;

Operation moment = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN)
        .locale(Locale.JALALI)
        .calenderOperation(calendar -> calendar.add(Calendar.HOUR, 5));
```

get Date:

```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;

import java.util.Date;

Date date = new Moment("1403/06/14", "yyyy/MM/dd", Locale.JALALI)
        .locale(Locale.GREGORIAN)
        .getDate();

```

formating:

```java
import com.ahhb.Moment;
import com.ahhb.statics.Locale;
import com.ibm.icu.util.Calendar;

String date = new Moment("2024-09-04 15:00", "yyyy-MM-dd HH:mm", Locale.GREGORIAN)
        .locale(Locale.JALALI)
        .calenderOperation(calendar -> calendar.add(Calendar.HOUR, 5))
        .format("yyyy/MM/dd - HH:mm");
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
