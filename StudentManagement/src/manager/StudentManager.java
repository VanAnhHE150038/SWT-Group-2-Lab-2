/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import model.Student;
import validate.Validator;

/**
 *
 * @author Admin
 */
public class StudentManager {

    /**
     * User creates student by inputting information from keyboard
     *
     * @param students
     */
    public void createStudent(ArrayList<Student> students) {
        System.out.println("---- Create Student ----");
        Student student;
        // Loop until user want to stop
        while (true) {
            System.out.println("Enter information of a studennt: ");
            do {
                // step 1: Input name, semester and course name of the student and check duplicate
                student = inputInformationOfStudent(students);
                // step 2: Check if information duplicate. The required user enter again
                if (student == null) {
                    System.out.println("Input information of the student again: ");
                }
            } while (student == null);
            students.add(student);
            // step 3: Display information of the student
            displayInfomationStudent(student);
            System.out.println("Create successfully!\n");
            // step 4: If number of students has been added greater than 10.
            if (students.size() >= 10) {
                //  Ask user want to continue or not.
                if (!Validator.getChoice("Do you want to continue (Y/N)?: ", "Y", "N")) {
                    return;
                }
            }
        }
    }

    /**
     * Input name, semester and course name of the student and check duplicate
     *
     * @param students
     * @return Student
     */
    private Student inputInformationOfStudent(ArrayList<Student> students) {
        String id = Validator.getString("Enter student id: ");
        String name = getNameByID(students, id);
        // Get name by id
        if (name != null) {
            System.out.println("Student name: " + name);
        } // if id is not exist. Enter student name
        else {
            name = Validator.getName("Enter student name: ");
        }
        System.out.print("Enter semester: ");
        int semester = Validator.getChoiceInt(1, Integer.MAX_VALUE);
        System.out.println("Enter course name: ");
        // Choice course name
        String courseName = choiceCourse();
        Student student = new Student(id, name, semester, courseName);
        // Check if the information of the student has been existed or not
        if (Validator.checkInformationStudentExist(students, student)) {
            System.out.println("Invalid. Information Of The Student Has Been Existed!");
            return null;
        }
        return student;
    }

    /**
     * Get name by id
     *
     * @param (students,id)
     * @return String
     */
    private String getNameByID(ArrayList<Student> students, String id) {
        // Loop to access all student into list students
        for (Student student : students) {
            // Check student id is exist or not.
            if (student.getId().equalsIgnoreCase(id)) {
                return student.getName();
            }
        }
        return null;
    }

    /**
     * Choice course name
     *
     * @return String
     */
    private String choiceCourse() {
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C/C++");
        System.out.print("Choice 1-3: ");
        int choice = Validator.getChoiceInt(1, 3);
        // Choice course
        switch (choice) {
            case 1:
                return "Java";
            case 2:
                return ".Net";
            default:
                return "C/C++";
        }
    }

