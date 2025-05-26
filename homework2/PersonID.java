package dev.m3s.programming2.homework2;

public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;


    // Methods
    public String getBirthDate() {
        return this.birthDate;
    }

    public String setPersonId(final String personID) { 
        if (personID != null && checkPersonIDNumber(personID)) {
            String birthdate = personID.substring(0, 6); // 6 first digits
            String century = personID.charAt(6) == '+' ? "18" : personID.charAt(6) == '-' ? "19" : "20"; // century mark
            String fullYear = century + birthdate.substring(4,6); // full year by adding century mark and last two digits
            String formattedBirthdate = birthdate.substring(0, 2) + "." + birthdate.substring(2, 4) + "." + fullYear;
            
            if (checkBirthdate(formattedBirthdate)) {
                if (checkValidCharacter(personID)) {
                    this.birthDate = formattedBirthdate;
                    return "Ok";
                } else {
                    return ConstantValues.INCORRECT_CHECKMARK;
                }
            } else {
                return ConstantValues.INVALID_BIRTHDAY;
            }
        } else {
            return ConstantValues.INVALID_BIRTHDAY;
        }
    }

    private boolean checkPersonIDNumber(final String personID) {
        int len = personID.length();
        int c = personID.charAt(6);
        if (len == 11 && (c == '+' || c == '-' || c == 'A')) {
            return true;
        } else {
            return false; 
        }
    }

    private boolean checkLeapYear(int year) {
        if ((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidCharacter(final String personID) {
        String numericPart = personID.substring(0,6) + personID.substring(7,10);
        int number = Integer.parseInt(numericPart);
        int remainder = number % 31;
        String lookUpTable = "0123456789ABCDEFHJKLMNPRSTUVWXY";

        if (lookUpTable.charAt(remainder) == personID.charAt(10)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBirthdate(final String date) {
        String[] parts = date.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if ((year >= 0) && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
            int maxDays = switch(month) {
                case 4, 6, 9, 11 -> 30;
                case 2 -> (checkLeapYear(year) ? 29 : 28);
                default -> 31;
            };
            return day <= maxDays;
        } else {
            return false;
        }
    }


}