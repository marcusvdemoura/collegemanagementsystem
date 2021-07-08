package JavaProject;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415



 */

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import JavaProject.queriesSQL.UsefulVariables;
import JavaProject.tableviewPOJO.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageStudent implements Initializable {


    @FXML
    private Label welcomeMessage1;

    @FXML
    private Label welcomeMessage2;

    @FXML
    private Label welcomeMessage3;

    @FXML
    private Label welcomeMessage4;

    @FXML
    private Button btnQuit1;

    @FXML
    private ChoiceBox<String> assignmentGetModule;

    @FXML
    private TableView<AssigmentMaster> tableViewAssignment;

    @FXML
    private TableColumn<AssigmentMaster, Integer> col_assignmentId;

    @FXML
    private TableColumn<AssigmentMaster, String> col_assignmentModule;

    @FXML
    private TableColumn<AssigmentMaster, String> col_assignmentDate;

    @FXML
    private TableColumn<AssigmentMaster, String> col_assignmentDescription;

    @FXML
    private Button btnQuit2;

    @FXML
    private ChoiceBox<String> gradesGetModule;

    @FXML
    private TableView<GradesMaster> tableViewGrades;

    @FXML
    private TableColumn<GradesMaster, Integer> col_gradesId;

    @FXML
    private TableColumn<GradesMaster, String> col_gradesStudentId;

    @FXML
    private TableColumn<GradesMaster, String> col_gradesModule;

    @FXML
    private TableColumn<GradesMaster, Double> col_gradesGrades;

    @FXML
    private Button btnQuit3;

    @FXML
    private ChoiceBox<String> examsGetModule;

    @FXML
    private TableView<ExamsMaster> tableViewExams;

    @FXML
    private TableColumn<ExamsMaster, Integer> col_examsId;

    @FXML
    private TableColumn<ExamsMaster, String> col_examsModule;

    @FXML
    private TableColumn<ExamsMaster, String> col_examsDate;


    @FXML
    private TableView<TimetablePOJO> tableViewTimetable;

    @FXML
    private TableColumn<TimetablePOJO, String> col_timetableModule;

    @FXML
    private TableColumn<TimetablePOJO, String> col_timetableLecturer;

    @FXML
    private TableColumn<TimetablePOJO, String> col_timetableWeekday;

    @FXML
    private TableColumn<TimetablePOJO, String> col_timetableClassHour;


    private String studentName = UserLoginPOJO.getUserFullName();
    private String studentCourse = UserLoginPOJO.getStudentCourse();
    private String studentId = UserLoginPOJO.getUserID();
    private Integer studentYear = UserLoginPOJO.getStudentYear();
    private String module = "";

    public MainPageStudent() throws SQLException {
    }

    private void setWelcomeMessage(String message) {
        welcomeMessage1.setText("Welcome " + message);
        welcomeMessage2.setText("Welcome " + message);
        welcomeMessage3.setText("Welcome " + message);
        welcomeMessage4.setText("Welcome " + message);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setWelcomeMessage(studentName);

        try {
            assignmentGetModule.getItems().addAll(getModules());
            gradesGetModule.getItems().addAll(getModules());
            examsGetModule.getItems().addAll(getModules());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        assignmentGetModule.setOnAction(actionEvent -> {

            module = assignmentGetModule.getValue();
            refreshAssignmentList(module);
        });

        gradesGetModule.setOnAction(actionEvent -> {

            module = gradesGetModule.getValue();
            refreshGradeList(module);

        });

        examsGetModule.setOnAction(actionEvent -> {

            module = examsGetModule.getValue();
            refreshExamsList(module);

        });


        refreshTimetableList();


    }


    private ArrayList<String> getModules() throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql1 = "select * from courseYear WHERE year = ? AND course = ?";
        UsefulVariables.getModules = UsefulVariables.con.prepareStatement(sql1);
        UsefulVariables.getModules.setInt(1, studentYear);
        UsefulVariables.getModules.setString(2, studentCourse);
        ResultSet resultModulesinYear = UsefulVariables.getModules.executeQuery();

        ArrayList<String> allModules = new ArrayList<>();

        while (resultModulesinYear.next()) {
            allModules.add(resultModulesinYear.getString("moduleName"));
        }


        return allModules;
    }


    // ASSIGNMENT TABLE VIEW


    private ObservableList<AssigmentMaster> getAssignmentList(String moduleSubject) throws ClassNotFoundException, SQLException {


        ObservableList<AssigmentMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from assignment WHERE moduleSubject = ?";

        UsefulVariables.getAllAssignments = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllAssignments.setString(1, moduleSubject);
        ResultSet result = UsefulVariables.getAllAssignments.executeQuery();

        while (result.next()) {

            AssigmentMaster assigmentMaster = new AssigmentMaster();
            assigmentMaster.setId(result.getInt("idassignment"));
            assigmentMaster.setDate(result.getString("dueDate"));
            assigmentMaster.setDescription(result.getString("description"));
            assigmentMaster.setModule(result.getString("moduleSubject"));
            assigmentMaster.setDescription(result.getString("description"));


            list.add(assigmentMaster);


        }


        return list;


    }


    public void getSelectedAssignment(MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        int indexAssignments = -1;

        indexAssignments = tableViewAssignment.getSelectionModel().getSelectedIndex();
        if (indexAssignments <= -1) {
            return;
        }

        int assignmentId = col_assignmentId.getCellData(indexAssignments);

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from assignment WHERE idassignment = ?";
        UsefulVariables.getSelectedAssignment = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getSelectedAssignment.setInt(1, assignmentId);
        ResultSet resultAllAssignments = UsefulVariables.getSelectedAssignment.executeQuery();


        while (resultAllAssignments.next()) {
            assignmentGetModule.setValue(resultAllAssignments.getString("moduleSubject"));


        }


    }

    private void refreshAssignmentList(String module) {

        col_assignmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_assignmentModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_assignmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_assignmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));


        try {
            tableViewAssignment.getItems().setAll(getAssignmentList(module));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // TAB GRADES


    private ObservableList<GradesMaster> getGradesList(String moduleSubject) throws ClassNotFoundException, SQLException {


        ObservableList<GradesMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from gradesModule WHERE studentid = ? AND modulename = ?";

        UsefulVariables.getAllGrades = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllGrades.setString(1, studentId);
        UsefulVariables.getAllGrades.setString(2, moduleSubject);
        ResultSet result = UsefulVariables.getAllGrades.executeQuery();

        while (result.next()) {

            GradesMaster gradesMaster = new GradesMaster();
            gradesMaster.setId(result.getInt("gradeid"));
            gradesMaster.setStudentid(result.getString("studentid"));
            gradesMaster.setModule(result.getString("modulename"));
            gradesMaster.setGrade(result.getDouble("grade"));


            list.add(gradesMaster);


        }


        return list;


    }


    public void getSelectedGrade(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        int indexGrades = -1;

        indexGrades = tableViewGrades.getSelectionModel().getSelectedIndex();
        if (indexGrades <= -1) {
            return;
        }

        int gradesId = col_gradesId.getCellData(indexGrades);

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from gradesModule WHERE gradeid = ?";
        UsefulVariables.getSelectedGrade = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getSelectedGrade.setInt(1, gradesId);
        ResultSet resultGetSelectedGrade = UsefulVariables.getSelectedGrade.executeQuery();


        while (resultGetSelectedGrade.next()) {
            gradesGetModule.setValue(resultGetSelectedGrade.getString("modulename"));


        }


    }

    private void refreshGradeList(String module) {

        col_gradesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_gradesStudentId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        col_gradesModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_gradesGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));


        try {
            tableViewGrades.getItems().setAll(getGradesList(module));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private ObservableList<ExamsMaster> getExamsList(String module) throws ClassNotFoundException, SQLException {


        ObservableList<ExamsMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from exam WHERE module = ?";

        UsefulVariables.getSelectedExam = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getSelectedExam.setString(1, module);
        ResultSet result = UsefulVariables.getSelectedExam.executeQuery();

        while (result.next()) {

            ExamsMaster examsMaster = new ExamsMaster();
            examsMaster.setId(result.getInt("idexam"));
            examsMaster.setModule(result.getString("module"));
            examsMaster.setDate(result.getString("date"));


            list.add(examsMaster);


        }


        return list;


    }


    public void getSelectedExam(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        int indexExam = -1;

        indexExam = tableViewExams.getSelectionModel().getSelectedIndex();
        if (indexExam <= -1) {
            return;
        }

        int examsId = col_examsId.getCellData(indexExam);

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from exam WHERE idexam = ?";
        UsefulVariables.getSelectedExam = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getSelectedExam.setInt(1, examsId);
        ResultSet resultGetExam = UsefulVariables.getSelectedExam.executeQuery();


        while (resultGetExam.next()) {
            examsGetModule.setValue(resultGetExam.getString("module"));

        }


    }

    private void refreshExamsList(String module) {

        col_examsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_examsModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_examsDate.setCellValueFactory(new PropertyValueFactory<>("date"));


        try {
            tableViewExams.getItems().setAll(getExamsList(module));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private ObservableList<TimetablePOJO> getTimeTable() throws ClassNotFoundException, SQLException {


        ObservableList<TimetablePOJO> list = FXCollections.observableArrayList();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql1 = "Select moduleName from courseYear WHERE year = ? AND course = ?";

        PreparedStatement statementOne = UsefulVariables.con.prepareStatement(sql1);

        statementOne.setInt(1, studentYear);
        statementOne.setString(2, studentCourse);
        ResultSet resultOne = statementOne.executeQuery();

        PreparedStatement statementTwo;

        while (resultOne.next()) {

            TimetablePOJO timetablePOJO = new TimetablePOJO();

            String sql2 = "SELECT * FROM module WHERE subject = ?";
            statementTwo = UsefulVariables.con.prepareStatement(sql2);
            statementTwo.setString(1, resultOne.getString("moduleName"));
            ResultSet resultTwo = statementTwo.executeQuery();

            while (resultTwo.next()) {
                timetablePOJO.setModule(resultOne.getString("moduleName"));
                timetablePOJO.setLecturer(resultTwo.getString("idlecturer"));
                timetablePOJO.setWeekday(resultTwo.getString("weekDay"));
                timetablePOJO.setClasshour(resultTwo.getString("classHour"));
                list.add(timetablePOJO);
            }


        }


        return list;
    }


    private void refreshTimetableList() {


        col_timetableModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_timetableLecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        col_timetableWeekday.setCellValueFactory(new PropertyValueFactory<>("weekday"));
        col_timetableClassHour.setCellValueFactory(new PropertyValueFactory<>("classhour"));


        try {
            tableViewTimetable.getItems().setAll(getTimeTable());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    private void quitProgram(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Good Bye " + studentName);
        alert.show();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


}

