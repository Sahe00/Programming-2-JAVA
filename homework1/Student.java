package dev.m3s.programming2.homework1;

import java.time.Year;

public class Student {
    private String firstName = ConstantValues.NO_NAME;  
    private String lastName = ConstantValues.NO_NAME;
    private int id = ConstantValues.MIN_ID;
    private double bachelorCredits = ConstantValues.MIN_CREDITS;
    private double masterCredits = ConstantValues.MIN_CREDITS;
    private String titleOfMastersThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorsThesis = ConstantValues.NO_TITLE;
    private int startYear = Year.now().getValue();
    private int graduationYear = 0;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    // Constructors
    public Student() { //The id for the student will be set in the constructor
        this.id = getRandomId();
    }

    public Student(String lname, String fname) {
        setFirstName(fname);
        setLastName(lname); 
        this.id = getRandomId();
    }

    // Methods
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        } 
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        } 
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
            this.id = id;
        } else {
            this.id = ConstantValues.MIN_ID; 
        }
    }

    public double getBachelorCredits() {
        return this.bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS) {
            this.bachelorCredits = bachelorCredits;
        } else {
            throw new IllegalArgumentException("Bachelor Credits must be between 0.0 and 300.0"); // LIKELY NEEDS TO BE FIXED
        }
    }

    public double getMasterCredits() {
        return this.masterCredits;
    }

    public void setMasterCredits(final double masterCredits) {
        if (masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS) {
            this.masterCredits = masterCredits;
        } else {
            throw new IllegalArgumentException("Masters Credits must be between 0.0 and 300.0"); // LIKELY NEEDS TO BE FIXED
        }
    }

    public String getTitleOfMastersThesis() {
        return this.titleOfMastersThesis;
    }

    public void setTitleOfMastersThesis(String title) {
        if (title != null) {
            this.titleOfMastersThesis = title;
        } else {
            this.titleOfMastersThesis = ConstantValues.NO_TITLE;
        }
    }

    public String getTitleOfBachelorsThesis() {
        return this.titleOfBachelorsThesis;
    }

    public void setTitleOfBachelorsThesis(String title) {
        if (title != null) {
            this.titleOfBachelorsThesis = title;
        } else {
            this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
        }
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(final int startYear) {
        int currentYear = Year.now().getValue();
        if (startYear > 2000 && startYear <= currentYear) {
            this.startYear = startYear;
        } else {
            throw new IllegalArgumentException("Start year must be between 2001 and the current year");
        }
    }

    public int getGraduationYear() {
        return this.graduationYear;
    }

    public String setGraduationYear(final int graduationYear) {
        if (canGraduate()) {
            int currentYear = Year.now().getValue();
            if (graduationYear >= startYear && graduationYear <= currentYear) {
                this.graduationYear = graduationYear;
                return "Ok";
            } else {
                return "Check graduation year";
            }
        } else {
            return "Check the required studies";
        }
    }

    public boolean hasGraduated() {
        if (this.graduationYear > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canGraduate() {
        if (bachelorCredits >= ConstantValues.BACHELOR_CREDITS && masterCredits >= ConstantValues.MASTER_CREDITS) {
            if (!titleOfBachelorsThesis.contains("No title") && !titleOfMastersThesis.contains("No title")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getStudyYears() {
        int currentYear = Year.now().getValue();
        if (hasGraduated()) {
            return this.graduationYear - this.startYear;
        } else {
            return currentYear - this.startYear;
        }
    }

    private int getRandomId() {
        return (int)(Math.random() * 100) + 1; //generate random 1-100. Range + Min.;
    }

    @Override
    public String toString() {
        return "Student id: " + this.id + "\n"
        + " FirstName: " + this.firstName + "," + " LastName: " + this.lastName + "\n"
        + " Date of birth: " + this.birthDate + "\n"
        + " Status: " + (hasGraduated() ? "The student has graduated in " + this.graduationYear : "The student has not graduated, yet") + "\n"
        + " StartYear: " + this.startYear + " (studies have lasted " + getStudyYears() + " years)" + "\n"
        + " BachelorCredits: " + this.bachelorCredits + " ==> " 
            + (this.bachelorCredits >= ConstantValues.BACHELOR_CREDITS 
                ? "All required bachelor credits completed (" + formatCredits(this.bachelorCredits) + "/180.0)" 
                : "Missing bachelor credits " + formatCredits(ConstantValues.BACHELOR_CREDITS - this.bachelorCredits) + " (" + formatCredits(this.bachelorCredits) + "/180.0)") + "\n"
        + " TitleOfBachelorThesis: \"" + this.titleOfBachelorsThesis + "\"\n"
        + " MasterCredits: " + this.masterCredits + " ==> " 
            + (this.masterCredits >= ConstantValues.MASTER_CREDITS 
            ? "All required master's credits completed (" + formatCredits(this.masterCredits) + "/120.0)" 
            : "Missing master's credits " + formatCredits(ConstantValues.MASTER_CREDITS - this.masterCredits) + " (" + formatCredits(this.masterCredits) + "/120.0)") + "\n"
        + " TitleOfMastersThesis: \"" + this.titleOfMastersThesis + "\"\n";
}

    private String formatCredits(double credits) {
        return String.format("%.1f", credits);
}

    public String setPersonId(final String personID) { 
        if (personID != null && checkPersonIDNumber(personID)) {
            String birthdate = personID.substring(0, 6); // 6 first digits
            String century = personID.charAt(6) == '+' ? "18" : personID.charAt(6) == '-' ? "19" : "20"; // century mark
            String fullYear = century + birthdate.substring(4,6); // full year by adding century mark and last two digits
            String formattedBirthdate = birthdate.substring(0, 2) + "." + birthdate.substring(2, 4) + "." + fullYear;
            
            if (checkBirthdate(formattedBirthdate)) {
                if (checkValidCharacter(personID)) {
                    this.birthDate = formattedBirthdate;
                    return "Ok";
                } else {
                    return ConstantValues.INCORRECT_CHECKMARK;
                }
            } else {
                return ConstantValues.INVALID_BIRTHDAY;
            }
        } else {
            return ConstantValues.INVALID_BIRTHDAY;
        }
    }

    private boolean checkPersonIDNumber(final String personID) {
        int len = personID.length();
        int c = personID.charAt(6);
        if (len == 11 && (c == '+' || c == '-' || c == 'A')) {
            return true;
        } else {
            return false; 
        }
    }

    private boolean checkLeapYear(int year) {
        if ((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidCharacter(final String personID) {
        String numericPart = personID.substring(0,6) + personID.substring(7,10);
        int number = Integer.parseInt(numericPart);
        int remainder = number % 31;
        String lookUpTable = "0123456789ABCDEFHJKLMNPRSTUVWXY";

        if (lookUpTable.charAt(remainder) == personID.charAt(10)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBirthdate(final String date) {
        String[] parts = date.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if ((year >= 0) && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
            int maxDays = switch(month) {
                case 4, 6, 9, 11 -> 30;
                case 2 -> (checkLeapYear(year) ? 29 : 28);
                default -> 31;
            };
            return day <= maxDays;
        } else {
            return false;
        }
    }
}
