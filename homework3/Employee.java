package dev.m3s.programming2.homework3;

import java.time.Year;

public abstract class Employee extends Person implements Payment {
    private String empId;
    private int startYear = Year.now().getValue();;
    private Payment payment;


    // Constructors
    public Employee(String lname, String fname) {
        super(lname, fname);

        int idNumber = getRandomId(2001, 3000);
        this.empId = getEmployeeIdString() + idNumber;
    }

    // Methods
    public String getIdString() {
        return this.empId;
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

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        }
    }

    @Override
    public double calculatePayment() {
        if (this.payment != null) {
            return this.payment.calculatePayment();
        } else {
            return 0.0;
        }
    } 

    protected abstract String getEmployeeIdString();


}
