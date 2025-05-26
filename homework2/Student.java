package dev.m3s.programming2.homework2;

import java.time.Year;

public class Student {
    private String firstName = ConstantValues.NO_NAME;  
    private String lastName = ConstantValues.NO_NAME;
    private int id = ConstantValues.MIN_ID;
    private int startYear = Year.now().getValue();
    private int graduationYear = 0;
    private int degreeCount = 0; 
    private Degree[] degrees = new Degree[3]; 
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    // Constructors
    public Student() {
        this.id = getRandomId();
    }

    public Student(String lname, String fname) {
        this.id = getRandomId();
        if (fname != null && !fname.isEmpty()) {
            this.firstName = fname;
        }
        if (lname != null && !lname.isEmpty()) {
            this.lastName = lname;
        }
    }

    //Methods
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
        if (i >= 0 && i < degreeCount && dName != null) {
            degrees[i].setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degreeCount && course != null) {
            return degrees[i].addStudentCourse(course);
        } else {
            return false;
        }
    }

    public int addCourses(final int i, StudentCourse[] courses) {
        if (i >= 0 && i < degreeCount && courses != null) {
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
        for (int i = 0; i < degreeCount; i++) {
            Degree indivDegree = degrees[i];
            if (indivDegree != null) {
                indivDegree.printCourses();
            }
        }
    }

    public void printDegrees() {
        for (int i = 0; i < degreeCount; i++) {
            Degree indivDegree = degrees[i];
            if (indivDegree != null) {
                System.out.println(indivDegree);
            }
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degreeCount && title != null) {
            degrees[i].setTitleOfThesis(title);
        }
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String setBirthDate(String personId) {
        if (personId == null) {
            return "No change";
        }
        
        PersonID tempPerson = new PersonID();
        String callback = tempPerson.setPersonId(personId);

        if (callback.equals("Ok")) {
            this.birthDate = tempPerson.getBirthDate();
            return getBirthDate();
            
        }
        return "No change";
    }

    public boolean hasGraduated() {
        if (this.graduationYear > 0) {
            return true;
        } else {
            return false;
        }
    }
 
    private boolean canGraduate() {
        if (degrees[0] != null && degrees[1] != null) {
            if (degrees[0].getCredits() >= ConstantValues.BACHELOR_CREDITS &&
                degrees[1].getCredits() >= ConstantValues.MASTER_CREDITS) {
                
                if (!degrees[0].getTitleOfThesis().equals(ConstantValues.NO_TITLE) &&
                    !degrees[1].getTitleOfThesis().equals(ConstantValues.NO_TITLE)) {
                    return true; 
                }
            }
        }
        return false;
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
            + "First name: " + this.firstName + ", Last name: " + this.lastName + "\n"
            + "Date of birth: " + (this.birthDate.equals(ConstantValues.NO_BIRTHDATE) ? "Not available" : this.birthDate) + "\n"
            + "Status: " + (hasGraduated() ? "The student has graduated in " + this.graduationYear : "The student has not graduated, yet") + "\n"
            + "Start year: " + this.startYear + " (studies have lasted for " + getStudyYears() + " years)" + "\n"
            + "Total credits: " + (degrees[0] != null ? degrees[0].getCredits() : 0.0) + (degrees[1] != null ? degrees[1].getCredits() : 0.0) + "\n"
            + "Bachelor credits: " + (degrees[0] != null ? degrees[0].getCredits() : 0.0) + " ==> "
                + ((degrees[0] != null && degrees[0].getCredits() >= ConstantValues.BACHELOR_CREDITS)
                    ? "All required bachelor credits completed (" + degrees[0].getCredits() + "/180.0)"
                    : "Missing bachelor credits " + (ConstantValues.BACHELOR_CREDITS - degrees[0].getCredits()) + " (" + degrees[0].getCredits() + "/180.0)") + "\n"
            + "Title of BSc Thesis: \"" + (degrees[0] != null ? degrees[0].getTitleOfThesis() : "No title") + "\"\n"
            + "Master credits: " + (degrees[1] != null ? degrees[1].getCredits() : 0.0) + " ==> "
                + ((degrees[1] != null && degrees[1].getCredits() >= ConstantValues.MASTER_CREDITS)
                    ? "All required master's credits completed (" + degrees[1].getCredits() + "/120.0)"
                    : "Missing master's credits " + (ConstantValues.MASTER_CREDITS - degrees[1].getCredits()) + " (" + degrees[1].getCredits() + "/120.0)") + "\n"
            + "Title of MSc Thesis: \"" + (degrees[1] != null ? degrees[1].getTitleOfThesis() : "No title") + "\"\n";
    }
}
    