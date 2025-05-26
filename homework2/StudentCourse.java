package dev.m3s.programming2.homework2;

import java.time.Year;

public class StudentCourse {
    private Course course;
    private int gradeNum = ConstantValues.MIN_GRADE;
    private int yearCompleted = 0;

    // Constructors
    public StudentCourse() {
    }

    public StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    // Methods
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    protected void setGrade(int gradeNum) {
        int currentYear = Year.now().getValue();
    
        if (this.course != null && this.course.isNumericGrade()) {
            // Numeric grading check
            if (checkGradeValidity(gradeNum)) { // Allow 0
                if (this.yearCompleted == 0) {
                    this.yearCompleted = currentYear;
                }
                this.gradeNum = gradeNum;
            }
        } else {
            // Letter grading check
            if (checkGradeValidity(gradeNum) && ((gradeNum >= 'A' && gradeNum <= 'F') || (gradeNum >= 'a' && gradeNum <= 'f'))) {
                if (this.yearCompleted == 0) {
                    this.yearCompleted = currentYear;
                }
                this.gradeNum = gradeNum;
            }
        }
    }
    

    private boolean checkGradeValidity(final int gradeNum) {
        return (gradeNum >= 0 && gradeNum <= 5) || 
               (gradeNum == ConstantValues.GRADE_FAILED) || 
               (gradeNum == ConstantValues.GRADE_ACCEPTED) ||
               (gradeNum >= 'A' && gradeNum <= 'F') || 
               (gradeNum >= 'a' && gradeNum <= 'f');   
    }

    public boolean isPassed() {
        if ((this.gradeNum == ConstantValues.MIN_GRADE) || (this.gradeNum == ConstantValues.GRADE_FAILED)) {
            return false;
        }
        if (this.gradeNum == 'F' || this.gradeNum == 'f') { 
            return false;
        }
        return true; 
    }

    public int getYear() {
        return this.yearCompleted;
    }

    public void setYear(final int year) {
        int currentYear = Year.now().getValue();
        if (year > 2000 || year <= currentYear) {
            this.yearCompleted = year;
        }
    }

    public String toString() {
        return "[" + this.course.getCourseCode() + "(" + this.course.getCredits() + "), " +
               "\"" + this.course.getName() + "\". " + this.course.getCourseTypeString() + ", " +
                "period: " + this.course.getPeriod() + ".]" + "Year: " + this.yearCompleted + ", Grade: " +
                this.gradeNum + ".]"; 
    }
}