    /**
     * Display information of the student
     *
     * @param student
     */
    private void displayInfomationStudent(Student student) {
        System.out.format("%-10s|%-25s|%-10s|%-12s\n", "Student ID", "Student Name", "Semester", "Course Name");
        System.out.format("%-10s|%-25s|%-10d|%-12s\n", student.getId(),
                student.getName(), student.getSemester(), student.getCourseName()); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Find and list student (by name) and sort by name.
     *
     * @param students
     */
    public void findAndSort(ArrayList<Student> students) {
        System.out.println("---- Find And Sort ----");
        // step 1: Check list students is empty or not 
        if (students.isEmpty()) {
            System.out.println("List Empty. Can Not Find And Sort.\n");
            return;
        }
        // step 2: Enter student name user want to find
        String name = Validator.getName("Enter name: ");
        // step 3: Get list information of the students find by name
        ArrayList<Student> ListStudentFindByName = getListStudentByName(students, name);
        // Step 4: Check if list information of the students by name exist or not
        if (!ListStudentFindByName.isEmpty()) {
            // step 5: Sort list information of the students by name in alphabetical order
            sortListByName(ListStudentFindByName);
            // step 6: Display list information of the students after sorted by name 
            displayListFindByName(ListStudentFindByName);
        } else {
            System.out.println("Not Found.");
        }
        System.out.println();
    }

    /**
     * Get list students find by name
     *
     * @param (students, name)
     * @return ArrayList<Student>
     */
    private ArrayList<Student> getListStudentByName(ArrayList<Student> students, String name) {
        ArrayList<Student> ListStudentFindByName = new ArrayList<>();
        // Loop to access all information of the student into list students
        for (Student student : students) {
            // If the student has student name contains name user entered from keyboard.
            // Add student into ListStudentFindByName
            if (student.getName().contains(name)) {
                ListStudentFindByName.add(student);
            }
        }
        return ListStudentFindByName;
    }

    /**
     * Sort list information of the students by name in alphabetical order
     *
     * @param ListStudentFindByName
     */
    private void sortListByName(ArrayList<Student> ListStudentFindByName) {
        Collections.sort(ListStudentFindByName);
    }

    /**
     * Display list information of the students after sorted by name
     *
     * @param ListStudentFindByName
     */
    private void displayListFindByName(ArrayList<Student> ListStudentFindByName) {
        System.out.println("--- List Of Students Found ---");
        System.out.format("%-25s|%-10s|%-12s\n", "Student Name", "Semester", "Course Name");
        // Loop to access all information of the student into list students and display the information
        for (Student student : ListStudentFindByName) {
            System.out.format("%-25s|%-10d|%-12s\n", student.getName(),
                    student.getSemester(), student.getCourseName());
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * The program allows user find a student by ID, after finding a student by
     * Id, a question is displayed (Do you want to update (U) or delete (D)
     * student
     *
     * @param students
     */
    public void updateOrDelete(ArrayList<Student> students) {
        System.out.println("---- Update/Delete ----");
        // step 1: Check list students is empty or not 
        if (students.isEmpty()) {
            System.out.println("List Empty. Can Not Update Or Delete.\n");
            return;
        }
        // View all of information of the students in list
        viewListInformationOfStudents(students);

        // step 2: Enter student id user want to find
        String id = Validator.getString("Enter Id: ");

        // step 3: Get list information of a student by id 
        ArrayList<Student> listStudentFindByID = getListStudentByID(students, id);

        // step 4: Check if list student found by id empty or not
        if (!listStudentFindByID.isEmpty()) {
            // step 5: Display list information of the student
            displayListFindByID(listStudentFindByID);

            // step 6: Ask user want to update(u) or delete(d). 
            // If user entered 'U/u'. Then implement update
            if (Validator.getChoice("Do you want to update (U) or delete (D) student?: ", "U", "D")) {
                // Update processing
                updateProcess(students, listStudentFindByID, id);
            } // If user entered 'D/d' Then implement update
            else {
                // Delete processing
                deleteProcess(students, listStudentFindByID);
            }
        } else {
            System.out.println("Not Found.");
        }
        System.out.println();
    }

    /**
     * View all of information of the students in list
     *
     * @param students
     */
    private void viewListInformationOfStudents(ArrayList<Student> students) {
        System.out.println("--- List Information Of Students");
        System.out.format("%-10s|%-25s|%-10s|%-12s\n", "Student ID", "Student Name", "Semester", "Course Name");
        for (Student student : students) {
            System.out.format("%-10s|%-25s|%-10d|%-12s\n", student.getId(),
                    student.getName(), student.getSemester(), student.getCourseName());
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get list information of a student by id
     *
     * @param (students,id)
     * @return ArrayList<Student>
     */
    private ArrayList<Student> getListStudentByID(ArrayList<Student> students, String id) {
        ArrayList<Student> listStudentFindByID = new ArrayList<>();
        // Loop to access all information of the student into list students add
        for (Student student : students) {
            // If the information has id duplicate with id user want to update
            // Add the information of the student to ListStudentFindByID
            if (student.getId().equalsIgnoreCase(id)) {
                listStudentFindByID.add(student);
            }
        }
        return listStudentFindByID;
    }

    /**
     * Display list information of the student
     *
     * @param ListStudentFindByID
     */
    private void displayListFindByID(ArrayList<Student> ListStudentFindByID) {
        System.out.println("--- List Information Of Student ---");
        System.out.format("%-8s|%-10s|%-25s|%-10s|%-12s\n", "Number", "Student ID", "Student Name", "Semester", "Course Name");
        int number = 0;
        // Loop to access all information of the student into list ListStudentFindByID
        for (Student student : ListStudentFindByID) {
            number++;
            System.out.format("%-8d|%-10s|%-25s|%-10d|%-12s\n", number, student.getId(), student.getName(),
                    student.getSemester(), student.getCourseName());
        }
    }

    /**
     * Update processing
     *
     * @param (student, ListStudentFindByID, id)
     */
    private void updateProcess(ArrayList<Student> students, ArrayList<Student> listStudentFindByID, String id) {
        System.out.print("Enter yout choice: ");
        // step 1: Choice an information of the student in list user want to update 
        int choiceNumber = Validator.getChoiceInt(1, listStudentFindByID.size());
        Student getStudent = listStudentFindByID.get(choiceNumber - 1);

        // step 2: Display information of the student has been chosen
        displayInfomationStudent(getStudent);

        // step 3: Input new information
        Student newStudent = inputInfomationStudent(students, listStudentFindByID, choiceNumber);

        // step 4: Update new information of the student in list if newStudent not null
        if (newStudent != null) {
            // Loop to access all information of the student into list students
            int number = 0;
            System.out.format("%-8s|%-10s|%-25s|%-10s|%-12s\n", "Number", "Student ID", "Student Name", "Semester", "Course Name");
            for (Student student : students) {
                // Set again new information for the information has been chosen at step 1
                if (student.equalsStudent(getStudent)) {
                    number++;
                    student.setId(newStudent.getId());
                    student.setName(newStudent.getName());
                    student.setSemester(newStudent.getSemester());
                    student.setCourseName(newStudent.getCourseName());
                    System.out.format("%-8d|%-10s|%-25s|%-10d|%-12s\n", number, student.getId(), student.getName(),
                            student.getSemester(), student.getCourseName());
                } // Update name if the information of the student has id duplicate with id of newStudent
                else if (student.getId().equalsIgnoreCase(newStudent.getId()) && !student.getName().equalsIgnoreCase(newStudent.getName())) {
                    student.setName(newStudent.getName());
                    number++;
                    System.out.format("%-8d|%-10s|%-25s|%-10d|%-12s\n", number, student.getId(), student.getName(),
                            student.getSemester(), student.getCourseName());
                }
            }
            System.out.println("Information Of The Student Has Been Updated!");
        }
    }

    /**
     * Input new information
     *
     * @param (students, ListStudentFindByID, student)
     */
    private Student inputInfomationStudent(ArrayList<Student> students, ArrayList<Student> listStudentFindByID, int choice) {
        String id = Validator.getString("Enter id student: ");
        String name = name = Validator.getName("Enter name student: ");
        System.out.print("Enter semester: ");
        int semester = Validator.getChoiceInt(1, Integer.MAX_VALUE);
        System.out.println("Enter course name: ");
        String courseName = choiceCourse();
        Student newStudent = new Student(id, name, semester, courseName);
        // Check information of the student is exist or not
        if (Validator.checkInformationStudentExist(students, newStudent)) {
            System.out.format("%-10s|%-10s|%-12s\n", "Student ID", "Semester", "Course Name");
            System.out.format("%-10s|%-10d|%-12s\n", newStudent.getId(), newStudent.getSemester(), newStudent.getCourseName());
            System.out.println("Invalid. Information Of The Student Has Been Existed!");
            return null;
        }
        return newStudent;
    }

    /**
     * Delete processing
     *
     * @param (students, ListStudentFindByID)
     */
    private void deleteProcess(ArrayList<Student> students, ArrayList<Student> ListStudentFindByID) {
        System.out.print("Enter yout choice: ");
        // step 1: Choice an information of the student in list user want to update 
        int choiceNumber = Validator.getChoiceInt(1, ListStudentFindByID.size());
        Student getStudent = ListStudentFindByID.get(choiceNumber - 1);

        // step 2: Display information of the student want to delete 
        displayInfomationStudent(getStudent);

        // Step 3: Delete information of the student
        // Loop to access all student information into list students
        for (Student student : students) {
            // Check If information of the student in list duplicate with information has been chosen. 
            if (student.equals(getStudent)) {
                // Delete information
                students.remove(student);
                System.out.println("Information Of The Student Has Been Deleted!");
                return;
            }
        }
        displayListFindByID(ListStudentFindByID);
    }

    /**
     * Report
     *
     * @param (students)
     */
    public void report(ArrayList<Student> students) {
        System.out.println("---- Report ----");
        // step 1: Check list students is empty or not 
        if (students.isEmpty()) {
            System.out.println("List Empty. Can Report.\n");
            return;
        }

        // step 2: Set all students is not report.
        boolean[] checkReport = setNotreport(students.size());
        int totalCourse;
        System.out.format("%-25s|%-12s|%-13s\n", "Student Name", "Course Name", "Total Course");
        // step 3: Loop to access all student into list student to report
        for (int i = 0; i < students.size(); i++) {
            totalCourse = 1;
            // If student has been reported then skip.
            if (checkReport[i] == true) {
                continue;
            } else {
                checkReport[i] = true;
            }
            int j = i + 1;
            // Loop get reported the student has same course name into list student
            for (; j < students.size(); j++) {
                // Check if student has id, name, and courseName duplicate
                // Count total course of the student
                // set reported
                if (students.get(i).getId().equalsIgnoreCase(students.get(j).getId())
                        && students.get(i).getName().equalsIgnoreCase(students.get(j).getName())
                        && students.get(i).getCourseName().equals(students.get(j).getCourseName())) {
                    checkReport[j] = true;
                    totalCourse++;
                }
            }
            System.out.format("%-25s|%-12s|%-13d\n", students.get(i).getName(), students.get(i).getCourseName(), totalCourse);
        }
        System.out.println();
    }

    /**
     * Set all students is not report
     *
     * @param (size)
     * @return boolean[]
     */
    private boolean[] setNotreport(int size) {
        boolean[] report = new boolean[size];
        // Loop to access all student into list students to set each element is false
        for (int i = 0; i < report.length; i++) {
            report[i] = false;
        }
        return report;
    }

    /**
     * Read information of the students in file "Student Management.txt"
     *
     * @param (students, frame)
     */
    public void readFile(ArrayList<Student> students, String path) throws ParseException, IOException {
        RandomAccessFile readFile = new RandomAccessFile(path, "r");
        String string;
        String[] array;
        String id;
        String name;
        int semester;
        String courseName;

        Student student;
        // Reading information of each student in each line
        try {
            while (true) {
                string = readFile.readLine();
                // Check null
                if (string == null || string.trim().equals("")) {
                    break;
                }
                array = string.split("[|]");
                id = array[0].trim();
                name = array[1].trim();
                semester = Integer.parseInt(array[2].trim());
                courseName = array[3].trim();
                // Create Student
                student = new Student(id, name, semester, courseName);
                // Add into array list
                students.add(student);
            }
        } finally {
            readFile.close();
        }

    }
}
