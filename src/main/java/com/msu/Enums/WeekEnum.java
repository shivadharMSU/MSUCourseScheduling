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
    
    public static String getWeekByValue(int value) {
    	
    	if(value == 1) {
    		return "MON";
    	}else if(value == 2) {
    		return "TUE";
    	}else if(value == 3) {
    		return "WED";
    	}else if(value == 4) {
    		return "THU";
    	}else if(value == 5) {
    		return "FRI";
    	}else if(value == 6) {
    		return "SAT";
    	}
    	return null;
    	
    }
    
 public static int getWeekIdByWeekName(String name) {
    	
    	if(name.equalsIgnoreCase("Mon") ) {
    		return 1;
    	}if(name.equalsIgnoreCase("Tue") ) {
    		return 2;
    	}if(name.equalsIgnoreCase("Wed") ) {
    		return 3;
    	}if(name.equalsIgnoreCase("Thu") ) {
    		return 4;
    	}if(name.equalsIgnoreCase("Fri") ) {
    		return 5;
    	}if(name.equalsIgnoreCase("Sat") ) {
    		return 6;
    	}
    	return 0;
    	
    }
    

}
