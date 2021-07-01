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

public class ModuleMaster {

    private final SimpleStringProperty moduleName = new SimpleStringProperty();
    private final SimpleStringProperty courseName = new SimpleStringProperty();
    private final SimpleStringProperty idlecturer = new SimpleStringProperty();
    private final SimpleStringProperty weekday = new SimpleStringProperty();
    private final SimpleStringProperty classhour = new SimpleStringProperty();

    public ModuleMaster() {
    }

    public String getModuleName() {
        return moduleName.get();
    }

    public SimpleStringProperty moduleNameProperty() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName.set(moduleName);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getIdlecturer() {
        return idlecturer.get();
    }

    public SimpleStringProperty idlecturerProperty() {
        return idlecturer;
    }

    public void setIdlecturer(String idlecturer) {
        this.idlecturer.set(idlecturer);
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
