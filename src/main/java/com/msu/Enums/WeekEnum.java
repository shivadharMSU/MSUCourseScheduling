package com.msu.Enums;

public enum WeekEnum {
	
	
	MONDAY(1),
    Tuesday(2),
    Wednday(3),
    Thursday(4),
	Friday(5),
	Saturday(6),
	Sunday(7);

    private final int value;

    WeekEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static String getWeekEnum(int value) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.value == value) {
                return weekEnum.name();
            }
        }
        return null; // or throw an exception if value doesn't match any semester
    }

}
