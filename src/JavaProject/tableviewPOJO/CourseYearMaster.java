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

public class CourseYearMaster {

    private final SimpleStringProperty branch = new SimpleStringProperty();
    private final SimpleStringProperty course = new SimpleStringProperty();
    private final SimpleIntegerProperty year = new SimpleIntegerProperty();
    private final SimpleStringProperty module = new SimpleStringProperty();


    public CourseYearMaster() {
    }

    public String getBranch() {
        return branch.get();
    }

    public SimpleStringProperty branchProperty() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch.set(branch);
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
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
}
