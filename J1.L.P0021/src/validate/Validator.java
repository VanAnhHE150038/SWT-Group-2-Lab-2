/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.util.ArrayList;
import java.util.Scanner;
import model.Student;

/**
 *
 * @author Admin
 */
public class Validator {

    private static Scanner in = new Scanner(System.in);
    // Using to check name input just contain characters is spaces or in range [a-z] or [A-Z]
    private static final String NAME_VALID = "^[A-Za-z\\s+]+$";

    /**
     * Get an integer in the range [min, max]
     *
     * @param (min, max)
     */
    public static int getChoiceInt(int min, int max) {
        //loop until user enter correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                // result is only valid in range [min, max]
                if (result < min || result > max) {
                    throw new Exception();
                }
                return result;
            } catch (Exception e) {
                System.err.println("Invalid! Please enter an integer number in the range[" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * get choice Yes(Y) = true / No(n) = false
     *
     * @param (message)
     * @return boolean
     */
    public static boolean getChoice(String message, String string1, String string2) {
        //loop until user enter correct
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if(result.isEmpty()){
                System.err.println("Not empty. Must be enter " + string1 + " or " + string2+ "!");
            }
            // if user enter string equals string1. Then return true
            else if (result.equalsIgnoreCase(string1)) {
                return true;
            } else if (result.equalsIgnoreCase(string2)) { // if user enter string equals string1. Then return false
                return false;
            } else {
                System.err.println("Invalid! Must be enter " + string1 + " or " + string2+ "!");
            }
        }
    }

    /**
     * get a String is not empty
     *
     * @param (message)
     * @return boolean
     */
    public static String getString(String message) {
        // loop until user input correct
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            // check string user entered is empty or not
            if (result.isEmpty()) {
                System.err.println("Not empty. Please re-enter!");
            } else {
                return result;
            }
        }
    }

    /**
     * get Name just contains alphabet
     *
     * @param (message)
     * @return boolean
     */
    public static String getName(String message) {
        // loop until user input corect
        while (true) {
            String result = getString(message);
            //check name just be cantain letters or not
            if (result.matches(NAME_VALID)) {
                result = result.toLowerCase();
                String[] words = result.split("\\s+");
                result = "";
                // Concating words 
                for (String word : words) {
                    result += String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1, word.length()) + " ";
                }
                result = result.trim();
                return result;
            } else {
                System.err.println("Invalid! Name must be letters. Please Re-enter.");
            }
        }
    }

    /**
     * Check if student id and name information is correct or not
     *
     * @param (students, id, name)
     * @return boolean
     */
    public static boolean checkStudentIDAndName(ArrayList<Student> students, String id, String name) {
        // loop to access all student into list students
        for (Student student : students) {
            // check student id is exist or not
            if (student.getId().equalsIgnoreCase(id)) {
                // Check the student name is valid with id or not
                if (!student.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if information the student has been added or not
     *
     * @param (students, student)
     * @return boolean
     */
    public static boolean checkInformationStudentExist(ArrayList<Student> students, Student student) {
        // loop to access all student into list students
        for (Student student1 : students) {
            // Check duplicate of the information student in the list with information student user just have entered
            if (student1.equalsStudent(student)) {
                return true;
            }
        }
        return false;
    }

}
