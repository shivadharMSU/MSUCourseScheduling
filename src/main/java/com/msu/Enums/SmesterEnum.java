package com.msu.Enums;

public enum SmesterEnum {
	
	SPRING(1),
    SUMMER(2),
    FALL(3),
    WINTER(4);

    private final int value;

    SmesterEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static String getSemesterName(int value) {
        for (SmesterEnum semester : SmesterEnum.values()) {
            if (semester.value == value) {
                return semester.name();
            }
        }
        return null; // or throw an exception if value doesn't match any semester
    }

}
