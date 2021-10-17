
import manager.StudentManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import model.Student;
import validate.Validator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        //TODO code application logic here
        ArrayList<Student> students = new ArrayList<>();
        StudentManager studentManager = new StudentManager();
        // Read information of the students in file "Student Management.txt"
        studentManager.readFile(students, "Student Management.txt");

        while (true) {
            // Step1: get choices from main menu
            showMenu();
            int choice = Validator.getChoiceInt(1, 5);
            
            switch (choice) {
                case 1:
                    // step2: if user choose option 1: User creates student by inputting information from keyboard.
                    studentManager.createStudent(students);
                    break;

                case 2:
                    // step3: if user choose option 2: Find and list student (by name) and sort by name.
                    studentManager.findAndSort(students);
                    break;

                case 3:
                    /* 
                        step4: if user choose option 3: The program allows user find a student by ID.
                        After finding a student by Id, a question is displayed (Do you want to update (U) or delete (D) student
                     */
                    studentManager.updateOrDelete(students);
                    break;

                case 4:
                    /*  
                        step5: if user choose option 4: Display student(s) with total Courses of this student, as: 
                        Student name, Course and Total of Course
                     */
                    studentManager.report(students);
                    break;

                case 5:
                    // step 6: if user choose option 5: Eixt program.
                    return;
            }
        }
    }

    /**
     * Show main menu
     * 
     */
    private static void showMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}
