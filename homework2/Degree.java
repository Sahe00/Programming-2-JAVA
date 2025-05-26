package dev.m3s.programming2.homework2;

public class Degree {
    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];

    // Methods
    public StudentCourse[] getCourses() {
        return myCourses;
    }

    public void addStudentCourses(StudentCourse[] courses) {
        if (courses != null) {
            for (StudentCourse course : courses) { // For-each loop used
                addStudentCourse(course);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && count < MAX_COURSES) {
            myCourses[count] = course;
            count++;
            return true;
        } else {
            return false;
        }
    }

    public String getDegreeTitle() {
        return this.degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null && !degreeTitle.isEmpty()) { 
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis() {
        return this.titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if (titleOfThesis != null && !titleOfThesis.isEmpty()) {
            this.titleOfThesis = titleOfThesis;
        }
    }

    public double getCreditsByBase(Character base) {
        double totalCredits = 0.0;

        for (int i = 0; i < count; i++) {
            StudentCourse sc = myCourses[i]; // Fetch individual course
            Course c = sc.getCourse(); // Access Course class for the methods through StudentCourse class

            if (c.getCourseBase() == base && sc.isPassed()) {
                totalCredits += c.getCredits();
            }
        }

        return totalCredits;
    }

    public double getCreditsByType(final int courseType) {
        double totalCredits = 0.0;

        for (int i = 0; i < count; i++) {
            StudentCourse sc = myCourses[i]; // Fetch individual course
            Course c = sc.getCourse(); // Access Course class for the methods through StudentCourse class

            if (c.getCourseType() == courseType && sc.isPassed()) {
                totalCredits += c.getCredits();
            }
        }

        return totalCredits;
    }

    public double getCredits() {
        double totalCredits = 0.0;

        for (int i = 0; i < count; i++) {
            StudentCourse sc = myCourses[i]; // Fetch individual course
            Course c = sc.getCourse(); // Access Course class for the methods through StudentCourse class

            if (sc.isPassed()) {
                totalCredits += c.getCredits();
            }
        }

        return totalCredits;
    }

    private boolean isCourseCompleted(StudentCourse c) {
        if (c != null && c.isPassed()) {
            return true;
        } else {
            return false;
        }
    }

    public void printCourses() {
        if (count == 0) {
            return;
        }
        
        for (int i = 0; i < count; i++) {
            StudentCourse sc = myCourses[i]; // Fetch individual course
            if (sc != null) {
                System.out.println(sc);
            }
        }
        
    }

    public String toString() { // Check if the format is correct in the myCourses[i] !
        String result =  "Degree [Title: \"" + this.degreeTitle + "\" (courses: " + this.count + ")" + "\n"
        + "Thesis title: \"" + this.titleOfThesis + "\"" + "\n";

        for (int i = 0; i < count; i++) {
            if (myCourses[i] != null) {
                result += " " + (i + 1) + ". " + myCourses[i] + "\n";
            }
        }

        return result;
    }
}  