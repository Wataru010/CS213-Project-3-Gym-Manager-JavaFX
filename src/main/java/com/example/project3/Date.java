package com.example.project3;

import java.util.Calendar;

/**
 * Date class is used deal with the information provide by the users.
 * Date class generate object for date information, provide information for a certain Date objects, and check Date object is a valid calendar date.
 *
 * @author Sihua Zhou
 */
public class Date implements Comparable<Date> {
    /**
     * Year value in a Date object.
     */
    private int year;
    /**
     * Month value in a Date object.
     */
    private int month;
    /**
     * Day value in a Date object.
     */
    private int day;

    /**
     * Number of years for quadrennial.
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Number of years for centennial.
     */
    public static final int CENTENNIAL = 100;
    /**
     * Number of years for Quartercentennial.
     */
    public static final int QUARTERCENTENNIAL = 400;
    /**
     * Days in a month in a normal year.
     */
    private static final int[] MOUTH_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**
     * Days in a month in a leap normal year.
     */
    private static final int[] MOUTH_DAYS_LEAPYEAR = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**
     * Minimum days in a month.
     */
    private static final int LESS_DAY_IN_A_MONTH = 1;
    /**
     * Number of elements in a date String input.
     */
    private static final int NUMBER_OF_INPUT = 3;
    /**
     * Index of month in the data information String array.
     */
    private static final int MOUTH_POSITION = 0;
    /**
     * Index of day in the data information String array.
     */
    private static final int DAY_POSITION = 1;
    /**
     * Index of year in the data information String array.
     */
    private static final int YEAR_POSITION = 2;

    /**
     * Generate a Date object contain today's date.
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH) + 1;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Overloaded Constructor generate a Date object with given date information.
     *
     * @param date Input with date format MM/DD/YYYY.
     */
    public Date(String date) {
        String[] dateArray = date.split("/", NUMBER_OF_INPUT);
        this.month = Integer.parseInt(dateArray[MOUTH_POSITION]);
        this.day = Integer.parseInt(dateArray[DAY_POSITION]);
        this.year = Integer.parseInt(dateArray[YEAR_POSITION]);
    }

    /**
     * Setter method change the year value for a Date object.
     *
     * @param year The new year value for the Date object.
     */
    public void setNewYear(int year) {
        this.year = year;
    }

    /**
     * Setter method change the month value for a Date object.
     *
     * @param month the new month value for the Date object.
     */
    public void setNewMonth(int month) {
        this.month = month;
    }

    /**
     * Setter method change the day value for a Date object.
     *
     * @param day the new day value for the Date object.
     */
    public void setNewDay(int day) {
        this.day = day;
    }

    /**
     * Getter method return value of year in this Date object.
     *
     * @return Value of year stored in this object.
     */
    public int getYear() {
        return this.year;
    }


    /**
     * Getter method return value of month in this Date object.
     *
     * @return Value of month stored in this object.
     */
    public int getMonth() {
        return this.month;
    }


    /**
     * Getter method return value of day in this Date object.
     *
     * @return Value of day stored in this object.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Override method to compare Date object.
     *
     * @param date A Date object for comparison.
     * @return The result of the comparing between two Date objects, -1 for smaller in calendar, 1 for bigger in calendar, 0 for equal.
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year) {
            return 1;
        } else if (this.year < date.year) {
            return -1;
        } else {
            if (this.month > date.month) {
                return 1;
            } else if (this.month < date.month) {
                return -1;
            } else {
                if (this.day > date.day) {
                    return 1;
                } else if (this.day < date.day) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Override method to return a String for a Date object.
     *
     * @return Return the textual representation of a Date object MM/DD/YYYY.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%d/%d/%d", month, day, year);
        return textualRep;
    }

    /**
     * Change the date in a Date object is a valid calendar date.
     *
     * @return Return true for valid calendar date and false invalid calendar date.
     */
    public boolean isValid() {
        if (month >= Calendar.JANUARY + 1 && month <= Calendar.DECEMBER + 1) {
            if (isLeapYear() == true) {
                if (day <= MOUTH_DAYS_LEAPYEAR[month] && day >= LESS_DAY_IN_A_MONTH) {
                    if (year > 1900) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (day <= MOUTH_DAYS[month] && day >= LESS_DAY_IN_A_MONTH) {
                    if (year > 1900) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Helper method that check a year is a leap year or not.
     *
     * @return True for is a leap year and false for not a leap year.
     */
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to check if the given year is a leap year.
     * @param year Year value.
     * @return true if it is a leap year, false it is not.
     */
    public boolean checkLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Helper method to extend the membership from today to 3 month later and change the date accordingly.
     *
     * @param expire date of today
     * @return True expiration date.
     */
    public Date increaseDateStandard(Date expire) {
        int extendMonth = 3;
        int newMonth = expire.getMonth() + extendMonth;
        if (newMonth > 12) {
            expire.setNewYear(expire.getYear() + 1);
            newMonth = newMonth - 12;
            if (newMonth == 2) {
                if (expire.checkLeapYear(expire.getYear())) {
                    setUpNewDate(expire, 29, newMonth);
                } else {
                    setUpNewDate(expire, 28, newMonth);
                }
            } else {
                if (newMonth == 1 || newMonth == 3 || newMonth == 5 || newMonth == 7 || newMonth == 8 || newMonth == 10 || newMonth == 12) {
                    setUpNewDate(expire, 31, newMonth);
                } else {
                    setUpNewDate(expire, 30, newMonth);
                }
            }
        }
        return expire;
    }

    /**
     * Helper method to change month value according to the change of new day value.
     *
     * @param expire   date of expiration.
     * @param MaxDay   max day of that month.
     * @param newMonth new month value for the Date.
     */
    private void setUpNewDate(Date expire, int MaxDay, int newMonth) {
        if (expire.getDay() < MaxDay) {
            expire.setNewMonth(newMonth);
        } else {
            int newDay = expire.getDay() - MaxDay;
            newMonth = newMonth + 1;
            expire.setNewMonth(newMonth);
            expire.setNewDay(newDay);
        }
    }

    /**
     * Helper method to convert specific given date string convert it into another format
     * @param dateString date string with YYYY-MM-DD
     * @return new date string with DD/MM/YYYY
     */
    public static String formatDate(String dateString){
        String[] dateContainer = dateString.split("-", 3);
        return dateContainer[1] + "/" + dateContainer[2] + "/" + dateContainer[0];
    }

    /**
     * Testbed Main to exercise the isValid method.
     */
    public static void main(String[] args) {
        System.out.println();

        Date date = new Date("3/32/2003");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: A date cannot have 32 days in a month.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("1/-1/2022");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #2: A date cannot have -1 days in a month.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/29/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #3: February cannot have 29 days in normal year.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #4: A date with a invalid month value.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("-1/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #5: A date with month value in negative.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/29/2000");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #6: February can have 29 days in leap year.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("1/2/-2000");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #7: A date with year value in negative.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("4/31/2022");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #8: April cannot have 31 days.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/28/2017");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #9: Non-leap can only have 28 days in February.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/30/2011");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #10: February cannot have 30 days.");
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Helper method for testbed main.
     *
     * @param date           Date test cases
     * @param expectedOutput True or false.
     * @param actualOutput   Actual output from test, ture or false.
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println(date.toString());
        System.out.print("isValid() returns " + actualOutput);
        if (actualOutput == expectedOutput) {
            System.out.println(", PASS.\n");
        } else {
            System.out.println(", FAIL.\n");
        }
    }
}
