package dev.m3s.programming2.homework3;

public abstract class Person {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    // Constructors
    public Person(String lname, String fname) {
        setFirstName(fname);
        setLastName(lname);
    }

    // Methods
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

    public String getBirthDate() {
        return this.birthDate;
    }

    public String setBirthDate(String personId) {
        if (personId != null) {
            PersonID pid = new PersonID();
            String callback = pid.setPersonId(personId); // Sets birthday attribute in PersonID

            if (callback.equals("Ok")) {
                this.birthDate = pid.getBirthDate();
                return getBirthDate();
            }
            return "No change";
        }
        return "No change";
    }

    protected int getRandomId(final int min, final int max) {
        return (int)(Math.random() * (max - min + 1)) + min; //generate random min-max. Range + Min.
    }

    abstract String getIdString();
}


