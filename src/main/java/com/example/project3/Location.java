package com.example.project3;

/**
 * Location information is stored in this enum class.
 * This class include methods to provide location information, such as county and zipcode.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public enum Location {
    /**
     * Information for Bridgewater gym.
     */
    BRIDGEWATER("08807", "SOMERSET"),
    /**
     * Information for Edison gym.
     */
    EDISON("08837", "MIDDLESEX"),
    /**
     * Information for Franklin gym.
     */
    FRANKLIN("08873", "SOMERSET"),
    /**
     * Information for Piscataway gym.
     */
    PISCATAWAY("08854", "MIDDLESEX"),
    /**
     * Information for Somerset gym.
     */
    SOMERVILLE("08876", "SOMERSET");

    /**
     * Zipcode information.
     */
    private final String zipcode;
    /**
     * County Name.
     */
    private final String countyName;

    /**
     * Location enum class constructors.
     *
     * @param zipcode    Zipcode information.
     * @param countyName County Name.
     */
    Location(String zipcode, String countyName) {
        this.zipcode = zipcode;
        this.countyName = countyName;
    }

    /**
     * Return the gym location.
     *
     * @return Name of the gym location.
     */
    public String getLoction() {
        return super.toString();
    }

    /**
     * Return the county name.
     *
     * @return County Name.
     */
    public String getCountyName() {
        return this.countyName;
    }

    /**
     * Return zipcode.
     *
     * @return Zipcode.
     */
    public String getZipcode() {
        return this.zipcode;
    }

    /**
     * Helper method to find the index of gym location in Location enum class.
     * @param gymLocation name of the gym location.
     * @return index of the gym location in Location enum class.
     */
    public static int findLocation(String gymLocation) {
        for (int index = 0; index < Location.values().length; index++) {
            if (Location.values()[index].getLoction().equalsIgnoreCase(gymLocation)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Return the location information in String format.
     *
     * @return Textual representation of the location.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%s, %s, %s", super.toString(), zipcode, countyName);
        return textualRep;
    }
}
