package dev.m3s.programming2.homework3;

import java.time.Year;

public class DesignatedCourse {
    private Course course;
    private boolean responsible;
    private int year;

    // Constructors
    public DesignatedCourse() {
    }

    public DesignatedCourse(Course course, boolean resp, int year) {
        this.course = course;
        this.responsible = resp;
        this.year = year;
    }

    // Methods
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        if (course != null) {
            this.course = course;
        }
    }

    public boolean isResponsible() {
        return this.responsible;
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

    public boolean getResponsible() {
        return this.responsible;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        int currentYearPlus = Year.now().getValue() + 1;
        if (year >= 2000 && year <= currentYearPlus) {
            this.year = year;
        }
    }

    public String toString() {
        return "[course=" + course.toString() + ", year=" + year + "]";
    }
}
