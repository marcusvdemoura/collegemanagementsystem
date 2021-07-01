package JavaProject.entities;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415
Valeria Cardoso da Paz – 21214
Alexson Oliveira Silva – 21643




 */


import java.util.ArrayList;

public class Module {

    private String subject;
    private Course course;
    private CollegeBranch collegeBranch;
    private Lecturer lecturer;
    private ArrayList<Student> listOfStudentsModule = new ArrayList<>();
    private String weekDay;
    private String classHour;
    private ArrayList<Assignment> listOfAssignments = new ArrayList<>();
    private ArrayList<Exam> listOfExams = new ArrayList<>();


    public Module(String subject, Course course, CollegeBranch collegeBranch, String weekDay, String classHour) {
        this.subject = subject;
        this.course = course;
        this.collegeBranch = collegeBranch;
        this.weekDay = weekDay;
        this.classHour = classHour;
    }

    public String getSubject() {
        return subject;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getClassHour() {
        return classHour;
    }

    public ArrayList<Assignment> getListOfAssignments() {
        return listOfAssignments;
    }

    public CollegeBranch getCollegeBranch() {
        return collegeBranch;
    }

    public Course getCourse() {
        return course;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public ArrayList<Student> getListOfStudentsModule() {
        return listOfStudentsModule;
    }

    public void setLecture(Lecturer lecture) {
        this.lecturer = lecture;
    }

    public ArrayList<Exam> getListOfExams() {
        return listOfExams;
    }
}
