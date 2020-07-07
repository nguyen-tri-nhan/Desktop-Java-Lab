package vd;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VerifyData {

    public void VerifyData() {
    }

    // Student code: max length is 10, not contains special characters (@, #, $)
    public boolean checkStudentCode(String studentCode) {
        if (studentCode.isEmpty()) {
            return false;
        }

        if (!studentCode.matches("^[a-zA-Z0-9[.][_][-][ ]]{1,10}$")) {
            return false;
        }

        if (studentCode.contains("@") || studentCode.contains("#") || studentCode.contains("$")) {
            return false;
        }

        return true;
    }

    // Student name: max length is 30
    public boolean checkName(String studentName) {

        if (studentName.isEmpty()) {
            return false;
        }

        if (studentName.length() > 30) {
            return false;
        }

        return true;
    }

    // Birth Date: in format MM/DD/YYYY
    public boolean checkDate(String birthday) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setLenient(false);
        Date today = java.util.Calendar.getInstance().getTime();

        try {
                Date date = df.parse(birthday);
            if (date.compareTo(today) < 0) {
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) { 
            return false;
        }


    }

    // Phone number: max length is 15, contain numeric characters only (0 – 9)
    public boolean checkPhone(String phoneNumber) {

        if (phoneNumber.isEmpty()) {
            return false;
        }

        if (!phoneNumber.matches("[0-9]{10,11}")) {
            return false;
        }
        return true;
    }

    //	Email: max length is 30, contain only one “@” character, do not contain special characters (!, #, $)
    public boolean checkEmail(String email) {

        if (email.isEmpty()) {
            return false;
        }



        if (!email.matches("[0-9a-zA-Z@[^!#$]]{1,30}")) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                count++;
            }
        }

        if (count > 1) {
            return false;
        }

        // RegEx check email: ([a-zA-Z0-9_\\-]{3,30})@([a-zA-Z0-9_\\-]{3,30})(\\.([a-zA-Z]{2,5})){1,2} 
        return true;

    }
}
