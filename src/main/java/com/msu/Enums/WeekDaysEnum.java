package com.msu.Enums;

public enum WeekDaysEnum {
    MONDAY(1, "Mon"),
    TUESDAY(2, "Tue"),
    WEDNESDAY(3, "Wed"),
    THURSDAY(4, "Thu"),
    FRIDAY(5, "Fri"),
    SATURDAY(6, "Sat"),
    SUNDAY(7, "Sun"); // Assuming you want to include Sunday

    private final int dayNumber;
    private final String dayAbbreviation;

    WeekDaysEnum(int dayNumber, String dayAbbreviation) {
        this.dayNumber = dayNumber;
        this.dayAbbreviation = dayAbbreviation;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public String getDayAbbreviation() {
        return dayAbbreviation;
    }

    public static WeekDaysEnum fromNumber(int number) {
        for (WeekDaysEnum day : values()) {
            if (day.getDayNumber() == number) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day number: " + number);
    }

    public static WeekDaysEnum fromAbbreviation(String abbreviation) {
        for (WeekDaysEnum day : values()) {
            if (day.getDayAbbreviation().equalsIgnoreCase(abbreviation)) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day abbreviation: " + abbreviation);
    }
}
