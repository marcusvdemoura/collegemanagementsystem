package JavaProject.entities;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415



 */

public class Exam {

    private String date;
    private String lecturerName;
    private Module module;


    public Exam(String date, String lecturerName, Module module) {
        this.date = date;
        this.lecturerName = lecturerName;
        this.module = module;
    }

    public String getDate() {
        return date;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public Module getModule() {
        return module;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
