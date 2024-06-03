package com.example.project3;

/**
 * Subclass of Family,
 * Methods are override in this class.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public class Premium extends Family {
    /**
     * Number of the Guest Pass this member has.
     */
    private int Number_Of_Guest_Pass;

    /**
     * Constant for number guest pass of a premium membership member can have.
     */
    private final int PREMIUM_PASS_NUMBER = 3;
    /**
     * Monthly fee for premium membership.
     */
    private final double MONTHLY_FEE = 59.99;
    /**
     * One time fee for premium membership.
     */
    private final double ONE_TIME_FEE = 0;

    /**
     * Constructor Premiuum to create an instance of Premium.
     *
     * @param fname    Member's firsy name.
     * @param lname    Member's lsat name.
     * @param dob      Member's date of birth.
     * @param expire   Member's membership expiration day.
     * @param location Membership location.
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        this.Number_Of_Guest_Pass = PREMIUM_PASS_NUMBER;
    }

    /**
     * Setter method for a new quantity the member has for his/her guest pass.
     *
     * @param newQuantity New quantity for the guest pass.
     */
    @Override
    public void setNumber_Of_Guest_Pass(int newQuantity) {
        this.Number_Of_Guest_Pass = newQuantity;
    }

    /**
     * Getter method for access the number of the guest pass this member has.
     *
     * @return number of guest passes left.
     */
    @Override
    public int getNumber_Of_Guest_Pass() {
        return Number_Of_Guest_Pass;
    }

    /**
     * Form the textual Representation of this member's information.
     *
     * @return textual Representation of this member's information.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Premium) guest-pass remaining: %d",
                super.getFirstName(), super.getLastName(), super.getDOB().toString(), super.getExpire().toString(), super.getLocation().toString(), Number_Of_Guest_Pass);
        return textualRep;
    }

    /**
     * Method for accessing the membership fee of the member.
     *
     * @return Due of the membership.
     */
    @Override
    public double membershipFee() {
        double fee = 11 * MONTHLY_FEE + ONE_TIME_FEE;
        return fee;
    }
}
