package JavaProject;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415



 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import JavaProject.entities.Lecturer;
import JavaProject.queriesSQL.UsefulVariables;
import JavaProject.tableviewPOJO.*;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainPageLecturer implements Initializable {



    @FXML
    private DatePicker setAssignmentGetDate;

    @FXML
    private TextArea setAssignmentGetDescription;

    @FXML
    private ChoiceBox<String> setAssignmentGetModule;

    @FXML
    private Label labelWelcome1;

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
    private ChoiceBox<String> gradesGetModule;

    @FXML
    private ChoiceBox<String> gradesGetStudent;

    @FXML
    private TextField gradesGetGrade;

    @FXML
    private Label labelWelcome2;

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
    private ChoiceBox<String> examsModule;

    @FXML
    private DatePicker examsDate;

    @FXML
    private Label labelWelcome3;

    @FXML
    private TableView<ExamsMaster> tableViewExams;

    @FXML
    private TableColumn<ExamsMaster, Integer> col_examsId;

    @FXML
    private TableColumn<ExamsMaster, String> col_examsModule;

    @FXML
    private TableColumn<ExamsMaster, String> col_examsDate;


    public MainPageLecturer() throws SQLException, ClassNotFoundException {

    }

    private void setWelcomeMessage(String message) {
        labelWelcome1.setText("Welcome " + message);
        labelWelcome2.setText("Welcome " + message);
        labelWelcome3.setText("Welcome " + message);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setWelcomeMessage(UserLoginPOJO.getUserFullName());

        try {
            gradesGetModule.getItems().addAll(getModules());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            setAssignmentGetModule.getItems().addAll(getModules());
            examsModule.getItems().addAll(getModules());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        refreshAssignmentList();

        refreshGradeList();

        refreshExamsList();


        gradesGetModule.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                gradesGetStudent.getItems().clear();
                gradesGetStudent.setDisable(true);
            } else {
                List<String> list = null;
                try {
                    list = getStudents(gradesGetModule.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                gradesGetStudent.getItems().setAll(list);
                gradesGetStudent.setDisable(false);
            }
        });


    }


    // TAB ASSIGNMENT

    private ArrayList<String> getModules() throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from module WHERE idlecturer = ?";
        UsefulVariables.getModules = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getModules.setString(1, UserLoginPOJO.getUserID());
        ResultSet resultBranches = UsefulVariables.getModules.executeQuery();

        ArrayList<String> allModules = new ArrayList<>();

        while (resultBranches.next()) {
            allModules.add(resultBranches.getString("subject"));
        }


        return allModules;
    }


    @FXML
    void createAssignment(ActionEvent event) throws SQLException, ClassNotFoundException {

        String dueDate = setAssignmentGetDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String description = setAssignmentGetDescription.getText();
        String module = setAssignmentGetModule.getValue();
        String lecturerid = UserLoginPOJO.getUserID();

        Lecturer.createAssignment(dueDate, description, module, lecturerid);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Assignment created");
        alert.show();

        refreshAssignmentList();

       toClear();

    }

    private ObservableList<AssigmentMaster> getAssignmentList() throws ClassNotFoundException, SQLException {


        ObservableList<AssigmentMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from assignment WHERE lecturerId = ?";

        UsefulVariables.getAllAssignments = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllAssignments.setString(1, UserLoginPOJO.getUserID());
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


    public void getSelectedAssignment(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

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
            setAssignmentGetModule.setValue(resultAllAssignments.getString("moduleSubject"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(resultAllAssignments.getString("dueDate"), formatter);
            setAssignmentGetDate.setValue(localDate);
            setAssignmentGetDescription.setText(resultAllAssignments.getString("description"));


        }


    }

    private void refreshAssignmentList() {

        col_assignmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_assignmentModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_assignmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_assignmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));


        try {
            tableViewAssignment.getItems().setAll(getAssignmentList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    private void toEditAssignment() throws Exception {

        int indexAssignments = -1;

        indexAssignments = tableViewAssignment.getSelectionModel().getSelectedIndex();
        if (indexAssignments <= -1) {
            return;
        }

        int assignmentId = col_assignmentId.getCellData(indexAssignments);


        Lecturer.editAssignment(assignmentId, setAssignmentGetDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                setAssignmentGetDescription.getText(), setAssignmentGetModule.getValue());


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Assignment edited");
        alert.show();
        refreshAssignmentList();

        toClear();



    }

    @FXML
    private void toDeleteAssignment() throws Exception {

        int indexAssignments = -1;

        indexAssignments = tableViewAssignment.getSelectionModel().getSelectedIndex();
        if (indexAssignments <= -1) {
            return;
        }

        int assignmentId = col_assignmentId.getCellData(indexAssignments);

        Lecturer.deleteAssignment(assignmentId);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Assignment Deleted");
        alert.show();
        refreshAssignmentList();

        refreshAssignmentList();


        toClear();

    }


    // TAB GRADES


    @FXML
    void setGrades(ActionEvent event) throws Exception {

        String getModule = gradesGetModule.getValue();
        String studentid = gradesGetStudent.getValue();
        Double grade = Double.parseDouble(gradesGetGrade.getText());
        String lecturerid = UserLoginPOJO.getUserID();

        Lecturer.setGrade(studentid, getModule, grade, lecturerid);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Grade added for Student");
        alert.show();

        refreshGradeList();

        toClear();


    }


    private ObservableList<GradesMaster> getGradesList() throws ClassNotFoundException, SQLException {


        ObservableList<GradesMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from gradesModule WHERE lecturerid = ?";

        UsefulVariables.getAllGrades = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllGrades.setString(1, UserLoginPOJO.getUserID());
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
            gradesGetStudent.setValue(resultGetSelectedGrade.getString("studentid"));
            gradesGetGrade.setText(resultGetSelectedGrade.getString("grade"));


        }


    }

    private void refreshGradeList() {

        col_gradesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_gradesStudentId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        col_gradesModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_gradesGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));


        try {
            tableViewGrades.getItems().setAll(getGradesList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    void editGrades(ActionEvent event) throws Exception {


        gradesGetStudent.valueProperty().addListener((e) -> {
            gradesGetStudent.setDisable(true);
        });

        String module = gradesGetModule.getValue();

        Double grade = Double.parseDouble(gradesGetGrade.getText());


        int indexGrades = -1;

        indexGrades = tableViewGrades.getSelectionModel().getSelectedIndex();
        if (indexGrades <= -1) {
            return;
        }


        int gradesId = col_gradesId.getCellData(indexGrades);


        Lecturer.editGrade(gradesId, grade, module);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Grade edited for Student");
        alert.show();

        refreshGradeList();



        toClear();


    }


    // TAB EXAMS


    @FXML
    void createExams(ActionEvent event) throws Exception {

        String getModule = examsModule.getValue();
        String lecturerid = UserLoginPOJO.getUserID();
        String examDate = examsDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Lecturer.createExam(examDate, getModule, lecturerid);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Exam created");
        alert.show();

        refreshExamsList();

        toClear();
    }

    @FXML
    void editExam(ActionEvent event) throws Exception {

        String getModule = examsModule.getValue();
        String examDate = examsDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int indexExam = -1;

        indexExam = tableViewGrades.getSelectionModel().getSelectedIndex();
        if (indexExam <= -1) {
            return;
        }

        int examsId = col_examsId.getCellData(indexExam);


        Lecturer.editExam(examDate, getModule, examsId);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Exam edited");
        alert.show();

        refreshExamsList();

        toClear();

    }

    @FXML
    void deleteExam(ActionEvent event) throws Exception {

        int indexExam = -1;

        indexExam = tableViewGrades.getSelectionModel().getSelectedIndex();
        if (indexExam <= -1) {
            return;
        }

        int examsId = col_examsId.getCellData(indexExam);

        Lecturer.deleteExam(examsId);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Exam deleted");
        alert.show();

        refreshExamsList();

        toClear();



    }


    private ObservableList<ExamsMaster> getExamsList() throws ClassNotFoundException, SQLException {


        ObservableList<ExamsMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from exam WHERE lecturerId = ?";

        UsefulVariables.getSelectedExam = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getSelectedExam.setString(1, UserLoginPOJO.getUserID());
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
            examsModule.setValue(resultGetExam.getString("module"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(resultGetExam.getString("date"), formatter);
            examsDate.setValue(localDate);

        }


    }

    private void refreshExamsList() {

        col_examsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_examsModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        col_examsDate.setCellValueFactory(new PropertyValueFactory<>("date"));


        try {
            tableViewExams.getItems().setAll(getExamsList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




    // ------------------------------------ general methods --------------------------------------


    @FXML
    private void toClear() {

        setAssignmentGetModule.getSelectionModel().clearSelection();
        setAssignmentGetDate.getEditor().clear();
        setAssignmentGetDescription.clear();


        gradesGetModule.getSelectionModel().clearSelection();
        gradesGetStudent.getSelectionModel().clearSelection();
        gradesGetGrade.clear();


        examsModule.getSelectionModel().clearSelection();
        examsDate.getEditor().clear();

    }


    private ArrayList<String> getStudents(String moduleName) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql1 = "select * from courseYear WHERE moduleName = ?";
        PreparedStatement getcourseYearList = UsefulVariables.con.prepareStatement(sql1);
        getcourseYearList.setString(1, moduleName);
        ResultSet firstResult = getcourseYearList.executeQuery();


        String course = "";
        Integer year = 0;

        while (firstResult.next()) {


            course = firstResult.getString("course");
            year = firstResult.getInt("year");

        }

        ArrayList<String> allStudents = new ArrayList<>();

        String sql2 = "SELECT idstudent FROM student WHERE course = ? AND courseYear = ?";
        PreparedStatement getListStudents = UsefulVariables.con.prepareStatement(sql2);
        getListStudents.setString(1, course);
        getListStudents.setInt(2, year);
        ResultSet secondResult = getListStudents.executeQuery();

        while (secondResult.next()) {
            allStudents.add(secondResult.getString("idstudent"));
        }


        return allStudents;


    }


}
