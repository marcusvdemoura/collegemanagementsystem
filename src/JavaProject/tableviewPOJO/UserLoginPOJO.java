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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserLoginPOJO {


    private static SimpleStringProperty userID = new SimpleStringProperty();
    private static SimpleStringProperty userFullName = new SimpleStringProperty();

    private static SimpleIntegerProperty studentYear = new SimpleIntegerProperty();

    private static SimpleStringProperty studentCourse = new SimpleStringProperty();

    public static String getUserID() {
        return userID.get();
    }

    public static SimpleStringProperty userIDProperty() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserLoginPOJO.userID.set(userID);
    }

    public static String getUserFullName() {
        return userFullName.get();
    }

    public static SimpleStringProperty userFullNameProperty() {
        return userFullName;
    }

    public static void setUserFullName(String userFullName) {
        UserLoginPOJO.userFullName.set(userFullName);
    }

    public static int getStudentYear() {
        return studentYear.get();
    }

    public static SimpleIntegerProperty studentYearProperty() {
        return studentYear;
    }

    public static void setStudentYear(int studentYear) {
        UserLoginPOJO.studentYear.set(studentYear);
    }

    public static String getStudentCourse() {
        return studentCourse.get();
    }

    public static SimpleStringProperty studentCourseProperty() {
        return studentCourse;
    }

    public static void setStudentCourse(String studentCourse) {
        UserLoginPOJO.studentCourse.set(studentCourse);
    }
}
