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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import JavaProject.entities.*;

import JavaProject.queriesSQL.UsefulVariables;
import JavaProject.tableviewPOJO.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminMainPage implements Initializable {


    private Admin admin;

    @FXML
    private ChoiceBox<String> studentsInDebt;

    @FXML
    private TextField branchUnitInsert, createLecturerId, lecturerFirstName, lecturerLastName, lecturerPhone, lecturerEmail, lecturerpassword;
    @FXML
    private TextField branchAddressInsert, createModuleName, createModuleAddHour, insertStudentFirstName, insertStudentLastName, insertStudentPhone;
    @FXML
    private ChoiceBox<String> createCourseBranchChoice, createModuleAddBranch, studentInBranch;
    @FXML
    private TextField courseNameInput;
    @FXML
    private TextField coursePrice;
    @FXML
    private ChoiceBox<String> courseYearNameInput, genderLecturer, genderStudent;

    @FXML
    private TextField courseYearInput, studentEmailInsert, studentPasswordInsert, insertStudentId;
    @FXML
    private ChoiceBox<String> courseYearModuleInput, ModuleInCourse, studentInCourse, courseYearBranchInput;
    @FXML
    private ChoiceBox<String> createModuleAddLecturer, addStudentCourseYear;

    @FXML
    private DatePicker lecturerbirth, studentdobInsert;

    @FXML
    private Label labelAmountOfInstallments;

    @FXML
    private ChoiceBox<String> createModuleAddWeekDay;
    private String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    @FXML
    private ChoiceBox<String> studentIsPaid;




    public AdminMainPage() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderLecturer.getItems().addAll("Male", "Female");
        genderStudent.getItems().addAll("Male", "Female");
        studentIsPaid.getItems().addAll("Yes", "No");
        createModuleAddWeekDay.getItems().addAll(weekdays);

        try {
            studentInBranch.getItems().addAll(getBranches());
            createModuleAddBranch.getItems().addAll(getBranches());
            courseYearBranchInput.getItems().addAll(getBranches());
            createCourseBranchChoice.getItems().addAll(getBranches());
            studentsInDebt.getItems().addAll(getStudentsToPay());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            createModuleAddLecturer.getItems().addAll(getLecturersId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // GET CHOICES BOX FOR STUDENT
        studentInBranch.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                studentInCourse.getItems().clear();
                studentInCourse.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<String> list = null;
                try {
                    list = getCourses(studentInBranch.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                studentInCourse.getItems().setAll(list);
                studentInCourse.setDisable(false);
            }
        });

        studentInCourse.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                addStudentCourseYear.getItems().clear();
                addStudentCourseYear.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<String> list = null;
                try {
                    list = getCourseYears(studentInCourse.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addStudentCourseYear.getItems().setAll(list);
                addStudentCourseYear.setDisable(false);
            }
        });

        //get choice boxes for modules

        createModuleAddBranch.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                ModuleInCourse.getItems().clear();
                ModuleInCourse.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<String> list = null;
                try {
                    list = getCourses(createModuleAddBranch.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ModuleInCourse.getItems().setAll(list);
                ModuleInCourse.setDisable(false);
            }
        });

        courseYearBranchInput.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                courseYearNameInput.getItems().clear();
                courseYearNameInput.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<String> list = null;
                try {
                    list = getCourses(courseYearBranchInput.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                courseYearNameInput.getItems().setAll(list);
                courseYearNameInput.setDisable(false);
            }
        });


        courseYearNameInput.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                courseYearModuleInput.getItems().clear();
                courseYearModuleInput.setDisable(true);
            } else {
                // sample code, adapt as needed:
                List<String> list = null;
                try {
                    list = getModules(courseYearNameInput.getValue());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                courseYearModuleInput.getItems().setAll(list);
                courseYearModuleInput.setDisable(false);
            }
        });


        // fetch data into table views from db

        refreshStudentTable();
        refreshLecturerTable();
        refreshCollegeBranch();
        refreshCourse();
        refreshModule();
        refreshCourseYear();



        studentsInDebt.setOnAction(actionEvent -> {

            String studentId = studentsInDebt.getValue();

            try {
                labelAmountOfInstallments.setText(String.format
                        ("The student has to pay %d more installments", getAmountOfPayments(studentId)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            refreshPaymentLog(studentId);

        });





    }


    // CREATE STUDENT, LECTURER

    @FXML
    private void toCreateStudent() throws SQLException, ClassNotFoundException, IOException {

        Integer installments[] = {1, 2, 3, 4, 5, 6};
        List<Integer> dialogData;
        Integer numberInstallments = 0;


        admin.createStudents(insertStudentFirstName.getText(), insertStudentLastName.getText(), genderStudent.getValue(), insertStudentPhone.
                        getText(), studentdobInsert.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                studentEmailInsert.getText(), insertStudentId.getText(), studentPasswordInsert.getText(), studentInBranch.getValue(),
                studentInCourse.getValue(), Integer.parseInt(addStudentCourseYear.getValue()),
                studentIsPaid.getValue().equals("Yes") ? true : false);

        if (studentIsPaid.getValue().equals("No")) {

            dialogData = Arrays.asList(installments);
            Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
            dialog.setTitle("Installments");
            dialog.setHeaderText("Select the number of installments: ");
            Optional<Integer> result = dialog.showAndWait();
            String selected = "cancelled.";

            if (result.isPresent()) {
                selected = String.valueOf(result.get());
                numberInstallments = result.get();
            }

            admin.setInstallments(numberInstallments, insertStudentId.getText());

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Student created");
        alert.show();

        studentsInDebt.getItems().add(insertStudentId.getText());

        insertStudentFirstName.clear();
        insertStudentLastName.clear();
        genderStudent.getSelectionModel().clearSelection();
        insertStudentPhone.clear();
        studentdobInsert.getEditor().clear();
        studentEmailInsert.clear();
        insertStudentId.clear();
        studentPasswordInsert.clear();
        studentInBranch.getSelectionModel().clearSelection();
        studentInCourse.getSelectionModel().clearSelection();
        addStudentCourseYear.getSelectionModel().clearSelection();
        studentIsPaid.getSelectionModel().clearSelection();

        refreshStudentTable();



    }

    @FXML
    private void toCreateLecturer() throws Exception {

        admin.createLecturer(lecturerFirstName.getText(), lecturerLastName.getText(), genderLecturer.getValue(), lecturerPhone.getText(),
                lecturerbirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), lecturerEmail.getText(),
                createLecturerId.getText(), lecturerpassword.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Lecturer created");
        alert.show();

        createModuleAddLecturer.getItems().add(createLecturerId.getText());

        lecturerFirstName.clear();
        lecturerLastName.clear();
        genderLecturer.getSelectionModel().clearSelection();
        lecturerPhone.clear();
        lecturerbirth.getEditor().clear();
        lecturerEmail.clear();
        createLecturerId.clear();
        lecturerpassword.clear();

        refreshLecturerTable();


    }


    // DELETE STUDENT, LECTURER
    @FXML
    private void toDeleteStudent() throws SQLException, ClassNotFoundException {

        admin.deleteStudent(insertStudentId.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Student deleted");
        alert.show();

        studentsInDebt.getItems().remove(insertStudentId.getText());


        toClearInputs();

        refreshStudentTable();

    }

    @FXML
    private void toDeleteLecturer() throws SQLException, ClassNotFoundException {

        admin.deleteLecturer(createLecturerId.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Lecturer deleted");
        alert.show();

        lecturerFirstName.clear();
        lecturerLastName.clear();
        genderLecturer.getSelectionModel().clearSelection();
        lecturerPhone.clear();
        lecturerbirth.getEditor().clear();
        lecturerEmail.clear();
        createLecturerId.clear();
        lecturerpassword.clear();

        refreshLecturerTable();


    }


    // CREATE COURSES AND COURSES YEAR


    @FXML
    private void toCreateCourse() throws ClassNotFoundException, SQLException {

        getAdmin().createCourse(createCourseBranchChoice.getValue(), courseNameInput.getText(),
                Double.valueOf(coursePrice.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Course created");
        alert.show();


        createCourseBranchChoice.getSelectionModel().clearSelection();
        courseNameInput.clear();
        coursePrice.clear();
        refreshCourse();

    }

    @FXML
    private void toCreateCourseYear() throws SQLException, ClassNotFoundException {

        String year = courseYearInput.getText();
        String courseName = courseYearNameInput.getValue();
        String module = courseYearModuleInput.getValue();


        admin.createCourseYear(year, courseName, module);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Course Year created");
        alert.show();


        courseYearBranchInput.getSelectionModel().clearSelection();
        courseYearInput.clear();
        courseYearNameInput.getSelectionModel().clearSelection();
        courseYearModuleInput.getSelectionModel().clearSelection();

        refreshCourseYear();


    }

    // DELETE COURSE

    @FXML
    private void toDeleteCourse() throws ClassNotFoundException, SQLException {

        getAdmin().deleteCourse(createCourseBranchChoice.getValue(), courseNameInput.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");

        alert.setHeaderText("Course deleted");
        alert.show();

        createCourseBranchChoice.getSelectionModel().clearSelection();
        courseNameInput.clear();
        coursePrice.clear();

        refreshCourse();



    }


    // CREATE MODULES

    @FXML
    private void toCreateModule() throws ClassNotFoundException, SQLException {

        getAdmin().createModule(createModuleName.getText(), ModuleInCourse.getValue(), createModuleAddBranch.getValue(), createModuleAddWeekDay.getValue(),
                createModuleAddHour.getText(), createModuleAddLecturer.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Module created");
        alert.show();

        toClearInputs();

        refreshModule();
    }

    // DELETE MODULES

    @FXML
    private void toDeleteModules() throws SQLException, ClassNotFoundException {

        admin.deleteModule(createModuleName.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Module deleted");
        alert.show();

        createModuleAddWeekDay.getSelectionModel().clearSelection();
        createModuleAddLecturer.getSelectionModel().clearSelection();
        ModuleInCourse.getSelectionModel().clearSelection();
        createModuleName.clear();
        createModuleAddBranch.getSelectionModel().clearSelection();
        createModuleName.clear();

        refreshModule();


    }

    // CREATE COLLEGE BRANCHES

    @FXML
    private void toCreateCollegeBranch() throws SQLException, ClassNotFoundException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO collegeBranches(unit, address) VALUES (?, ?)";
        UsefulVariables.createBranchQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createBranchQuery.setString(1, branchUnitInsert.getText());
        UsefulVariables.createBranchQuery.setString(2, branchAddressInsert.getText());

        UsefulVariables.createBranchQuery.execute();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Branch created");
        alert.show();

        refreshCollegeBranch();

        studentInBranch.getItems().add(branchUnitInsert.getText());
        createModuleAddBranch.getItems().add(branchUnitInsert.getText());
        courseYearBranchInput.getItems().add(branchUnitInsert.getText());
        createCourseBranchChoice.getItems().add(branchUnitInsert.getText());

        branchUnitInsert.clear();
        branchAddressInsert.clear();

    }

    // DELETE COLLEGE BRANCHES

    @FXML
    private void toDeleteBranch() throws SQLException, ClassNotFoundException {

        admin.deleteBranch(branchUnitInsert.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Branch deleted");
        alert.show();

        studentInBranch.getItems().remove(branchUnitInsert.getText());
        createModuleAddBranch.getItems().remove(branchUnitInsert.getText());
        courseYearBranchInput.getItems().remove(branchUnitInsert.getText());
        createCourseBranchChoice.getItems().remove(branchUnitInsert.getText());

        branchUnitInsert.clear();
        branchAddressInsert.clear();

        refreshCollegeBranch();
        refreshCourse();

    }

    private ArrayList<String> getStudentsToPay() throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from student WHERE isPaidFull=?";
        UsefulVariables.addPaymentQuery = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.addPaymentQuery.setBoolean(1, false);
        ResultSet resultStudentsInDebt = UsefulVariables.addPaymentQuery.executeQuery();

        ArrayList<String> studentsToPay = new ArrayList<>();

        while (resultStudentsInDebt.next()) {
            studentsToPay.add(resultStudentsInDebt.getString("idstudent"));
        }


        return studentsToPay;

    }


    private ArrayList<String> getBranches() throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from collegeBranches";
        UsefulVariables.getBranches = UsefulVariables.con.prepareStatement(sql);
        ResultSet resultBranches = UsefulVariables.getBranches.executeQuery();

        ArrayList<String> branchOptions = new ArrayList<>();

        while (resultBranches.next()) {
            branchOptions.add(resultBranches.getString("unit"));
        }


        return branchOptions;
    }

    private ArrayList<String> getCourses(String branchUnit) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "select * from course WHERE collegeBranchUnit = ?";
        UsefulVariables.getCourses = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getCourses.setString(1, branchUnit);
        ResultSet resultBranches = UsefulVariables.getCourses.executeQuery();

        ArrayList<String> allCourses = new ArrayList<>();


        while (resultBranches.next()) {
            allCourses.add(resultBranches.getString("name"));
        }


        return allCourses;


    }

    private ArrayList<String> getLecturersId() throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from lecturer";
        UsefulVariables.getLecturersId = UsefulVariables.con.prepareStatement(sql);
        ResultSet resultBranches = UsefulVariables.getLecturersId.executeQuery();

        ArrayList<String> allLecturers = new ArrayList<>();

        while (resultBranches.next()) {
            allLecturers.add(resultBranches.getString("idlecturer"));
        }


        return allLecturers;
    }

    private ArrayList<String> getModules(String courseName) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from module WHERE course = ?";
        UsefulVariables.getModules = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getModules.setString(1, courseName);
        ResultSet resultBranches = UsefulVariables.getModules.executeQuery();

        ArrayList<String> allModules = new ArrayList<>();

        while (resultBranches.next()) {
            allModules.add(resultBranches.getString("subject"));
        }


        return allModules;
    }

    private ArrayList<String> getCourseYears(String courseName) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from courseYear WHERE course = ?";
        UsefulVariables.getModules = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getModules.setString(1, courseName);
        ResultSet resultBranches = UsefulVariables.getModules.executeQuery();

        ArrayList<String> allYears = new ArrayList<>();
        int count = 0;

        while (resultBranches.next()) {
            for (String year : allYears){
                if (year.equalsIgnoreCase(resultBranches.getString("year"))){
                    count++;
                }
            }

            if(count==0){
                allYears.add(resultBranches.getString("year"));
            }
        }


        return allYears;
    }


    @FXML
    private void toClearInputs() {
        insertStudentFirstName.clear();
        insertStudentLastName.clear();
        genderStudent.getSelectionModel().clearSelection();
        insertStudentPhone.clear();
        studentdobInsert.getEditor().clear();
        studentEmailInsert.clear();
        insertStudentId.clear();
        studentPasswordInsert.clear();
        studentInBranch.getSelectionModel().clearSelection();
        studentInCourse.getSelectionModel().clearSelection();
        addStudentCourseYear.getSelectionModel().clearSelection();
        studentIsPaid.getSelectionModel().clearSelection();


        lecturerFirstName.clear();
        lecturerLastName.clear();
        genderLecturer.getSelectionModel().clearSelection();
        lecturerPhone.clear();
        lecturerbirth.getEditor().clear();
        lecturerEmail.clear();
        createLecturerId.clear();
        lecturerpassword.clear();

        createCourseBranchChoice.getSelectionModel().clearSelection();
        courseNameInput.clear();
        coursePrice.clear();

        createModuleAddWeekDay.getSelectionModel().clearSelection();
        createModuleAddLecturer.getSelectionModel().clearSelection();
        ModuleInCourse.getSelectionModel().clearSelection();
        createModuleName.clear();
        createModuleAddBranch.getSelectionModel().clearSelection();
        createModuleName.clear();
        createModuleAddHour.clear();

        branchUnitInsert.clear();
        branchAddressInsert.clear();

        courseYearBranchInput.getSelectionModel().clearSelection();
        courseYearInput.clear();
        courseYearNameInput.getSelectionModel().clearSelection();
        courseYearModuleInput.getSelectionModel().clearSelection();

        courseYearBranchInput.setDisable(false);
        courseYearNameInput.setDisable(false);


    }


    // TABLE VIEWS CONFIG

    // Student table view

    @FXML
    private TableView<StudentMaster> studentTableView = new TableView<>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentid = new TableColumn<StudentMaster, String>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentFirstName = new TableColumn<>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentLastName = new TableColumn<>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentBranch = new TableColumn<>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentCourse = new TableColumn<>();

    @FXML
    private TableColumn<StudentMaster, String> col_studentYear = new TableColumn<>();

    // Get All Students

    private ObservableList<StudentMaster> getStudentsList() throws ClassNotFoundException, SQLException {


        ObservableList<StudentMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "Select * from student";

        UsefulVariables.getStudents = UsefulVariables.con.prepareStatement(sql);


        ResultSet result = UsefulVariables.getStudents.executeQuery();

        while (result.next()) {

            StudentMaster studentMaster = new StudentMaster();
            studentMaster.setStudentID(result.getString("idstudent"));
            studentMaster.setFirstName(result.getString("firstName"));
            studentMaster.setLastName(result.getString("lastName"));
            studentMaster.setBranch(result.getString("collegeBranch"));
            studentMaster.setCourse(result.getString("course"));
            studentMaster.setYear(result.getInt("courseYear"));

            list.add(studentMaster);


        }


        return list;


    }

    // to edit student data

    int indexStudent = -1;

    public void getSelectedStudent(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexStudent = studentTableView.getSelectionModel().getSelectedIndex();
        if (indexStudent <= -1) {
            return;
        }

        String idstudent = col_studentid.getCellData(indexStudent).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from student WHERE idstudent = ?";
        UsefulVariables.getAllStudents = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllStudents.setString(1, idstudent);
        ResultSet resultAllStudents = UsefulVariables.getAllStudents.executeQuery();


        while (resultAllStudents.next()) {
            insertStudentId.setText(resultAllStudents.getString("idstudent"));
            insertStudentFirstName.setText(resultAllStudents.getString("firstName"));
            insertStudentLastName.setText(resultAllStudents.getString("lastName"));
            insertStudentPhone.setText(resultAllStudents.getString("phone"));
            studentEmailInsert.setText(resultAllStudents.getString("emailAddress"));
            studentPasswordInsert.setText(resultAllStudents.getString("password"));
            String gender = resultAllStudents.getString("gender");
            genderStudent.setValue(gender);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(resultAllStudents.getString("dob"), formatter);
            studentdobInsert.setValue(localDate);
            String branch = resultAllStudents.getString("collegeBranch");
            String course = resultAllStudents.getString("course");
            int courseYear = resultAllStudents.getInt("courseYear");
            String isPaidFull = "";
            if (resultAllStudents.getBoolean("isPaidFull")) {
                isPaidFull = "Yes";
            } else {
                isPaidFull = "No";
            }
            studentInBranch.setValue(branch);
            studentInCourse.setValue(course);
            addStudentCourseYear.setValue(String.valueOf(courseYear));
            studentIsPaid.setValue(isPaidFull);
        }


    }

    @FXML
    private void toEditStudent() throws SQLException, ClassNotFoundException {

        Integer installments[] = {1, 2, 3, 4, 5, 6};
        List<Integer> dialogData;
        Integer numberInstallments = 0;

        indexStudent = studentTableView.getSelectionModel().getSelectedIndex();
        if (indexStudent <= -1) {
            return;
        }

        String idstudent = col_studentid.getCellData(indexStudent).toString();


        admin.editingStudent(insertStudentFirstName.getText(), insertStudentLastName.getText(), genderStudent.getValue(), insertStudentPhone.
                        getText(), studentdobInsert.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                studentEmailInsert.getText(), insertStudentId.getText(), studentPasswordInsert.getText(), studentInBranch.getValue(),
                studentInCourse.getValue(), Integer.parseInt(addStudentCourseYear.getValue()),
                studentIsPaid.getValue().equals("Yes") ? true : false, idstudent);

        if (studentIsPaid.getValue().equals("No")) {

            dialogData = Arrays.asList(installments);
            Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
            dialog.setTitle("Installments");
            dialog.setHeaderText("Select the number of installments: ");
            Optional<Integer> result = dialog.showAndWait();
            String selected = "cancelled.";

            if (result.isPresent()) {
                selected = String.valueOf(result.get());
                numberInstallments = result.get();
            }

            admin.setInstallments(numberInstallments, insertStudentId.getText());

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Student edited");
        alert.show();

        insertStudentFirstName.clear();
        insertStudentLastName.clear();
        genderStudent.getSelectionModel().clearSelection();
        insertStudentPhone.clear();
        studentdobInsert.getEditor().clear();
        studentEmailInsert.clear();
        insertStudentId.clear();
        studentPasswordInsert.clear();
        studentInBranch.getSelectionModel().clearSelection();
        studentInCourse.getSelectionModel().clearSelection();
        addStudentCourseYear.getSelectionModel().clearSelection();
        studentIsPaid.getSelectionModel().clearSelection();

        refreshStudentTable();

    }

    private void refreshStudentTable() {

        col_studentid.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_studentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_studentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_studentBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        col_studentCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_studentYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        try {
            studentTableView.getItems().setAll(getStudentsList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    private TableView<LecturerMaster> lecturerTableView;

    @FXML
    private TableColumn<LecturerMaster, String> col_lecturerid;

    @FXML
    private TableColumn<LecturerMaster, String> col_lecturerFirstName;

    @FXML
    private TableColumn<LecturerMaster, String> col_lecturerLastName;

    @FXML
    private TableColumn<LecturerMaster, String> col_lecturerGender;

    @FXML
    private TableColumn<LecturerMaster, String> col_lecturerPhone;

    // Get All Lecturers

    private ObservableList<LecturerMaster> getLecturersList() throws ClassNotFoundException, SQLException {


        ObservableList<LecturerMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "Select * from lecturer";

        UsefulVariables.getAllLecturers = UsefulVariables.con.prepareStatement(sql);


        ResultSet result = UsefulVariables.getAllLecturers.executeQuery();

        while (result.next()) {

            LecturerMaster lecturerMaster = new LecturerMaster();
            lecturerMaster.setLecturerId(result.getString("idlecturer"));
            lecturerMaster.setFirstName(result.getString("firstName"));
            lecturerMaster.setLastName(result.getString("lastName"));
            lecturerMaster.setGender(result.getString("gender"));
            lecturerMaster.setPhone(result.getString("phone"));

            list.add(lecturerMaster);


        }


        return list;


    }


    int indexLecturer = -1;

    public void getSelectedLecturer(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexLecturer = lecturerTableView.getSelectionModel().getSelectedIndex();
        if (indexLecturer <= -1) {
            return;
        }

        String idlecturer = col_lecturerid.getCellData(indexLecturer).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from lecturer WHERE idlecturer = ?";
        UsefulVariables.getAllLecturers = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllLecturers.setString(1, idlecturer);
        ResultSet resultAllStudents = UsefulVariables.getAllLecturers.executeQuery();


        while (resultAllStudents.next()) {
            createLecturerId.setText(resultAllStudents.getString("idlecturer"));
            lecturerFirstName.setText(resultAllStudents.getString("firstName"));
            lecturerLastName.setText(resultAllStudents.getString("lastName"));
            lecturerPhone.setText(resultAllStudents.getString("phone"));
            lecturerEmail.setText(resultAllStudents.getString("emailAddress"));
            lecturerpassword.setText(resultAllStudents.getString("password"));
            String gender = resultAllStudents.getString("gender");
            genderLecturer.setValue(gender);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(resultAllStudents.getString("dob"), formatter);
            lecturerbirth.setValue(localDate);

        }


    }

    @FXML
    private void toEditLecturer() throws Exception {

        String idlecturer = col_lecturerid.getCellData(indexLecturer).toString();

        admin.editingLecturer(lecturerFirstName.getText(), lecturerLastName.getText(), genderLecturer.getValue(), lecturerPhone.getText(),
                lecturerbirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), lecturerEmail.getText(),
                createLecturerId.getText(), lecturerpassword.getText(), idlecturer);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Lecturer edited");
        alert.show();

        lecturerFirstName.clear();
        lecturerLastName.clear();
        genderLecturer.getSelectionModel().clearSelection();
        lecturerPhone.clear();
        lecturerbirth.getEditor().clear();
        lecturerEmail.clear();
        createLecturerId.clear();
        lecturerpassword.clear();

        refreshLecturerTable();

    }

    private void refreshLecturerTable() {

        col_lecturerid.setCellValueFactory(new PropertyValueFactory<>("lecturerId"));
        col_lecturerFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lecturerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_lecturerGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_lecturerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        try {
            lecturerTableView.getItems().setAll(getLecturersList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // COLLEGE BRANCH TABLE VIEW MANAGEMENT

    @FXML
    private TableView<CollegeBranchMaster> collegeBranchTableView;

    @FXML
    private TableColumn<CollegeBranchMaster, String> col_createbranchUnit;

    @FXML
    private TableColumn<CollegeBranchMaster, String> col_createbranchAddress;


    private ObservableList<CollegeBranchMaster> getBranchesList() throws ClassNotFoundException, SQLException {


        ObservableList<CollegeBranchMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "Select * from collegeBranches";

        UsefulVariables.getAllBranches = UsefulVariables.con.prepareStatement(sql);


        ResultSet result = UsefulVariables.getAllBranches.executeQuery();

        while (result.next()) {

            CollegeBranchMaster collegeBranchMaster = new CollegeBranchMaster();
            collegeBranchMaster.setBranchUnit(result.getString("unit"));
            collegeBranchMaster.setAddress(result.getString("address"));


            list.add(collegeBranchMaster);


        }


        return list;


    }

    int indexBranches = -1;

    public void getSelectedBranch(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexBranches = collegeBranchTableView.getSelectionModel().getSelectedIndex();
        if (indexBranches <= -1) {
            return;
        }

        String idlecturer = col_createbranchUnit.getCellData(indexBranches).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from collegeBranches WHERE unit = ?";
        UsefulVariables.getAllBranches = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllBranches.setString(1, idlecturer);
        ResultSet resultAllBranches = UsefulVariables.getAllBranches.executeQuery();


        while (resultAllBranches.next()) {
            branchUnitInsert.setText(resultAllBranches.getString("unit"));
            branchAddressInsert.setText(resultAllBranches.getString("address"));

        }


    }

    @FXML
    private void toEditCollegeBranch() throws Exception {

        String branchUnit = col_createbranchUnit.getCellData(indexBranches).toString();


        admin.editingBranch(branchUnitInsert.getText(), branchAddressInsert.getText(), branchUnit);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("College Branch edited");
        alert.show();

        branchUnitInsert.clear();
        branchAddressInsert.clear();

        refreshCollegeBranch();

    }

    private void refreshCollegeBranch() {

        col_createbranchUnit.setCellValueFactory(new PropertyValueFactory<>("branchUnit"));
        col_createbranchAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


        try {
            collegeBranchTableView.getItems().setAll(getBranchesList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // TABLE VIEW MANAGE COURSES

    @FXML
    private TableView<CoursesMaster> courseTableVIew;

    @FXML
    private TableColumn<CoursesMaster, String> col_createCourseName;

    @FXML
    private TableColumn<CoursesMaster, String> col_createCourseBranch;

    @FXML
    private TableColumn<CoursesMaster, Double> col_createCoursePrice;

    private ObservableList<CoursesMaster> getCoursesList() throws ClassNotFoundException, SQLException {


        ObservableList<CoursesMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from course";

        UsefulVariables.getAllCourses = UsefulVariables.con.prepareStatement(sql);


        ResultSet result = UsefulVariables.getAllCourses.executeQuery();

        while (result.next()) {

            CoursesMaster coursesMaster = new CoursesMaster();
            coursesMaster.setCourse(result.getString("name"));
            coursesMaster.setBranch(result.getString("collegeBranchUnit"));
            coursesMaster.setPrice(result.getDouble("price"));


            list.add(coursesMaster);


        }


        return list;


    }

    int indexCourse = -1;

    public void getSelectedCourse(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexCourse = courseTableVIew.getSelectionModel().getSelectedIndex();
        if (indexCourse <= -1) {
            return;
        }

        String courseName = col_createCourseName.getCellData(indexCourse).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from course WHERE name = ?";
        UsefulVariables.getAllCourses = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllCourses.setString(1, courseName);
        ResultSet resultAllBranches = UsefulVariables.getAllCourses.executeQuery();


        while (resultAllBranches.next()) {
            createCourseBranchChoice.setValue(resultAllBranches.getString("collegeBranchUnit"));
            courseNameInput.setText(resultAllBranches.getString("name"));
            coursePrice.setText(String.valueOf(resultAllBranches.getDouble("price")));

        }


    }

    @FXML
    private void toEditCourse() throws Exception {

        String courseName = col_createCourseName.getCellData(indexCourse).toString();


        admin.editingCourse(createCourseBranchChoice.getValue(), courseNameInput.getText(), Double.parseDouble(coursePrice.getText()),
                courseName);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Course edited");
        alert.show();

        createCourseBranchChoice.getSelectionModel().clearSelection();
        courseNameInput.clear();
        coursePrice.clear();

        refreshCourse();

    }

    private void refreshCourse() {

        col_createCourseName.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_createCourseBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        col_createCoursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        try {
            courseTableVIew.getItems().setAll(getCoursesList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // MANAGING MODULE TABLEVIEW

    @FXML
    private TableView<ModuleMaster> moduleTableView;

    @FXML
    private TableColumn<ModuleMaster, String> col_CreateModuleName;

    @FXML
    private TableColumn<ModuleMaster, String> col_createModuleCourse;

    @FXML
    private TableColumn<ModuleMaster, String> col_createModuleLecturerid;

    @FXML
    private TableColumn<ModuleMaster, String> col_createModuleWeekday;

    @FXML
    private TableColumn<ModuleMaster, String> col_createModuleHour;


    private ObservableList<ModuleMaster> getModuleList() throws ClassNotFoundException, SQLException {


        ObservableList<ModuleMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from module";

        UsefulVariables.getAllModules = UsefulVariables.con.prepareStatement(sql);


        ResultSet result = UsefulVariables.getAllModules.executeQuery();

        while (result.next()) {

            ModuleMaster moduleMaster = new ModuleMaster();
            moduleMaster.setModuleName(result.getString("subject"));
            moduleMaster.setCourseName(result.getString("course"));
            moduleMaster.setIdlecturer(result.getString("idlecturer"));
            moduleMaster.setWeekday(result.getString("weekDay"));
            moduleMaster.setClasshour(result.getString("classHour"));


            list.add(moduleMaster);


        }


        return list;


    }

    int indexModule = -1;

    public void getSelectedModule(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexModule = moduleTableView.getSelectionModel().getSelectedIndex();
        if (indexModule <= -1) {
            return;
        }

        String courseName = col_CreateModuleName.getCellData(indexModule).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from module WHERE subject = ?";
        UsefulVariables.getAllModules = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllModules.setString(1, courseName);
        ResultSet resultAllModules = UsefulVariables.getAllModules.executeQuery();


        while (resultAllModules.next()) {
            createModuleAddBranch.setValue(resultAllModules.getString("collegeBranch"));
            ModuleInCourse.setValue(resultAllModules.getString("course"));
            createModuleAddLecturer.setValue(resultAllModules.getString("idlecturer"));
            createModuleName.setText(resultAllModules.getString("subject"));
            createModuleAddHour.setText(resultAllModules.getString("classHour"));
            createModuleAddWeekDay.setValue(resultAllModules.getString("weekday"));

        }


    }

    @FXML
    private void toEditModule() throws Exception {

        String moduleSubject = col_CreateModuleName.getCellData(indexModule).toString();


        admin.editingModule(createModuleName.getText(), ModuleInCourse.getValue(), createModuleAddBranch.getValue(), createModuleAddWeekDay.getValue(),
                createModuleAddHour.getText(), createModuleAddLecturer.getValue(),
                moduleSubject);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Module edited");
        alert.show();

        toClearInputs();

        refreshModule();

    }

    private void refreshModule() {

        col_CreateModuleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        col_createModuleCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        col_createModuleLecturerid.setCellValueFactory(new PropertyValueFactory<>("idlecturer"));
        col_createModuleWeekday.setCellValueFactory(new PropertyValueFactory<>("weekday"));
        col_createModuleHour.setCellValueFactory(new PropertyValueFactory<>("classhour"));


        try {
            moduleTableView.getItems().setAll(getModuleList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // MANAGING COURSE YEAR TABLE VIEW

    @FXML
    private TableView<CourseYearMaster> courseYearTableView;

    @FXML
    private TableColumn<CourseYearMaster, String> col_createCourseYearCourse;

    @FXML
    private TableColumn<CourseYearMaster, Integer> col_createCourseYearYear;

    @FXML
    private TableColumn<CourseYearMaster, String> col_createCourseYearModule;


    private ObservableList<CourseYearMaster> getCourseYearList() throws ClassNotFoundException, SQLException {


        ObservableList<CourseYearMaster> list = FXCollections.observableArrayList();


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "Select * from courseYear";

        UsefulVariables.getAllCourseYear = UsefulVariables.con.prepareStatement(sql);
        ResultSet result = UsefulVariables.getAllCourseYear.executeQuery();



        while (result.next()) {

            CourseYearMaster courseYearMaster = new CourseYearMaster();
            courseYearMaster.setYear(result.getInt("year"));
            courseYearMaster.setCourse(result.getString("course"));
            courseYearMaster.setModule(result.getString("moduleName"));


            list.add(courseYearMaster);


        }


        return list;


    }

    int indexCourseYear = -1;

    public void getSelectedCourseYear(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexCourseYear = courseYearTableView.getSelectionModel().getSelectedIndex();
        if (indexCourseYear <= -1) {
            return;
        }

        String moduleName = col_createCourseYearModule.getCellData(indexCourseYear).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select * from courseYear WHERE moduleName = ?";
        UsefulVariables.getAllCourseYear = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAllCourseYear.setString(1, moduleName);
        ResultSet resultAllModules = UsefulVariables.getAllCourseYear.executeQuery();


        while (resultAllModules.next()) {
            courseYearNameInput.setValue(resultAllModules.getString("course"));
            courseYearModuleInput.setValue(resultAllModules.getString("moduleName"));
            courseYearInput.setText(resultAllModules.getString("year"));

        }

        String sql1 = "select * from module WHERE subject = ?";
        UsefulVariables.getModules = UsefulVariables.con.prepareStatement(sql1);
        UsefulVariables.getModules.setString(1, courseYearModuleInput.getValue());
        ResultSet resultModule = UsefulVariables.getModules.executeQuery();

        while (resultModule.next()){
            courseYearBranchInput.setValue(resultModule.getString("collegeBranch"));
        }

        courseYearBranchInput.setDisable(true);
        courseYearNameInput.setDisable(true);

    }

    private void refreshCourseYear() {

        col_createCourseYearCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_createCourseYearYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_createCourseYearModule.setCellValueFactory(new PropertyValueFactory<>("module"));


        try {
            courseYearTableView.getItems().setAll(getCourseYearList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    // MANAGE PAYMENTS

    @FXML
    private TextField insertStudentIDPayments;

    @FXML
    private TableView<PaymentsMaster> tableViewPayments;

    @FXML
    private TableColumn<PaymentsMaster, Integer> col_paymentsID;

    @FXML
    private TableColumn<PaymentsMaster, String> col_paymentsStudentID;

    @FXML
    private TableColumn<PaymentsMaster, String> col_paymentsInstallment;

    @FXML
    private TableColumn<PaymentsMaster, String> col_paymentsDate;

    @FXML
    private Button btnAddPayment;

    @FXML
    private Label numberInstallmentsToAdd;


    @FXML
    private ObservableList<PaymentsMaster> getStudentPaymentList(String studentid) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        ObservableList<PaymentsMaster> list = FXCollections.observableArrayList();


        String sql = "Select * from payments WHERE idstudent = ?";
        UsefulVariables.getPayments = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getPayments.setString(1, studentid);
        ResultSet result = UsefulVariables.getPayments.executeQuery();


        while (result.next()) {

            PaymentsMaster payments = new PaymentsMaster();
            payments.setPaymentid(result.getInt("idpayments"));
            payments.setStudentid(result.getString("idstudent"));
            payments.setPaidInstallment(result.getString("paidInstallment"));
            payments.setDate(result.getString("paymentDate"));
            list.add(payments);

        }

        return list;


    }

    int indexPaymentLog = -1;

    public void getSelectedPaymentLog(javafx.scene.input.MouseEvent mouseEvent) throws ClassNotFoundException, SQLException, ParseException {

        indexPaymentLog = tableViewPayments.getSelectionModel().getSelectedIndex();
        if (indexPaymentLog <= -1) {
            return;
        }

        String studentid = col_paymentsStudentID.getCellData(indexPaymentLog).toString();

        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "select idstudent from payments WHERE idstudent = ?";
        UsefulVariables.getPayments = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getPayments.setString(1, studentid);
        ResultSet resultPaymentLog = UsefulVariables.getPayments.executeQuery();


        while (resultPaymentLog.next()) {
            studentsInDebt.setValue(resultPaymentLog.getString("idstudent"));


        }


    }


    @FXML
    private void refreshPaymentLog(String idStudent) {


        col_paymentsID.setCellValueFactory(new PropertyValueFactory<>("paymentid"));
        col_paymentsStudentID.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        col_paymentsInstallment.setCellValueFactory(new PropertyValueFactory<>("paidInstallment"));
        col_paymentsDate.setCellValueFactory(new PropertyValueFactory<>("date"));


        try {
            tableViewPayments.getItems().setAll(getStudentPaymentList(idStudent));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }


    @FXML
    private void addPayments() throws ClassNotFoundException, SQLException {

        String idStudent = studentsInDebt.getValue();

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);

        if (!admin.addPayment(idStudent, formattedDate)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR ALERT");
            alert.setHeaderText("No more payments needed for this student!!!");
            alert.show();
        } else {
            labelAmountOfInstallments.setText(String.format
                    ("The student has to pay %d more installments", getAmountOfPayments(idStudent)));
            refreshPaymentLog(idStudent);
        }




    }


    private Integer getAmountOfPayments(String idStudent) throws ClassNotFoundException, SQLException {

        Integer amount = 0;
        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "select numberOfInstallments from student WHERE idstudent = ?";
        UsefulVariables.getAmountInstallments = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.getAmountInstallments.setString(1, idStudent);
        ResultSet result = UsefulVariables.getAmountInstallments.executeQuery();

        while (result.next()){
            amount = result.getInt("numberOfInstallments");
        }

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql1 = "select count(*) from payments WHERE idstudent = ?";
        UsefulVariables.getAmountInstallments = UsefulVariables.con.prepareStatement(sql1);
        UsefulVariables.getAmountInstallments.setString(1, idStudent);
        ResultSet resultTwo = UsefulVariables.getAmountInstallments.executeQuery();

        int count = 0;

        while(resultTwo.next()){
            count = resultTwo.getInt(1);
        }


        return (amount - count);


    }


    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
