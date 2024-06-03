package com.example.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MemberDatabase class contain methods to generate member list and perform memberlist operation.
 * This class generate member list as well as add/remove member from list and print the list in variety way.
 * @author Sihua Zhou
 */
public class MemberDatabase {
    /**
     * Member class array contains member list.
     */
    private Member[] mlist;
    /**
     * Size of the member list.
     */
    private int size;

    /**
     * Constant value for not found.
     */
    private static final int NOT_FOUND = -1;
    /**
     * Increase size by this constant every time when the list is full.
     */
    private static final int INCREASE_ARRAY_SIZE = 4;

    /**
     * Index of operation in the input String array.
     */
    public static final int OPERATION = 0;
    /**
     * Index of first name in the input String array. Adding or Removing member.
     */
    public static final int FIRST_NAME = 1;
    /**
     * Index of last name in the input String array. Adding or Removing member.
     */
    public static final int LAST_NAME = 2;
    /**
     * Index of date of birth in the input String array. Adding or Removing member.
     */
    public static final int DATE_OF_BIRTH = 3;
    /**
     * Index of location in the input String array. Adding or Removing member.
     */
    public static final int LOCATION = 4;

    /**
     * MemberDatabase constructor that generate an object of MemberDatabase.
     */
    public MemberDatabase() {
        size = 4;
        mlist = new Member[size];
    }

