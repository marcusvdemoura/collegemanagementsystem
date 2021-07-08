package JavaProject.tableviewPOJO;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415




 */

import javafx.beans.property.SimpleStringProperty;

public class LecturerMaster {

    private final SimpleStringProperty lecturerId = new SimpleStringProperty();
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleStringProperty gender = new SimpleStringProperty();
    private final SimpleStringProperty phone = new SimpleStringProperty();

    public LecturerMaster() {
    }

    public String getLecturerId() {
        return lecturerId.get();
    }

    public SimpleStringProperty lecturerIdProperty() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId.set(lecturerId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
