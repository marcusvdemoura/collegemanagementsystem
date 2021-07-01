package JavaProject.tableviewPOJO;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415
Valeria Cardoso da Paz – 21214
Alexson Oliveira Silva – 21643




 */

import javafx.beans.property.SimpleStringProperty;

public class TimetablePOJO {


    private final SimpleStringProperty module = new SimpleStringProperty();
    private final SimpleStringProperty lecturer = new SimpleStringProperty();
    private final SimpleStringProperty weekday = new SimpleStringProperty();
    private final SimpleStringProperty classhour = new SimpleStringProperty();


    public String getModule() {
        return module.get();
    }

    public SimpleStringProperty moduleProperty() {
        return module;
    }

    public void setModule(String module) {
        this.module.set(module);
    }

    public String getLecturer() {
        return lecturer.get();
    }

    public SimpleStringProperty lecturerProperty() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer.set(lecturer);
    }

    public String getWeekday() {
        return weekday.get();
    }

    public SimpleStringProperty weekdayProperty() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday.set(weekday);
    }

    public String getClasshour() {
        return classhour.get();
    }

    public SimpleStringProperty classhourProperty() {
        return classhour;
    }

    public void setClasshour(String classhour) {
        this.classhour.set(classhour);
    }
}
