package com.example.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that manage multiple fitness classes.
 * This class can return with fitness classes information and print out information of the memeber checked in.
 *
 * @author Sihua Zhou, Yihang Sun
 */
public class ClassSchedule {
    /**
     * List of fitness classes.
     */
    private FitnessClass[] classes;
    /**
     * Number of fitness classes.
     */
    private int numClasses;

    /**
     * Default size for a list of fitness class.
     */
    private final int defaultClassSize = 10;
    /**
     * Increasing size for the list of fitness class.
     */
    private final int increaseSize = 4;

    /**
     * ClassSchedule Constructor that create a instance of ClassSchedule
     */
    public ClassSchedule() {
        this.numClasses = defaultClassSize;
        classes = new FitnessClass[this.numClasses];
    }

    /**
     * Increase the size of the list of fitness class when necessary.
     */
    public void growSize() {
        FitnessClass[] newClasses = new FitnessClass[classes.length + increaseSize];
        for (int index = 0; index < classes.length; index++) {
            newClasses[index] = classes[index];
        }
        numClasses = classes.length + increaseSize;
        classes = newClasses;
    }

    /**
     * Method to print out the class schedule for certain user input.
     * @return The list of the class schedule along with its participants and guest list.
     */
    public String printClassSchedule() {
        String str = "";
        if (classes[0] != null) {
            str = str + "-Fitness classes-\n";
            for (int index = 0; index < classes.length; index++) {
                if (classes[index] != null) {
                    str = str + classes[index].toString() + "\n";
                    if (classes[index].getParticipants().getMemberList()[0] != null) {
                        str = str + "- Participants -\n";
                        str = str + classes[index].printPartcipants();
                    }
                    if (classes[index].getGuests().getMemberList()[0] != null) {
                        str = str + "- Guests -\n";
                        str = str + classes[index].printGuests();
                    }

                } else {
                    break;
                }
            }
            str = str + "-end of class list.\n";
            str = str + "\n";
        } else {
            str = str + "Fitness class schedule is empty.\n";
        }
        return str;
    }

    /**
     * Method to print the class schedule after it is loaded.
     * @return The loaded list of class schedule
     */
    public String printLoadedList() {
        String str = "";
        for (int index = 0; index < classes.length; index++) {
            if (classes[index] != null) {
                str = str + classes[index].toString() + "\n";
                if (classes[index].getParticipants().getMemberList()[0] != null) {
                    str = str + "- Participants -\n";
                    str = str + classes[index].printPartcipants();
                }
                if (classes[index].getGuests().getMemberList()[0] != null) {
                    str = str + "- Guests -\n";
                    str = str + classes[index].printGuests();
                }

            } else {
                break;
            }
        }
        return str;
    }

    /**
     * Setter method to set a class schedule slot for new class.
     * @param fc    A fitness class.
     * @param index index in the list of class schedule.
     */
    public void setClass(FitnessClass fc, int index) {
        if (index < numClasses) {
            this.classes[index] = fc;
        } else {
            growSize();
            this.classes[index] = fc;
        }
    }

    /**
     * Getter method to access the class schedule.
     * @return List of class schedule.
     */
    public FitnessClass[] getFitnessClasses() {
        return classes;
    }

    /**
     * Getter method to access the size of the class schedule list.
     * @return size the list of class schedule.
     */
    public int getClassListSize() {
        return numClasses;
    }

    /**
     * Helper method to check the existence of a instructor.
     * @param inputName Instructor name.
     * @return True for instructor exist, false for not.
     */
    public boolean checkInstructor(String inputName) {
        for (int index = 0; index < classes.length; index++) {
            if (classes[index] == null) {
                break;
            }
            if (classes[index].getInstructor().equalsIgnoreCase(inputName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check the existence of a class
     * @param inputName Class name.
     * @return True for exist, False for not.
     */
    public boolean checkClassName(String inputName) {
        for (int index = 0; index < classes.length; index++) {
            if (classes[index] == null) {
                break;
            }
            if (classes[index].getClassName().equalsIgnoreCase(inputName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check the existence of gym location
     * @param inputName gym location.
     * @return True for exist, false for not.
     */
    public boolean checkLocation(String inputName) {
        for (int index = 0; index < classes.length; index++) {
            if (classes[index] == null) {
                break;
            }
            if (classes[index].getLocation().equalsIgnoreCase(inputName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to load class schedule from a file.
     * @param cs Class schedule.
     * @return The textual list of class schedule for display
     */
    public static String loadSchedule(ClassSchedule cs) {
        try {
            Scanner classesSchedule = new Scanner(new File("classSchedule.txt"));
            String str = "";
            int className = 0;
            int instructor = 1;
            int timeRange = 2;
            int location = 3;
            int index = 0;
            FitnessClass newClass;
            while (classesSchedule.hasNext()) {
                String[] infomation = classesSchedule.nextLine().split("\\s+", 4);
                newClass = new FitnessClass(infomation[className], infomation[instructor], infomation[timeRange], infomation[location]);
                cs.setClass(newClass, index);
                index++;
            }

            str = str + "\n";
            str = str + "-Fitness classes loaded-\n";
            str = str + cs.printLoadedList();
            str = str + "-end of class list.\n";
            str = str + "\n";
            classesSchedule.close();
            return str;
        } catch (FileNotFoundException f) {
            return "file not found.";
        }
    }
}
