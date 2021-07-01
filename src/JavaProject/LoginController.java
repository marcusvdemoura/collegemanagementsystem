package JavaProject;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415
Valeria Cardoso da Paz – 21214
Alexson Oliveira Silva – 21643




 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import JavaProject.entities.*;

import JavaProject.queriesSQL.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import JavaProject.tableviewPOJO.UserLoginPOJO;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    private LoginQueries loginQueries = new LoginQueries();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Admin admin;
    private Student student;
    private Lecturer lecturer;

    private static String userId;

    @FXML
    private ChoiceBox<String> myChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField idTextField;
    @FXML
    private PasswordField passwordTextField;

    private String loginOptions[] = {"Admin", "Student", "Lecturer"};

    @FXML
    private Button loginButton = new Button();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myChoiceBox.getItems().addAll(loginOptions);

    }

    @FXML
    private void toLogin(ActionEvent event) throws Exception {

        String userId = idTextField.getText();
        String password = passwordTextField.getText();


        if (myChoiceBox.getValue().toString().equals("Admin")) {

            admin = null;


            ResultSet result = loginQueries.loginAdmin(userId, password);
            while (result.next()) {
                this.admin = new Admin(result.getString("firstName"), result.getString("lastName"),
                        result.getString("gender"), result.getString("phoneNumber"), result.getString("dob"),
                        result.getString("emailAddress"), result.getString("adminID"), result.getString("password"));
            }

            if (admin != null) {


                if (admin.getPassword().equals(password) && admin.getId().equals(userId)) {


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainPage.fxml"));
                    Parent root = loader.load();

                    AdminMainPage adminMainPage = loader.getController();
                    adminMainPage.setAdmin(admin);


                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
//                    throw new Exception("Wrong password");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Admin user doesn't exist OR wrong password");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Admin user doesn't exist OR wrong password");
                alert.show();
            }


        } else if (myChoiceBox.getValue().toString().equals("Student")) {
            student = null;


            ResultSet result = loginQueries.loginStudent(userId, password);

            while (result.next()) {
                this.student = new Student(result.getString("firstName"), result.getString("lastName"),
                        result.getString("gender"), result.getString("phone"), result.getString("dob"),
                        result.getString("emailAddress"), result.getString("idstudent"), result.getString("password"),
                        result.getString("collegeBranch"), result.getString("course"), result.getBoolean("isPaidFull"),
                        result.getString("courseYear"));
            }

            if (student != null) {
                if (student.getPassword().equals(password) && student.getId().equals(userId)) {


                    UserLoginPOJO.setUserFullName(student.getFirstName() + " " + student.getLastName());
                    UserLoginPOJO.setUserID(student.getId());
                    UserLoginPOJO.setStudentYear(Integer.parseInt(student.getCourseYear()));
                    UserLoginPOJO.setStudentCourse(student.getCourseName());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage_Student.fxml"));
                    Parent root = loader.load();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Student user doesn't exist OR wrong password");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Student user doesn't exist OR wrong password");
                alert.show();
            }
        } else if (myChoiceBox.getValue().toString().equals("Lecturer")) {
            lecturer = null;


            ResultSet result = loginQueries.loginLecturer(userId, password);

            while (result.next()) {
                this.lecturer = new Lecturer(result.getString("firstName"), result.getString("lastName"),
                        result.getString("gender"), result.getString("phone"), result.getString("dob"),
                        result.getString("emailAddress"), result.getString("idlecturer"), result.getString("password"));
            }

            if (lecturer != null) {
                if (lecturer.getPassword().equals(password) && lecturer.getId().equals(userId)) {


                    UserLoginPOJO.setUserID(lecturer.getId());
                    UserLoginPOJO.setUserFullName(lecturer.getFirstName() + " " + lecturer.getLastName());


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage_Lecturer.fxml"));
                    Parent root = loader.load();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    System.setProperty("userId", lecturer.getId());
                    scene = new Scene(root);
                    stage.setScene(scene);

                    stage.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Lecturer user doesn't exist OR wrong password");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Lecturer user doesn't exist OR wrong password");
                alert.show();
            }
        }
    }


    public Admin getAdmin() {
        return admin;
    }

    private void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Student getStudent() {
        return student;
    }

    private void setStudent(Student student) {
        this.student = student;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    private void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }


}

