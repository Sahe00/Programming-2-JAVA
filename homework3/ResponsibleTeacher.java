package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class ResponsibleTeacher extends Employee implements Teacher, Payment {
    private List<DesignatedCourse> courses = new ArrayList<>();

    // Constructor
    ResponsibleTeacher(String lname, String fname) {
        super(lname, fname);
    }

    // Methods
    public String getEmployeeIdString() {
        return "OY_TEACHER_";
    }

    public String getCourses() {
        String result = "";
        for (DesignatedCourse dc : courses) {
            if (dc.isResponsible()) {
                result += "Responsible teacher: ";
            } else {
                result += "Teacher: ";
            }
            result += dc.toString() + "\n";
        }
        return result;
    }

    public void setCourses(List<DesignatedCourse> courses) {
        this.courses = courses;
    }

    public String toString() { // Does this requre "getSalary" instead of getPayment?
        String result = "Teacher id: " + getIdString() + "\n";
            result += "First name: " + getFirstName() + ", Last name: " + getLastName() + "\n";
            result += "Birthdate: " + getBirthDate() + "\n";
            result += "Salary: " + String.format("%.2f", (getPayment() != null ? getPayment().calculatePayment() : 0.0))  + "\n"; //.replace('.', ',')
            result += "Teacher for courses:\n";
            result += getCourses();
            return result.trim();

    }

    

}
