package com.example.project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * A class control the event in the GUI
 * Similar to console base GymManager but in user interface
 * @author Sihua Zhou
 */
public class GymManagerController {

    /**
     * RadioButton for choosing membership type and class check in type.
     */
    @FXML
    private RadioButton standard, family, premium, member, guest;

    /**
     * TextField for required information from user
     */
    @FXML
    private TextField FirstNameAddRemove, LastNameAddRemove, LocationAddRemove, className, insturctorName, gymLocation, FirstNameCheckDrop, LastNameCheckDrop;

    /**
     * DatePicker for user to choose the date within the calendar.
     */
    @FXML
    private DatePicker dobAddRemove, dobCheckDrop;

    /**
     * ToggleGroup to bind different type of RadioButton.
     */
    @FXML
    private ToggleGroup membershipType, memberType;

    /**
     * TextArea to display information
     */
    @FXML
    private TextArea display;

    /**
     * Instance variable MemberDatabase that stores member information.
     */
    private MemberDatabase md;
    /**
     * Instance variable ClassSchedule that stores class information.
     */
    private ClassSchedule cs;
    /**
     * Instance variable FitnessClass that is instance of FitnessClass to use its methods.
     */
    private FitnessClass fc;

    /**
     * Initialize the GymManagerController with these instance variables.
     */
    @FXML
    public void initialize(){
        this.md = new MemberDatabase();
        this.cs = new ClassSchedule();
        this.fc = new FitnessClass();
        dobAddRemove.getEditor().setDisable(true);
        dobCheckDrop.getEditor().setDisable(true);
        display.setEditable(false);
    }

    /**
     * Method to load the class schedule from file.
     * @param event MenuItem Load Class Schedule From File in Class Schedule bar.
     */
    @FXML
    void loadSchedule(ActionEvent event){
        display.appendText(ClassSchedule.loadSchedule(cs));
    }

    /**
     * Method to load old member list from file.
     * @param event MenuItem Load Member List From File in Member Database bar.
     */
    @FXML
    void loadMemberList(ActionEvent event){
        display.appendText(MemberDatabase.loadMemberList(md));
    }

    /**
     * Method to add a validated member after press Button Add.
     * @param event press Button Add.
     */
    @FXML
    void addMember(ActionEvent event){
        try{
            String[] info = new String[5];
            if(standard.isSelected()){
                info[MemberDatabase.OPERATION] = "A";
            }else if(family.isSelected()){
                info[MemberDatabase.OPERATION] = "AF";
            }else if(premium.isSelected()){
                info[MemberDatabase.OPERATION] = "AP";
            }else{
                display.appendText("Please select a membership type!\n");
            }
            if(!FirstNameAddRemove.getText().equals("") && !LastNameAddRemove.getText().equals("") && !LocationAddRemove.equals("")){
                info[MemberDatabase.FIRST_NAME] = FirstNameAddRemove.getText();
                info[MemberDatabase.LAST_NAME] = LastNameAddRemove.getText();
                String DOB = Date.formatDate(dobAddRemove.getValue().toString());
                info[MemberDatabase.DATE_OF_BIRTH] = DOB;
                info[MemberDatabase.LOCATION] = LocationAddRemove.getText();
                display.appendText(md.addMember(md, info) + "\n");

            }else{
                display.appendText("Enter the required information in the text field!\n");
            }
            FirstNameAddRemove.clear();
            LastNameAddRemove.clear();
            dobAddRemove.getEditor().clear();
            LocationAddRemove.clear();
            membershipType.selectToggle(null);
        }catch (NullPointerException e){
            display.appendText("Enter the required information in the text field!\n");
        }
    }

    /**
     * Method to remove a validated member after press Button Remove.
     * @param event press Button Remove.
     */
    @FXML
    void removeMember(ActionEvent event){
        try{
            String[] info = new String[5];
            if(!FirstNameAddRemove.getText().equals("") && !LastNameAddRemove.getText().equals("")){
                info[MemberDatabase.FIRST_NAME] = FirstNameAddRemove.getText();
                info[MemberDatabase.LAST_NAME] = LastNameAddRemove.getText();
                String DOB = Date.formatDate(dobAddRemove.getValue().toString());
                info[MemberDatabase.DATE_OF_BIRTH] = DOB;
                display.appendText(md.removeMember(md, info) + "\n");
            }else{
                display.appendText("Enter the required information in the text field!\n");
            }

            FirstNameAddRemove.clear();
            LastNameAddRemove.clear();
            dobAddRemove.getEditor().clear();
            membershipType.selectToggle(null);
        }catch (NullPointerException e){
            display.appendText("Enter the required information in the text field!\n");
        }
    }