    /**
     * Method to find if a member is in the member list.
     * @param member Member information for checking it is found or not found.
     * @return Index of the member list if member exist, -1 for not found.
     */
    private int find(Member member) {
        for (int index = 0; index < mlist.length; index++) {
            if (mlist[index] == null) {
                break;
            }
            if (mlist[index].equals(member) == true) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * The method to grow the member list whenever it is full.
     */
    private void grow() {
        int newArrayLength = mlist.length + INCREASE_ARRAY_SIZE;
        Member[] newList = new Member[newArrayLength];

        for (int index = 0; index < mlist.length; index++) {
            newList[index] = mlist[index];
        }

        mlist = newList;
    }

    /**
     * The method to add a valid member.
     * @param member Member information
     * @return True for successfully added, false for member already exist.
     */
    public boolean add(Member member) {
        int arrayIndex = find(member);
        int lastArrayIndex = mlist.length - 1;

        if (arrayIndex == NOT_FOUND) {
            if (mlist[lastArrayIndex] == null) {
                for (int index = 0; index < mlist.length; index++) {
                    if (mlist[index] == null) {
                        mlist[index] = member;
                        break;
                    }
                }
                return true;

            } else {
                grow();
                for (int index = 0; index < mlist.length; index++) {
                    if (mlist[index] == null) {
                        mlist[index] = member;
                        break;
                    }
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Helper method to add a guest in the list.
     * @param member Member infomation.
     */
    public void addGuest(Member member) {
        int lastArrayIndex = mlist.length - 1;
        if (mlist[lastArrayIndex] == null) {
            for (int index = 0; index < mlist.length; index++) {
                if (mlist[index] == null) {
                    mlist[index] = member;
                    break;
                }
            }
        } else {
            grow();
            for (int index = 0; index < mlist.length; index++) {
                if (mlist[index] == null) {
                    mlist[index] = member;
                    break;
                }
            }
        }
    }

    /**
     * The method is for removing a valid member.
     * @param member Member information.
     * @return True for successfully removed, false for member not in the database.
     */
    public boolean remove(Member member) {
        int arrayIndex = find(member);
        int lastArrayIndex = mlist.length - 1;

        if (arrayIndex != NOT_FOUND) {
            if (arrayIndex == lastArrayIndex) {
                mlist[arrayIndex] = null;
                return true;
            } else {
                while (arrayIndex != lastArrayIndex) {
                    mlist[arrayIndex] = mlist[arrayIndex + 1];
                    arrayIndex++;
                }
                mlist[lastArrayIndex] = null;
                return true;
            }
        }

        return false;
    }

    /**
     * The method is for print the array contents as is.
     * @return The list of members for display
     */
    public String print() {
        String str = "";
        if (mlist[0] != null) {
            str = str + "\n";
            str = str + "-list of members-\n";
            for (int index = 0; index < mlist.length; index++) {
                if (mlist[index] != null) {
                    str = str + mlist[index].toString() + "\n";
                } else {
                    break;
                }
            }
            str = str + "-end of list-\n";
            str = str + "\n";
        } else {
            str = str + "Member database is empty!\n";
        }
        return str;
    }

    /**
     * The method is for other class to access for printing the class member array as is.
     * @return The list of participants or guest
     */
    public String printClassMember() {
        String str = "";
        for (int index = 0; index < mlist.length; index++) {
            if (mlist[index] != null) {
                str = str + "  " + mlist[index].toString() + "\n";
            } else {
                break;
            }
        }
        return str;
    }

    /**
     * The method is for other class to access for printing the member list array as is.
     * @return The list of members
     */
    public String printList() {
        String str = "";
        for (int index = 0; index < mlist.length; index++) {
            if (mlist[index] != null) {
                str = str + mlist[index].toString() + "\n";
            } else {
                break;
            }
        }
        return str;
    }

    /**
     * Return the member list of the member database.
     * @return Member list.
     */
    public Member[] getMemberList() {
        return this.mlist;
    }

    /**
     * This method is to check the member whether it is in the member database.
     * @param member Member information
     * @return True for in the member database, false for not.
     */
    public boolean checkMembership(Member member) {
        int memberIndex = find(member);
        if (memberIndex != NOT_FOUND) {
            return true;
        }
        return false;
    }

    /**
     * This method is to check a member's membership is expired or not.
     * @param member Member information.
     * @return True for not expired, false for expired.
     */
    public boolean checkExpiration(Member member) {
        int memberIndex = find(member);
        Date today = new Date();
        if (mlist[memberIndex].getExpire().compareTo(today) < 0) {
            return false;
        }
        return true;
    }

    /**
     * To get a member's index in the member database.
     * @param member Member information.
     * @return Member index in the member.
     */
    public int getMemberIndex(Member member) {
        return find(member);
    }

    /**
     * The method is to sort by county and then zipcode, and print the member list.
     * @return The list of member in county and then zipcode order.
     */
    public String printByCounty() {
        String str = "";
        if (mlist[0] != null) {
            str = str + "\n";
            str = str + "-list of members sorted by county and zipcode-\n";
            sortByCounty();
            sortByZipcode();
            str = str + printList();
            str = str + "-end of list-\n";
            str = str + "\n";
        } else {
            str = str + "Member database is empty!\n";
        }
        return str;
    }


    /**
     * Helper method for printByCounty to sort list by county.
     */
    private void sortByCounty() {
        //insertion sort
        for (int index = 1; index < mlist.length; index++) {
            if (mlist[index] == null) {
                break;
            }
            int indexHolder = index;
            // if county is the same it needs to print in
            while (mlist[indexHolder].getLocation().getCountyName().compareTo(mlist[indexHolder - 1].getLocation().getCountyName()) < 0) {
                Member holder = mlist[indexHolder - 1];
                mlist[indexHolder - 1] = mlist[indexHolder];
                mlist[indexHolder] = holder;
                indexHolder--;
                if (indexHolder < 1) {
                    break;
                }
            }
        }
    }

    /**
     * Helper method for printByCounty to sort list by zipcode.
     */
    private void sortByZipcode() {
        int startingIndex = 0;
        int endingIndex = 0;
        while (startingIndex != mlist.length) {
            if (mlist[endingIndex] == null) {
                break;
            }
            if (mlist[endingIndex].getLocation().getCountyName().compareTo(mlist[endingIndex + 1].getLocation().getCountyName()) != 0) {
                for (int index = startingIndex + 1; index < endingIndex + 1; index++) {
                    if (mlist[index] == null) {
                        break;
                    }
                    int indexHolder = index;
                    while (mlist[indexHolder].getLocation().getZipcode().compareTo(mlist[indexHolder - 1].getLocation().getZipcode()) < 0) {
                        Member holder = mlist[indexHolder - 1];
                        mlist[indexHolder - 1] = mlist[indexHolder];
                        mlist[indexHolder] = holder;
                        indexHolder--;
                        if (indexHolder < startingIndex + 1) {
                            startingIndex = endingIndex + 1;
                            break;
                        }
                    }
                }
                break;
            }
            endingIndex++;
        }
    }

    /**
     * The method is for sort the member list by the expiration date.
     * @return The list of member in expiration date.
     */
    public String printByExpirationDate() {
        String str = "";
        if (mlist[0] != null) {
            str = str + "\n";
            str = str + "-list of members sorted by membership expiration date-\n";

            for (int index = 1; index < mlist.length; index++) {
                if (mlist[index] == null) {
                    break;
                }
                int indexHolder = index;
                while (mlist[indexHolder].getExpire().compareTo(mlist[indexHolder - 1].getExpire()) < 0) {
                    Member holder = mlist[indexHolder - 1];
                    mlist[indexHolder - 1] = mlist[indexHolder];
                    mlist[indexHolder] = holder;
                    indexHolder--;
                    if (indexHolder < 1) {
                        break;
                    }
                }
            }

            str = str + printList();
            str = str + "-end of list-\n";
            str = str + "\n";
        } else {
            str = str + "Member database is empty!\n";
        }
        return str;
    }

    /**
     * The method is for sort the member list by last name and then first name.
     * @return The list of member in last name and then first name order.
     */
    public String printByName() {
        String str = "";
        if (mlist[0] != null) {
            str = str + "\n";
            str = str + "-list of members sorted by last name, and first name-\n";

            for (int index = 1; index < mlist.length; index++) {
                if (mlist[index] == null) {
                    break;
                }
                int indexHolder = index;
                while (mlist[indexHolder].compareTo(mlist[indexHolder - 1]) < 0) {
                    Member holder = mlist[indexHolder - 1];
                    mlist[indexHolder - 1] = mlist[indexHolder];
                    mlist[indexHolder] = holder;
                    indexHolder--;
                    if (indexHolder < 1) {
                        break;
                    }
                }
            }

            str = str + printList();
            str = str + "-end of list-\n";
            str = str + "\n";
        } else {
            str = str + "Member database is empty!\n";
        }
        return str;
    }

    /**
     * The method is for print out the current member list in the database with their correspond fee.
     * @return The list of member with corresponding fee
     */
    public String printWithFee() {
        String str = "";
        if (mlist[0] != null) {
            str = str + "\n";
            str = str + "-list of members with membership fees-\n";
            for (int index = 0; index < mlist.length; index++) {
                if (mlist[index] != null) {
                    str = str + mlist[index].toString() + ", " + mlist[index].feeToString() + "\n";
                } else {
                    break;
                }
            }
            str = str + "-end of list-\n";
            str = str + "\n";
        } else {
            str = str + "Member database is empty!\n";
        }
        return str;
    }

    /**
     * Information checking for adding a valid member into the database.
     * @param md   Member list in the Member database.
     * @param info Line information from console that separate and putted to into a String.
     * @return status of the member is added or not
     */
    public String addMember(MemberDatabase md, String[] info) {
        Date DOB = new Date(info[DATE_OF_BIRTH]);
        Date today = new Date();

        if (DOB.isValid() == true) {
            if (DOB.compareTo(today) >= 0) {
                return "DOB " + info[DATE_OF_BIRTH] + ": cannot be today or a future date!";
            } else {

                Date eighteen = new Date(info[DATE_OF_BIRTH]);
                eighteen.setNewYear(DOB.getYear() + 18);

                if (eighteen.compareTo(today) <= 0) {
                    return codeACheckValidCity(md, DOB, today, info);
                } else {
                    return "DOB " + info[DATE_OF_BIRTH] + ": must be 18 or older to join!";
                }
            }

        } else {
            return "DOB " + info[DATE_OF_BIRTH] + ": invalid calendar date!";
        }
    }

    /**
     * Helper method that helps addMember method check the location of a gym is valid or not and print the status of that member.
     * @param md     Member list in the Member database.
     * @param DOB    Member's date of birth.
     * @param info   Line information from console that separate and putted to into a String.
     * @return status of the member is added or not
     */
    private String codeACheckValidCity(MemberDatabase md, Date DOB, Date today, String[] info) {
        Location locHolder = null;
        boolean validCity = false;
        Location[] locs = Location.values();
        for (Location loc : locs) {
            if (loc.getLoction().equalsIgnoreCase(info[LOCATION]) == true) {
                locHolder = loc;
                validCity = true;
            }
        }
        if (validCity == true) {
            return AddMemberAccordingly(md, DOB, locHolder, today, info);
        } else {
            return info[LOCATION] + ": invalid location!";
        }
    }

    /**
     * Helper method of codeACheckValidCity to add member with different membership.
     * @param md        Member list in the Member database.
     * @param DOB       Member's date of birth.
     * @param locHolder Gym location of the membership.
     * @param today     date of today.
     * @param info      Line information from console that separate and putted to into a String.
     * @return status of the member is added or not
     */
    private String AddMemberAccordingly(MemberDatabase md, Date DOB, Location locHolder, Date today, String[] info) {
        if (info[OPERATION].equals("A")) {
            Date Expire = today.increaseDateStandard(today);
            if (md.add(new Member(info[FIRST_NAME], info[LAST_NAME], DOB, Expire, locHolder))) {
                String name = String.format("%s %s added.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            } else {
                String name = String.format("%s %s is already in the database.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            }
        } else if (info[OPERATION].equals("AF")) {
            Date Expire = today.increaseDateStandard(today);
            if (md.add(new Family(info[FIRST_NAME], info[LAST_NAME], DOB, Expire, locHolder))) {
                String name = String.format("%s %s added.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            } else {
                String name = String.format("%s %s is already in the database.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            }
        } else {
            int newYearValue = today.getYear() + 1;
            today.setNewYear(newYearValue);
            Date Expire = today;
            if (md.add(new Premium(info[FIRST_NAME], info[LAST_NAME], DOB, Expire, locHolder))) {
                String name = String.format("%s %s added.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            } else {
                String name = String.format("%s %s is already in the database.", info[FIRST_NAME], info[LAST_NAME]);
                return name;
            }
        }
    }

    /**
     * Information checking for removing a valid member from database.
     * @param md   Member list in the Member database.
     * @param info Line information from console that separate and putted to into a String.
     * @return status of the member is removed or not
     */
    public String removeMember(MemberDatabase md, String[] info) {
        Date DOB = new Date(info[DATE_OF_BIRTH]);
        Member temp = new Member(info[FIRST_NAME], info[LAST_NAME], DOB, null, null);
        if (md.remove(temp)) {
            String name = String.format("%s %s removed", info[FIRST_NAME], info[LAST_NAME]);
            return name;
        } else {
            String name = String.format("%s %s is not in the database.", info[FIRST_NAME], info[LAST_NAME]);
            return name;
        }
    }

    /**
     * Helper method to load old member from a file.
     * @param md Member database.
     * @return The list of the loaded old member
     */
    public static String loadMemberList(MemberDatabase md) {
        try {
            Scanner oldMemberList = new Scanner(new File("memberList.txt"));
            String str = "";
            int firstName = 0;
            int lastName = 1;
            int DOB = 2;
            int expire = 3;
            int location = 4;
            Member newMember;
            Date dob;
            Date expireDate;

            while (oldMemberList.hasNext()) {
                String[] information = oldMemberList.nextLine().split("\\s+", 6);
                dob = new Date(information[DOB]);
                expireDate = new Date(information[expire]);
                int locIndex = checkAndGetLocation(information[location]);
                newMember = new Member(information[firstName], information[lastName], dob, expireDate, Location.values()[locIndex]);
                md.add(newMember);
            }
            str = str + "\n";
            str = str + "-list of members loaded-\n";
            str = str + md.printList();
            str = str + "-end of list-\n";
            str = str + "\n";
            oldMemberList.close();
            return str;
        } catch (FileNotFoundException f) {
            return "file not found.";
        }
    }

    /**
     * Private helper method to get gym location index in Location enum class.
     * @param locationInput Gym location.
     * @return gym location index in Location enum class.
     */
    private static int checkAndGetLocation(String locationInput) {
        for (int index = 0; index < Location.values().length; index++) {
            if (locationInput.equalsIgnoreCase(Location.values()[index].getLoction())) {
                return index;
            }
        }
        return NOT_FOUND;
    }
}
