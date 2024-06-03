package com.example.project3;

/**
 * FitnessClass class can deal with multiple functions for member list opearation.
 * This class include printing the class schedule along with member list and class member list operations.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public class FitnessClass {
    /**
     * Name of this fitness class.
     */
    private String className;
    /**
     * Name of the Instructor who teaches this class.
     */
    private String Instructor;
    /**
     * Time range of this class.
     */
    private String timeRange;
    /**
     * Gym location of this class.
     */
    private String location;

    /**
     * List of the participants in this class.
     */
    private MemberDatabase participants;
    /**
     * List of the guests in this class.
     */
    private MemberDatabase guests;

    /**
     * Index of operation in the input String array.
     */
    public static final int OPERATION = 0;
    /**
     * Index of class name in the input String array. Check in/drop class.
     */
    public static final int CLASS_NAME = 1;
    /**
     * Index of instructor name in the input String array. Check in/drop class.
     */
    public static final int INSTRUCTOR_NAME = 2;
    /**
     * Index of gym location in the input String array. Check in/drop class.
     */
    public static final int GYM_LOCATION = 3;
    /**
     * Index of first name in the input String array. Check in/drop class.
     */
    public static final int CLASS_FIRST_NAME = 4;
    /**
     * Index of last name in the input String array. Check in/drop class.
     */
    public static final int CLASS_LAST_NAME = 5;
    /**
     * Index of date of birth in the input String array. Check in/drop class.
     */
    public static final int CLASS_DOB = 6;
    /**
     * Constant for representing member not found.
     */
    private static final int NOT_FOUND = -1;

    /**
     * Empty constructor for running the method in this class.
     */
    public FitnessClass() {
    }

    /**
     * Overloaded constructor to create an instance of FitnessClass.
     * @param className   Name of the class.
     * @param Instructor  Name of the instructor who teaches this class.
     * @param timeRange   Time range of this class.
     * @param location location of this class.
     */
    public FitnessClass(String className, String Instructor, String timeRange, String location) {
        this.className = className.toUpperCase();
        this.Instructor = Instructor.toUpperCase();
        this.timeRange = timeRange.toUpperCase();
        this.location = location.toUpperCase();

        participants = new MemberDatabase();
        guests = new MemberDatabase();
    }

    /**
     * Helper method to covert timeRange to actual time
     * @param timeRange Time range of the class.
     * @return Actual time of the class.
     */
    private String timeRangeTotime(String timeRange) {
        for (int index = 0; index < Time.values().length; index++) {
            if (timeRange.equalsIgnoreCase(Time.values()[index].getTimeRange())) {
                return Time.values()[index].toString();
            }
        }
        return "no such time";
    }

    /**
     * Getter method for access the name of the class.
     * @return Name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Getter method for access the instructor's name.
     * @return instructor's name
     */
    public String getInstructor() {
        return Instructor;
    }

    /**
     * Getter method for access the time range of the class.
     * @return Time range of the class.
     */
    public String getTimeRange() {
        return timeRange;
    }

    /**
     * Method for other classes to get the actual time of the class.
     * @param timeRange Time range of the class.
     * @return Actual time of the class.
     */
    public String getTime(String timeRange) {
        return timeRangeTotime(timeRange);
    }

    /**
     * Getter method for access the gym location of the class.
     * @return Gym location of the class.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter method for access the list of partcipants in this class.
     * @return List of participants of this class.
     */
    public MemberDatabase getParticipants() {
        return participants;
    }

    /**
     * Getter method for access the list of guests in this class.
     * @return List of guests of this class.
     */
    public MemberDatabase getGuests() {
        return guests;
    }

    /**
     * Form the textual representation of the fitness class.
     * @return Textual representation of the fitness class.
     */
    @Override
    public String toString() {
        String textualRep = String.format("%s - %s, %s, %s", className, Instructor, timeRangeTotime(timeRange), location);
        return textualRep;
    }

    /**
     * Helper method to print the list of participants in this class.
     * @return List of the participants in a class.
     */
    public String printPartcipants() {
        String str = "";
        str = str + participants.printClassMember();
        return str;
    }

    /**
     * Helper method to print the list of guests in this class.
     * @return List of the guest in a class.
     */
    public String printGuests() {
        String str = "";
        str = str + guests.printClassMember();
        return str;
    }

    /**
     * Helper method to print the list of the class after adding a member.
     * @param cs         Class schedule
     * @param classIndex index of the class in the list of class schedule
     * @return The list of both participants and guests in a class.
     */
    public String printClassList(ClassSchedule cs, int classIndex) {
        String str = "";
        if (cs.getFitnessClasses()[classIndex].getParticipants().getMemberList()[0] != null) {
            str = str + "- Participants -\n";
            str = str + cs.getFitnessClasses()[classIndex].printPartcipants();
        }
        if (cs.getFitnessClasses()[classIndex].getGuests().getMemberList()[0] != null) {
            str = str + "- Guests -\n";
            str = str + cs.getFitnessClasses()[classIndex].printGuests();
        }
        return str;
    }

    /**
     * Method for check in a member/guest according to different situation.
     *
     * @param cs   Class schedule.
     * @param md   Member database.
     * @param info Line information from console that separate and putted to into a String.
     * @return status of the member/guest.
     */
    public String checkInMember(ClassSchedule cs, MemberDatabase md, String[] info) {
        return ClassInfoCheck(cs, md, info);
    }

    /**
     * Helper method to check class information
     *
     * @param cs   Class schedule.
     * @param md   Member database.
     * @param info Line information from console that separate and putted to into a String.
     * @return status of the member.
     */
    private String ClassInfoCheck(ClassSchedule cs, MemberDatabase md, String[] info) {
        Date dob = new Date(info[CLASS_DOB]);
        Member tempMember = new Member(info[CLASS_FIRST_NAME], info[CLASS_LAST_NAME], dob, null, null);

        if (cs.checkClassName(info[CLASS_NAME]) && cs.checkInstructor(info[INSTRUCTOR_NAME]) && cs.checkLocation(info[GYM_LOCATION])) {
            return checkInputInfo(cs, md, info, tempMember);
        } else {
            if (cs.checkClassName(info[CLASS_NAME]) == false) {
                // class no exist
                return info[CLASS_NAME] + " - class does not exist.\n";
            } else if (cs.checkInstructor(info[INSTRUCTOR_NAME]) == false) {
                // instructor not exist
                return info[INSTRUCTOR_NAME] + " - instructor does not exist.\n";
            } else {
                // invalid location
                return info[GYM_LOCATION] + " - invalid location.\n";
            }
        }
    }

    /**
     * Helper method to check the member information.
     *
     * @param cs         Class schedule.
     * @param md         Member database.
     * @param info       Line information from console that separate and putted to into a String.
     * @param tempMember Temporary instance of Member for match real member in the database.
     * @return status of the member.
     */
    private String checkInputInfo(ClassSchedule cs, MemberDatabase md, String[] info, Member tempMember) {
        Date dob = new Date(info[CLASS_DOB]);
        if (dob.isValid()) {
            if (md.checkMembership(tempMember)) {
                if (md.checkExpiration(tempMember)) {
                    int memberIndex = md.getMemberIndex(tempMember);
                    if (info[OPERATION].equals("C")) {
                        return checkMembershipRestrictionC(cs, md, memberIndex, info);
                    } else if (info[OPERATION].equals("CG")) {
                        return checkMembershipRestrictionCG(cs, md, memberIndex, info);
                    } else if (info[OPERATION].equals("D")) {
                        return classMembershipCheckD(cs, md, memberIndex, info);
                    } else {
                        return classMembershipCheckDG(cs, md, memberIndex, info);
                    }
                } else {
                    // Membership expired
                    return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " " + dob.toString() + " membership expired.\n";
                }
            } else {
                // Not in database
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " " + dob.toString() + " is not in the database.\n";
            }
        } else {
            // Invalid DOB
            return "DOB " + dob.toString() + ": invalid calendar date!\n";
        }
    }

    /**
     * Helper method for check membership restriction for code "C".
     *
     * @param cs          Class schedule.
     * @param md          Member database.
     * @param memberIndex The actual member index in the database.
     * @param info        Line information from console that separate and putted to into a String.
     * @return status of the member.
     */
    private String checkMembershipRestrictionC(ClassSchedule cs, MemberDatabase md, int memberIndex, String[] info) {
        if (md.getMemberList()[memberIndex] instanceof Member) {
            int classIndex = findClass(cs, info[CLASS_NAME], info[INSTRUCTOR_NAME], info[GYM_LOCATION]);
            if (classIndex != NOT_FOUND) {
                if ((md.getMemberList()[memberIndex] instanceof Family) || md.getMemberList()[memberIndex] instanceof Premium) {
                    return checkMemberExistenceC(cs, classIndex, md.getMemberList()[memberIndex], info);
                } else {
                    if (md.getMemberList()[memberIndex].getLocation().getLoction().equalsIgnoreCase(info[GYM_LOCATION])) {
                        Member member = md.getMemberList()[memberIndex];
                        return checkMemberExistenceC(cs, classIndex, member, info);
                    } else {
                        // Membership Location Restriction
                        int locationIndex = Location.findLocation(cs.getFitnessClasses()[classIndex].getLocation());
                        return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " checking in " +  Location.values()[locationIndex].toString() + " - standard membership location restriction.\n";
                    }
                }
            } else {
                // Location not match
                return info[CLASS_NAME] + " by " + info[INSTRUCTOR_NAME] + " does not exist at " + info[GYM_LOCATION] + "\n";
            }
        }
        return null;
    }

    /**
     * Helper method for check membership(guest) restriction for code "CG".
     *
     * @param cs          Class schedule.
     * @param md          Member database.
     * @param memberIndex The actual member index in the database.
     * @param info        Line information from console that separate and putted to into a String.
     * @return status of the guest.
     */
    private String checkMembershipRestrictionCG(ClassSchedule cs, MemberDatabase md, int memberIndex, String[] info) {
        if (md.getMemberList()[memberIndex] instanceof Member) {
            if ((md.getMemberList()[memberIndex] instanceof Family) || md.getMemberList()[memberIndex] instanceof Premium) {
                int classIndex = findClass(cs, info[CLASS_NAME], info[INSTRUCTOR_NAME], info[GYM_LOCATION]);
                if (classIndex != NOT_FOUND) {
                    if (md.getMemberList()[memberIndex].getLocation().getLoction().equalsIgnoreCase(info[GYM_LOCATION])) {
                        return checkMemberExistenceCG(cs, classIndex, (Family) md.getMemberList()[memberIndex],info);
                    } else {
                        // Guest Location Restriction
                        int locationIndex = Location.findLocation(cs.getFitnessClasses()[classIndex].getLocation());
                        return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " Guest checking in " + Location.values()[locationIndex].toString() + " - guest location restiction.\n";
                    }
                } else {
                    // Location not match
                    return info[CLASS_NAME] + " by " + info[INSTRUCTOR_NAME] + " does not exist at " + info[GYM_LOCATION] + "\n";
                }
            } else {
                // Standard membership restriction
                return "Standard membership - guest check-in is not allowed.\n";
            }
        }
        return null;
    }

    /**
     * Private helper method to find the index of the class in the class schedule.
     *
     * @param cs             Class schedule.
     * @param className      Name of the class.
     * @param instructorName Name of the Instructor.
     * @param gymLocation    Gym location.
     * @return NOT_FOUND for not such class, index for class index in the class schedule.
     */
    private int findClass(ClassSchedule cs, String className, String instructorName, String gymLocation) {
        for (int index = 0; index < cs.getClassListSize(); index++) {
            if (cs.getFitnessClasses()[index] == null) {
                break;
            }
            if (cs.getFitnessClasses()[index].getClassName().equalsIgnoreCase(className) && cs.getFitnessClasses()[index].getInstructor().equalsIgnoreCase(instructorName) && cs.getFitnessClasses()[index].getLocation().equalsIgnoreCase(gymLocation)) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to check the existence of member in a class for code "C".
     *
     * @param cs         Class schedule.
     * @param classIndex The index of the class in the class schedule.
     * @param member     member information.
     * @return status of the member.
     */
    private String checkMemberExistenceC(ClassSchedule cs, int classIndex, Member member, String[] info) {
        if (cs.getFitnessClasses()[classIndex].getParticipants().checkMembership(member) == true) {
            // Already Checked in
            if(info[OPERATION].equals("C")){
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " already checked in.\n";
            }else {
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " (guest) already checked in.\n";
            }
        } else {
            boolean[] classCheckedIn = new boolean[cs.getClassListSize()];
            for (int index = 0; index < cs.getClassListSize(); index++) {
                if (cs.getFitnessClasses()[index] == null) {
                    break;
                }
                if (cs.getFitnessClasses()[index].getParticipants().checkMembership(member) == true) {
                    classCheckedIn[index] = true;
                }
            }
            return checkTimeConflictC(cs, classIndex, classCheckedIn, member, info);
        }
    }

    /**
     * Helper method to check the existence of member(guest) in a class for code "CG".
     *
     * @param cs         Class schedule.
     * @param classIndex The index of the class in the class schedule.
     * @param member     member information.
     * @return status of the guest.
     */
    private String checkMemberExistenceCG(ClassSchedule cs, int classIndex, Family member, String[] info) {
        if (member.getNumber_Of_Guest_Pass() == 0) {
            return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " ran out of guest pass.\n";
        }

        member.setNumber_Of_Guest_Pass(member.getNumber_Of_Guest_Pass() - 1);
        cs.getFitnessClasses()[classIndex].getGuests().addGuest(member);
        String str = info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " (guest) checked in "
                + cs.getFitnessClasses()[classIndex].getClassName() + " - "
                + cs.getFitnessClasses()[classIndex].getInstructor()
                + ", " + cs.getFitnessClasses()[classIndex].getTime(cs.getFitnessClasses()[classIndex].getTimeRange())
                + ", " + cs.getFitnessClasses()[classIndex].getLocation() + "\n";
        str = str + printClassList(cs,classIndex);
        return str + "\n";

    }

    /**
     * Helper method to check time conflict of the class for code "C".
     *
     * @param cs             Class schedule
     * @param classIndex     The index of the class in the class schedule.
     * @param classCheckedIn boolean array for class this member checked in that use true to represent checked in.
     * @param member         Member information.
     * @return status of the member.
     */
    private String checkTimeConflictC(ClassSchedule cs, int classIndex, boolean[] classCheckedIn, Member member, String[] info) {
        FitnessClass classNeedsCheck = cs.getFitnessClasses()[classIndex];

        for (int index = 0; index < classCheckedIn.length; index++) {
            if (cs.getFitnessClasses()[classIndex] == null) {
                break;
            }
            if (classCheckedIn[index] == true) {
                if (cs.getFitnessClasses()[index].getTimeRange().equalsIgnoreCase(classNeedsCheck.getTimeRange())) {
                    // Time conflict with other class
                    int locationIndex = Location.findLocation(cs.getFitnessClasses()[classIndex].getLocation());
                    return "Time conflict - " + cs.getFitnessClasses()[classIndex].getClassName()
                            + " - " + cs.getFitnessClasses()[classIndex].getInstructor() + ", "
                            + cs.getFitnessClasses()[classIndex].getTime(cs.getFitnessClasses()[classIndex].getTimeRange())
                            + ", " + Location.values()[locationIndex].toString() + "\n";
                }
            }
        }

        classNeedsCheck.getParticipants().add(member);
        String str = info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " checked in "
                + cs.getFitnessClasses()[classIndex].getClassName()
                + " - " + cs.getFitnessClasses()[classIndex].getInstructor()
                + ", " + cs.getFitnessClasses()[classIndex].getTime(cs.getFitnessClasses()[classIndex].getTimeRange())
                + ", " + cs.getFitnessClasses()[classIndex].getLocation() + "\n";
        str = str + printClassList(cs, classIndex);
        return str + "\n";
    }

    /**
     * Method to drop a member/guest according to different situation.
     *
     * @param cs   Class schedule.
     * @param md   Member database.
     * @param info Line information from console that separate and putted to into a String.
     * @return statues of the member/guest.
     */
    public String dropClass(ClassSchedule cs, MemberDatabase md, String[] info) {
        return ClassInfoCheck(cs, md, info);
    }

    /**
     * Helper method to check a member is in this class for code "D".
     *
     * @param cs          Class schedule.
     * @param md          Member database.
     * @param memberIndex The actual member index in the database.
     * @param info        Line information from console that separate and putted to into a String.
     * @return statues of the member.
     */
    private String classMembershipCheckD(ClassSchedule cs, MemberDatabase md, int memberIndex, String[] info) {
        int classIndex = findClass(cs, info[CLASS_NAME], info[INSTRUCTOR_NAME], info[GYM_LOCATION]);
        Member member = md.getMemberList()[memberIndex];
        if (classIndex != NOT_FOUND) {
            if (cs.getFitnessClasses()[classIndex].getParticipants().checkMembership(member)) {
                cs.getFitnessClasses()[classIndex].getParticipants().remove(member);
                // Done with the class
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " done with the class.\n";
            } else {
                // Didn't check in
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " did not check in.\n";
            }
        } else {
            // Location not match
            return info[CLASS_NAME] + " by " + info[INSTRUCTOR_NAME] + " does not exist at " + info[GYM_LOCATION] + "\n";
        }
    }

    /**
     * Helper method to check a member is in this class for code "DG".
     *
     * @param cs          Class schedule.
     * @param md          Member database.
     * @param memberIndex The actual member index in the database.
     * @param info        Line information from console that separate and putted to into a String.
     * @return statues of the guest.
     */
    private String classMembershipCheckDG(ClassSchedule cs, MemberDatabase md, int memberIndex, String[] info) {
        int classIndex = findClass(cs, info[CLASS_NAME], info[INSTRUCTOR_NAME], info[GYM_LOCATION]);
        Family member = (Family) md.getMemberList()[memberIndex];
        if (classIndex != NOT_FOUND) {
            if (cs.getFitnessClasses()[classIndex].getGuests().checkMembership(member)) {
                member.setNumber_Of_Guest_Pass(member.getNumber_Of_Guest_Pass() + 1);
                cs.getFitnessClasses()[classIndex].getGuests().remove(member);
                // Done with the class
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " Guest done with the class.\n";
            } else {
                // Didn't check in
                return info[CLASS_FIRST_NAME] + " " + info[CLASS_LAST_NAME] + " did not check in.\n";
            }
        } else {
            // Location not match
            return info[CLASS_NAME] + " by " + info[INSTRUCTOR_NAME] + " does not exist at " + info[GYM_LOCATION] + "\n";
        }
    }
}
