package com.example.project3;

/**
 * Member is for generate member object and storing the member information.
 * Member operation can be performed and some information can be provided.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public class Member implements Comparable<Member> {
    /**
     * First name.
     */
    private String fname;
    /**
     * Last name.
     */
    private String lname;
    /**
     * Date of birth.
     */
    private Date dob;
    /**
     * Membership expiration date.
     */
    private Date expire;
    /**
     * Gym Location.
     */
    private Location location;

    /**
     * Monthly fee for standard membership.
     */
    private static final double MONTHLY_FEE = 39.99;
    /**
     * One time fee for standard membership.
     */
    private static final double ONE_TIME_FEE = 29.99;

    /**
     * Member class constructor for generate the member object.
     *
     * @param fname    First name.
     * @param lname    Last name.
     * @param dob      Date of birth.
     * @param expire   Membership expiration date.
     * @param location Gym Location.
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     * Override toString method to create a String for member information.
     *
     * @return Member information in textual representation.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%s %s, DOB: %s, Membership expires %s, Location: %s", fname, lname, dob.toString(), expire.toString(), location.toString());
        return textualRep;
    }

    /**
     * Getter method for access the member's first name.
     *
     * @return member's first name.
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Getter method for access the member's last name.
     *
     * @return member's last name.
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Getter method to access the member's date of birth.
     *
     * @return member's data of birth.
     */
    public Date getDOB() {
        return dob;
    }

    /**
     * Return a member's membership expiration date.
     *
     * @return Membership expiration date.
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * Return the gym location.
     *
     * @return Location enum class type of gym location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Method for accessing the membership fee of the member.
     *
     * @return Due of the membership.
     */
    public double membershipFee() {
        double fee = 3 * MONTHLY_FEE + ONE_TIME_FEE;
        return fee;
    }

    /**
     * Form a textual representation of the membership fee the member should pay.
     *
     * @return Textual representation of the membership fee the member should pay.
     */
    public String feeToString() {
        String textualRep = String.format("Membership: $%,.2f", membershipFee());
        return textualRep;
    }

    /**
     * Override equals methods to check two members is the same one.
     *
     * @return True for is the same, false for not the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member member = (Member) obj;
            if (member.fname.equalsIgnoreCase(this.fname) && member.lname.equalsIgnoreCase(this.lname)
                    && member.dob.toString().equalsIgnoreCase(this.dob.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Override compareTo method to compare to member's first name and then last name for alphabetical order.
     *
     * @return 1 for alphabetically bigger, -1 for alphabetical smaller, 0 for alphabetically equal.
     */
    @Override
    public int compareTo(Member member) {
        int greater = 1;
        int less = -1;
        int equal = 0;

        if (this.lname.compareToIgnoreCase(member.lname) > 0) {
            return greater;
        } else if (this.lname.compareToIgnoreCase(member.lname) < 0) {
            return less;
        } else {
            if (this.fname.compareToIgnoreCase(member.fname) > 0) {
                return greater;
            } else if (this.fname.compareToIgnoreCase(member.fname) < 0) {
                return less;
            } else {
                return equal;
            }
        }
    }

    /**
     * Testbed Main to exercise the compareTo method.
     */
    public static void main(String[] args) {
        int greater = 1;
        int less = -1;
        int equal = 0;

        System.out.println();
        Date date = new Date("1/20/2003");
        Member member1 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        Member member2 = new Member("john", "doe", date, date, Location.BRIDGEWATER);
        int expectedOutput = equal;
        int actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #1: Name should not be case-sensitive.");
        testResult(member1, member2, expectedOutput, actualOutput);

        member1 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        member2 = new Member("Duke", "Ellington", date, date, Location.BRIDGEWATER);
        expectedOutput = less;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #2: Compare to find the alphabetical order that first one is smaller than second one.");
        testResult(member1, member2, expectedOutput, actualOutput);

        member1 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        member2 = new Member("Carl", "Brown", date, date, Location.BRIDGEWATER);
        expectedOutput = greater;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #3: Compare to find the alphabetical order that first one is greater than second one.");
        testResult(member1, member2, expectedOutput, actualOutput);

        member1 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        member2 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        expectedOutput = equal;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #4: Same name should be the same.");
        testResult(member1, member2, expectedOutput, actualOutput);

        member1 = new Member("John", "Doe", date, date, Location.BRIDGEWATER);
        member2 = new Member("Jane", "Doe", date, date, Location.BRIDGEWATER);
        expectedOutput = greater;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #5: Same last name, so compare first name that the alphabetical order of the first one is great than second one.");
        testResult(member1, member2, expectedOutput, actualOutput);
    }

    /**
     * Helper method for testbed main.
     *
     * @param member1        First member information.
     * @param member2        Second member information.
     * @param expectedOutput Expected output.
     * @param actualOutput   Actual output.
     */
    private static void testResult(Member member1, Member member2, int expectedOutput, int actualOutput) {
        System.out.println(member1.toString());
        System.out.println(member2.toString());
        System.out.print("compareTo() returns " + actualOutput);
        if (actualOutput == expectedOutput) {
            System.out.println(", PASS.\n");
        } else {
            System.out.println(", FAIL.\n");
        }
    }
}
