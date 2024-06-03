package com.example.project3;

/**
 * Subclass of Member.
 * More feature added, for example guest pass.
 *
 * @author Sihua Zhou
 */
public class Family extends Member {
    /**
     * Number of the Guest Pass this member has.
     */
    private int Number_Of_Guest_Pass;
    /**
     * Constant for number guest pass of a family membership member can have.
     */
    private final int FAMILY_PASS_NUMBER = 1;
    /**
     * Monthly fee for family membership.
     */
    private final double MONTHLY_FEE = 59.99;
    /**
     * One time fee for family membership.
     */
    private final double ONE_TIME_FEE = 29.99;

    /**
     * Constructor Family to create an instance of Family.
     *
     * @param fname    Member's firsy name.
     * @param lname    Member's lsat name.
     * @param dob      Member's date of birth.
     * @param expire   Member's membership expiration day.
     * @param location Membership location.
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);
        this.Number_Of_Guest_Pass = FAMILY_PASS_NUMBER;
    }

    /**
     * Setter method for a new quantity the member has for his/her guest pass.
     *
     * @param newQuantity New quantity for the guest pass.
     */
    public void setNumber_Of_Guest_Pass(int newQuantity) {
        this.Number_Of_Guest_Pass = newQuantity;
    }

    /**
     * Getter method for access the number of the guest pass this member has.
     *
     * @return number of guest passes left.
     */
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
        String textualRep = String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Family) guest-pass remaining: %d",
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
        double fee = 3 * MONTHLY_FEE + ONE_TIME_FEE;
        return fee;
    }

}
