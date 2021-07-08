package JavaProject.tableviewPOJO;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415




 */

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GradesMaster {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty module = new SimpleStringProperty();
    private final SimpleStringProperty studentid = new SimpleStringProperty();
    private final SimpleDoubleProperty grade = new SimpleDoubleProperty();


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getModule() {
        return module.get();
    }

    public SimpleStringProperty moduleProperty() {
        return module;
    }

    public void setModule(String module) {
        this.module.set(module);
    }

    public String getStudentid() {
        return studentid.get();
    }

    public SimpleStringProperty studentidProperty() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid.set(studentid);
    }

    public double getGrade() {
        return grade.get();
    }

    public SimpleDoubleProperty gradeProperty() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade.set(grade);
    }
}
