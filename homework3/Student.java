package dev.m3s.programming2.homework3;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private int id = ConstantValues.MIN_STUDENT_ID;
    private int startYear = Year.now().getValue();
    private int graduationYear = 0;
    private List<Degree> degrees = new ArrayList<>();

    // Constructors
    public Student(String lname, String fname) {
        super(lname, fname);
        this.id = getRandomId(1, 100);
        degrees.add(new Degree()); // 0 = bachelor
        degrees.add(new Degree()); // 1 = master
    }

    // Methods
    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID) {
            this.id = id;
        } else {
            this.id = ConstantValues.MIN_STUDENT_ID; 
        }
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
        if (!canGraduate()) {
            return "Check amount of required credits";
        }
    
        int currentYear = Year.now().getValue();
        if (graduationYear < startYear || graduationYear > currentYear) {
            return "Check the graduation year";
        }
    
        this.graduationYear = graduationYear;
        return "Ok";
    }

    public void setDegreeTitle(final int i, String dName) {
        if (i >= 0 && i < degrees.size() && dName != null) {
            degrees.get(i).setDegreeTitle(dName);
        }
    }
    
    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degrees.size() && course != null) {
            return degrees.get(i).addStudentCourse(course);
        } else {
            return false;
        }
    }

    public int addCourses(final int i, List<StudentCourse> courses) {
        if (i >= 0 && i < degrees.size() && courses != null) {
            int count = 0;
            for (StudentCourse course : courses) {
                if (addCourse(i, course)) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
        
    }

    public void printCourses() {
        for (int i = 0; i < degrees.size(); i++) {
            Degree indivDegree = degrees.get(i);
            if (indivDegree != null) {
                indivDegree.printCourses();
            }
        }
    }

    public void printDegrees() {
        for (int i = 0; i < degrees.size(); i++) {
            Degree indivDegree = degrees.get(i);
            if (indivDegree != null) {
                System.out.println(indivDegree);
            }
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degrees.size() && title != null) {
            degrees.get(i).setTitleOfThesis(title);
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
        if (degrees.size() < 2) {
            return false; // If both are necessary
        }

        Degree bachelor = degrees.get(0);
        Degree master = degrees.get(1);

        if (bachelor == null || master == null) {
            return false;
        }

        // Check Bachelor degree
        boolean bachelorCreditsCheck = bachelor.getCredits() >= ConstantValues.BACHELOR_CREDITS;
        boolean bachelorMandatoryCheck = bachelor.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.BACHELOR_MANDATORY;
        boolean bachelorThesisCheck = bachelor.getTitleOfThesis() != null && bachelor.getTitleOfThesis().equals(ConstantValues.NO_TITLE);

        // Check Masters degree
        boolean masterCreditsCheck = master.getCredits() >= ConstantValues.MASTER_CREDITS;
        boolean masterMandatoryCheck = master.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.MASTER_MANDATORY;
        boolean masterThesisCheck = master.getTitleOfThesis() != null && master.getTitleOfThesis().equals(ConstantValues.NO_TITLE);

        return bachelorCreditsCheck && bachelorMandatoryCheck && bachelorThesisCheck && 
               masterCreditsCheck && masterMandatoryCheck && masterThesisCheck;
    }

    public int getStudyYears() {
        int currentYear = Year.now().getValue();
        if (hasGraduated()) {
            return this.graduationYear - this.startYear;
        } else {
            return currentYear - this.startYear;
        }
    }

    public String toString() { 
        Degree bachelor = degrees.get(0);
        Degree master = degrees.get(1);

        List<Double> bachelorGpaInfo = bachelor.getGPA(ConstantValues.ALL);
        List<Double> masterGpaInfo = master.getGPA(ConstantValues.ALL);

        double totalSum = bachelorGpaInfo.get(0) + masterGpaInfo.get(0);
        double totalCount = bachelorGpaInfo.get(1) + masterGpaInfo.get(1);
        double totalGPA = totalCount > 0 ? totalSum / totalCount : 0.0;

        double totalCredits = (bachelor != null ? bachelor.getCredits() : 0.0) + (master != null ? master.getCredits() : 0.0);

        return getIdString() + "\n"
            + "First name: " + getFirstName() + ", Last name: " + getLastName() + "\n"
            + "Date of birth: " + (getBirthDate().equals(ConstantValues.NO_BIRTHDATE) ? "\"Not available\"" : getBirthDate()) + "\n"
            + "Status: " + (hasGraduated() ? "The student has graduated in " + this.graduationYear : "The student has not graduated, yet") + "\n"
            + "Start year: " + this.startYear + " (studies have lasted for " + getStudyYears() + " years)\n"
            + "Total credits: " + totalCredits + " (GPA = " + String.format("%.2f", totalGPA).replace('.',',') + ")\n"
            + "Bachelor credits: " + (bachelor != null ? bachelor.getCredits() : 0.0) + "\n"
                + ((bachelor != null && bachelor.getCredits() >= ConstantValues.BACHELOR_CREDITS)
                    ? "Total bachelor credits completed (" + bachelor.getCredits() + "/180.0)\n"
                    : "Missing bachelor credits " + (ConstantValues.BACHELOR_CREDITS - bachelor.getCredits()) + " (" + bachelor.getCredits() + "/180.0)\n")
                    + (bachelor.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.BACHELOR_MANDATORY
                    ? "All mandatory bachelor credits completed (" + bachelor.getCreditsByType(ConstantValues.MANDATORY) + "/150.0)\n"
                    : "Missing mandatory bachelor credits " + (ConstantValues.BACHELOR_MANDATORY - bachelor.getCreditsByType(ConstantValues.MANDATORY)) + " (" + bachelor.getCreditsByType(ConstantValues.MANDATORY) + "/150.0)\n")
                    + "GPA of Bachelor studies: " + String.format("%.2f", bachelorGpaInfo.get(2)).replace('.', ',') + "\n"
                    + "Title of BSc Thesis: \"" + (bachelor != null ? bachelor.getTitleOfThesis() : "No title") + "\"\n"
            + "Master credits: " + (master != null ? master.getCredits() : 0.0) + "\n"
                + ((master != null && master.getCredits() >= ConstantValues.MASTER_CREDITS)
                    ? "Total master's credits completed (" + master.getCredits() + "/120.0)\n"
                    : "Missing master's credits " + (ConstantValues.MASTER_CREDITS - master.getCredits()) + " (" + master.getCredits() + "/120.0)\n")
                    + (master.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.MASTER_MANDATORY
                    ? "All mandatory master credits completed (" + master.getCreditsByType(ConstantValues.MANDATORY) + "/50.0)\n"
                    : "Missing mandatory master credits " + (ConstantValues.MASTER_MANDATORY - master.getCreditsByType(ConstantValues.MANDATORY)) + " (" + master.getCreditsByType(ConstantValues.MANDATORY) + "/50.0)\n")
                    + "GPA of Master studies: " + String.format("%.2f", masterGpaInfo.get(2)).replace('.', ',') + "\n"
                    + "Title of MSc Thesis: \"" + (master != null ? master.getTitleOfThesis() : "No title") + "\"\n";
    }

    @Override
    public String getIdString() {
        return "Student id: " + String.valueOf(getId());
    }

}