    /**
     * Method to check in a validated member/guest after press Button Check in.
     * @param event press Button Check in.
     */
    @FXML
    void checkMember(ActionEvent event){
        try{
            String[] info = new String[7];
            if(member.isSelected()){
                info[FitnessClass.OPERATION] = "C";
            }else if(guest.isSelected()){
                info[FitnessClass.OPERATION] = "CG";
            }else{
                display.appendText("Please select a member type!\n");
            }
            if(!className.getText().equals("") && !insturctorName.getText().equals("") && !gymLocation.getText().equals("") && !FirstNameCheckDrop.getText().equals("") && !LastNameCheckDrop.getText().equals("")){
                info[FitnessClass.CLASS_NAME] = className.getText();
                info[FitnessClass.INSTRUCTOR_NAME] = insturctorName.getText();
                info[FitnessClass.GYM_LOCATION] = gymLocation.getText();
                info[FitnessClass.CLASS_FIRST_NAME] = FirstNameCheckDrop.getText();
                info[FitnessClass.CLASS_LAST_NAME] = LastNameCheckDrop.getText();
                String DOB = Date.formatDate(dobCheckDrop.getValue().toString());
                info[FitnessClass.CLASS_DOB] = DOB;
                display.appendText(fc.checkInMember(cs, md, info));
            }else{
                display.appendText("Enter the required information in the text field!\n");
            }
            className.clear();
            insturctorName.clear();
            gymLocation.clear();
            FirstNameCheckDrop.clear();
            LastNameCheckDrop.clear();
            dobCheckDrop.getEditor().clear();
            memberType.selectToggle(null);
        }catch (NullPointerException e){
            display.appendText("Enter the required information in the text field!\n");
        }
    }

    /**
     * Method to drop a validated member/guest after press Button Drop.
     * @param event press Button Drop.
     */
    @FXML
    void dropMember(ActionEvent event){
       try {
           String[] info = new String[7];
           if(member.isSelected()){
               info[FitnessClass.OPERATION] = "D";
           }else if(guest.isSelected()){
               info[FitnessClass.OPERATION] = "DG";
           }else{
               display.appendText("Please select a member type!\n");
           }
           if(!className.getText().equals("") && !insturctorName.getText().equals("") && !gymLocation.getText().equals("") && !FirstNameCheckDrop.getText().equals("") && !LastNameCheckDrop.getText().equals("")){
               info[FitnessClass.CLASS_NAME] = className.getText();
               info[FitnessClass.INSTRUCTOR_NAME] = insturctorName.getText();
               info[FitnessClass.GYM_LOCATION] = gymLocation.getText();
               info[FitnessClass.CLASS_FIRST_NAME] = FirstNameCheckDrop.getText();
               info[FitnessClass.CLASS_LAST_NAME] = LastNameCheckDrop.getText();
               String DOB = Date.formatDate(dobCheckDrop.getValue().toString());
               info[FitnessClass.CLASS_DOB] = DOB;
               display.appendText(fc.dropClass(cs, md, info));
           }else{
               display.appendText("Enter the required information in the text field!\n");
           }

           className.clear();
           insturctorName.clear();
           gymLocation.clear();
           FirstNameCheckDrop.clear();
           LastNameCheckDrop.clear();
           dobCheckDrop.getEditor().clear();
           memberType.selectToggle(null);
       }catch (NullPointerException e){
           display.appendText("Enter the required information in the text field!\n");
       }
    }

    /**
     * Method to display the member list in database as it is in Member Database bar.
     * @param event select MenuItem Print.
     */
    @FXML
    void print(ActionEvent event){
        display.appendText(md.print());
    }

    /**
     * Method to display the member list in database sorted by county and then zipcode.
     * @param event select MenuItem Print by County/Zipcode in Member Database bar.
     */
    @FXML
    void printByCountyZipcode(ActionEvent event){
        display.appendText(md.printByCounty());
    }

    /**
     * Method to display the member list in database sorted by expiration date.
     * @param event select MenuItem Print by Expiration Date in Member Database bar.
     */
    @FXML
    void printByExpirationDate(ActionEvent event){
        display.appendText(md.printByExpirationDate());
    }

    /**
     * Method to display the member list in database sorted by last name and then first name.
     * @param event select MenuItem Print by Last/First Name in Member Database bar.
     */
    @FXML
    void printByLastFirstName(ActionEvent event){
        display.appendText(md.printByName());
    }

    /**
     * Method to display the member list in database along with its corresponding fees.
     * @param event select MenuItem Print with Fee in Membership Fee bar.
     */
    @FXML
    void printWithFee(ActionEvent event){
        display.appendText(md.printWithFee());
    }

    /**
     * Method to display the class schedule along with its participants and guests.
     * @param event select MenuItem Show All Classes in Class Schedule bar.
     */
    @FXML
    void printClassSchedule(ActionEvent event){
        display.appendText(cs.printClassSchedule());
    }

}