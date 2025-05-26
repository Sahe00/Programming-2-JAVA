package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class AssistantTeacher extends Employee implements Teacher, Payment{
    private List<DesignatedCourse> courses = new ArrayList<>();

    // Constructors
    public AssistantTeacher(String lname, String fname) {
        super(lname, fname);
    }
    
    // Methods
    public String getEmployeeIdString() { // THIS CLASS IS IN PROGRESS
        return "OY_ASSISTANT_";
    }

    public String getCourses() {
        String result = "";
        for (DesignatedCourse dc : courses) {
            result += dc.toString() + "\n";
        }
        return result;
    }

    public void setCourses(List<DesignatedCourse> courses) {
        if (courses != null) {
            this.courses = courses;
        }
    }

    public String toString() { // Check if payment is correct
        String result = "Teacher id: " + getIdString() + "\n";
            result += "First name: " + getFirstName() + ", Last name: " + getLastName() + "\n";
            result += "Birthdate: " + getBirthDate() + "\n";
            result += "Salary: " + String.format("%.2f", (getPayment() != null ? getPayment().calculatePayment() : 0.0)) + "\n"; //.replace('.', ',') 
            result += "Assistant for courses:\n";
            result += getCourses();
            return result.trim();
    }
}
