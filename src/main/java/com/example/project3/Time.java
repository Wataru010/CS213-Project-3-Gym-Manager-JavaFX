package com.example.project3;

/**
 * Fitness class time information is stored in this class.
 * Information for time can be provided from this enum class.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public enum Time {
    /**
     * Morning class time.
     */
    MORNING("9", "30"),
    /**
     * Afternoon class time.
     */
    AFTERNOON("14", "00"),
    /**
     * Evening class time.
     */
    EVENING("18", "30");

    /**
     * Hour value.
     */
    private final String hour;
    /**
     * Minutes value.
     */
    private final String minutes;

    /**
     * Time enum class constructor.
     *
     * @param hour    Hour value.
     * @param minutes Minute value.
     */
    Time(String hour, String minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    /**
     * Get information of time range for other class's access.
     *
     * @return Time range.
     */
    public String getTimeRange() {
        return super.toString();
    }

    /**
     * Override method to return a String of time of the fitness class.
     *
     * @return Textual representation of the time.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%s:%s", hour, minutes);
        return textualRep;
    }
}
