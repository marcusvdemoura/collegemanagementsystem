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

public class Course {

    CollegeBranch collegeBranch;
    private String name;
    private ArrayList<Lecturer> listOfLecturersCourse = new ArrayList<>();
    private ArrayList<Student> listOfStudentsCourse = new ArrayList<>();
    private ArrayList<Module> listOfModulesCourse = new ArrayList<>();
    private Double price;
    private Lecturer lecturer;

    public Course(CollegeBranch collegeBranch, String name, Double price) {
        this.collegeBranch = collegeBranch;
        this.name = name;
        this.price = price;
    }

    public CollegeBranch getCollegeBranch() {
        return collegeBranch;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public ArrayList<Lecturer> getListOfLecturersCourse() {
        return listOfLecturersCourse;
    }

    public void setListOfLecturersCourse(ArrayList<Lecturer> listOfLecturersCourse) {
        this.listOfLecturersCourse = listOfLecturersCourse;
    }

    public ArrayList<Student> getListOfStudentsCourse() {
        return listOfStudentsCourse;
    }

//    public void setListOfStudentsCourse(ArrayList<Student> listOfStudentsCourse) {
//        this.listOfStudentsCourse = listOfStudentsCourse;
//    }

    public ArrayList<Module> getListOfModulesCourse() {
        return listOfModulesCourse;
    }

    public void setListOfModulesCourse(ArrayList<Module> listOfModulesCourse) {
        this.listOfModulesCourse = listOfModulesCourse;
    }

    public void printListModules(){

        for(Module m: getListOfModulesCourse()){
            System.out.println("Module: " + m.getSubject());
//            System.out.println("Lecturer: "+ m.getLecturer().getFirstName()+ " "+ m.getLecturer().getLastName());
            System.out.println("Class day and Hour: " + m.getWeekDay() +  " at " + m.getClassHour());
        }
    }
}
