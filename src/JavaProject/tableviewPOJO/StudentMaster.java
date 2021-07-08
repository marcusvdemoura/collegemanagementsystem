package JavaProject.tableviewPOJO;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415



 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentMaster {

    private final SimpleStringProperty studentID = new SimpleStringProperty();
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleStringProperty branch = new SimpleStringProperty();
    private final SimpleStringProperty course = new SimpleStringProperty();
    private final SimpleIntegerProperty year = new SimpleIntegerProperty();


    public StudentMaster(){

    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setBranch(String branch) {
        this.branch.set(branch);
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getStudentID() {
        return studentID.get();
    }

    public SimpleStringProperty studentIDProperty() {
        return studentID;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getBranch() {
        return branch.get();
    }

    public SimpleStringProperty branchProperty() {
        return branch;
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }
}
