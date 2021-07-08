package JavaProject.entities;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415



 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person{

    private HashMap<String, Double> grades = new HashMap<String, Double>();
    private boolean isPaidFull;
    private String courseYear;
    private String courseName;
    private String collegeBranchUnit;




    public Student(String firstName, String lastName, String gender, String phone, String dob,
                   String emailAddress, String id, String password, String collegeBranchUnit,
                   String courseName, boolean isPaidFull, String courseYear) {
        super(firstName, lastName, gender, phone, dob, emailAddress, id, password);
        this.collegeBranchUnit = collegeBranchUnit;
        this.isPaidFull = isPaidFull;
        this.courseYear = courseYear;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public String getCourseYear() {
        return courseYear;
    }






}
