package dev.m3s.programming2.homework2;

public class Course {
    private String name = ConstantValues.NO_NAME;
    private String courseCode = ConstantValues.NO_NAME; // 3 letters + 3 digits
    private Character courseBase = ' '; // One of the values: 'A', 'P', or 'S'
    private int courseType = ConstantValues.OPTIONAL;
    private int period = 0; // 1-5
    private double credits = ConstantValues.MIN_CREDITS; // 0-55
    private boolean numericGrade = false; // true or false

    // Constructors
    public Course() {
    }

    public Course(String name, final int code, Character courseBase, final int type,
                 final int period, final double credits, boolean numericGrade){
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        
        // Validation
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            setPeriod(period);
        }

        setCredits(credits);
        setNumericGrade(numericGrade);

    }

    public Course(Course course) {
        this.name = course.name;
        this.courseCode = course.courseCode;
        this.courseBase = course.courseBase;
        this.courseType = course.courseType;
        this.period = course.period;
        this.credits = course.credits;
        this.numericGrade = course.numericGrade;
    }

    // Methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) { 
            this.name = name;
        } 
    }

    public String getCourseTypeString() {
        return (this.courseType == ConstantValues.OPTIONAL) ? "Optional" : "Mandatory";
    }

    public int getCourseType() {
        return (this.courseType == ConstantValues.OPTIONAL) ? ConstantValues.OPTIONAL : ConstantValues.MANDATORY;
    }

    public void setCourseType(final int type) {
        if (type == ConstantValues.OPTIONAL || type == ConstantValues.MANDATORY) {
            this.courseType = type;
        }
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        if (courseCode > 0 && courseCode < 1000000) {
            if (courseBase == 'A' || courseBase == 'P' || courseBase == 'S' ||
            courseBase == 'a' || courseBase == 'p' || courseBase == 's') {
                this.courseCode = String.valueOf(courseCode) + Character.toUpperCase(courseBase);
                this.courseBase = Character.toUpperCase(courseBase);
            }
        }
    }

    public Character getCourseBase() {
        return this.courseBase;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(final int period) {
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }

    public double getCredits() {
        return this.credits;
    }

    private void setCredits(final double credits) {
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }

    public boolean isNumericGrade() {
        return this.numericGrade;
    }

    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }

    @Override
    public String toString() {
        return "[" + this.courseCode + this.courseType
            + " ( " + String.format("%.2f", this.credits) + " cr), "
            + "\"" + this.name + "\". "
            + this.getCourseTypeString() + ", period: " + this.period + ".]";
    }


    

}