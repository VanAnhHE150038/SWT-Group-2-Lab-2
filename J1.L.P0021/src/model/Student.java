/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Admin
 */
public class Student implements Comparable<Student> {

    private String id;
    private String name;
    private int semester;
    private String courseName;

    public Student() {
    }

    public Student(String id, String name, int semester, String courseName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean equalsStudent(Student o) {
        try {
            if (o != null) {
                Student student = (Student) o;
                return this.id.equalsIgnoreCase(student.getId())
                        && this.getSemester() == student.getSemester()
                        && this.getCourseName().equals(student.getCourseName()); //To change body of generated methods, choose Tools | Templates.
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public int compareTo(Student student) {
        return this.getName().compareTo(student.getName());
    }
}
