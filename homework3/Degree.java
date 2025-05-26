package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class Degree {
    private static final int MAX_COURSES = 50;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<>();

    // Methods
    public List<StudentCourse> getCourses() {
        return myCourses;
    }

    public void addStudentCourses(List<StudentCourse> courses) {
        if (courses != null) {
            for (StudentCourse course : courses) { // For-each loop used
                addStudentCourse(course);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && myCourses.size() < MAX_COURSES) {
            myCourses.add(course);
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

        for (int i = 0; i < myCourses.size(); i++) {
            StudentCourse sc = myCourses.get(i); // Fetch individual course in index i
            Course c = sc.getCourse(); // Access Course class for the methods through StudentCourse class

            if (c.getCourseBase() == base && sc.isPassed()) {
                totalCredits += c.getCredits();
            }
        }

        return totalCredits;
    }

    public double getCreditsByType(final int courseType) {
        double totalCredits = 0.0;

        for (int i = 0; i < myCourses.size(); i++) {
            StudentCourse sc = myCourses.get(i); // Fetch individual course
            Course c = sc.getCourse(); // Access Course class for the methods through StudentCourse class

            if (c.getCourseType() == courseType && sc.isPassed()) {
                totalCredits += c.getCredits();
            }
        }

        return totalCredits;
    }

    public double getCredits() {
        double totalCredits = 0.0;

        for (int i = 0; i < myCourses.size(); i++) {
            StudentCourse sc = myCourses.get(i); // Fetch individual course
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
        if (myCourses.isEmpty()) {
            return;
        }
        
        for (int i = 0; i < myCourses.size(); i++) {
            StudentCourse sc = myCourses.get(i); // Fetch individual course on index i
            if (sc != null) {
                System.out.println(sc);
            }
        }
        
    }

    public List<Double> getGPA(int type) {
        double sum = 0.0;
        int count = 0;

        for (StudentCourse sc : myCourses) {
            Course c = sc.getCourse();
            if ((type == c.getCourseType() || type == ConstantValues.ALL) && c.isNumericGrade()) {
                double grade = sc.getGradeNum();
                sum += grade;
                count++;
            } 
        }
        double average = count > 0 ? sum / count : 0.0;
        average = Math.round(average * 100.0) / 100.0; // Round to 2 decimal points
        return List.of(sum, (double) count, average);
    }

    public String toString() {
        String result =  "Degree [Title: \"" + this.degreeTitle + "\" (courses: " + this.myCourses.size() + ")" + "\n"
                      + "Thesis title: \"" + this.titleOfThesis + "\"" + "\n";

        for (int i = 0; i < myCourses.size(); i++) {
            if (myCourses.get(i) != null) {
                result += " " + (i + 1) + ". " + myCourses.get(i) + "\n";
            }
        }

        return result;
    }
}  