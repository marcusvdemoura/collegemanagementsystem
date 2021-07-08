package JavaProject.entities;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415


 */

import JavaProject.queriesSQL.UsefulVariables;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class Admin extends Person {

    private Student student;
    private Lecturer lecturer;
    private CollegeBranch collegeBranch;
    private Module module;
    private Course course;
    private String courseYear;
    private ArrayList<CollegeBranch> listOfBranches = new ArrayList<>();


//    final static String URL = "jdbc:mysql://127.0.0.1:3306/collegeManagementSystem?autoReconnect=true&useSSL=false";
//    final static String DB_USER = "root";
//    final static String DB_PASSWORD = "M@rcus2020";
//
//    private PreparedStatement createBranchQuery;
//    private PreparedStatement deleteBranchQuery;
//    private PreparedStatement editBranchQuery;
//    private PreparedStatement createStudentQuery;
//    private PreparedStatement deleteStudentQuery;
//    private PreparedStatement editStudentQuery;
//    private PreparedStatement createLecturerQuery;
//    private PreparedStatement deleteLecturerQuery;
//    private PreparedStatement editLecturerQuery;
//    private PreparedStatement createAdminQuery;
//    private PreparedStatement createModuleQuery;
//    private PreparedStatement deleteModuleQuery;
//    private PreparedStatement editModuleQuery;
//    private PreparedStatement createCourseQuery;
//    private PreparedStatement deleteCourseQuery;
//    private PreparedStatement editCourseQuery;
//    private PreparedStatement addStudentModuleQuery;
//    private PreparedStatement createCourseYear;
//    private PreparedStatement addPaymentQuery;


//    private Connection con = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);


    public Admin(String first_name, String last_name, String gender, String phone, String dob, String emailAddress, String adminId, String password) throws SQLException {
        super(first_name, last_name, gender, phone, dob, emailAddress, adminId, password);
    }

    public Admin(String first_name, String last_name) throws SQLException {
        super(first_name, last_name);
    }


    // CREATING BRANCHES, LECTURERS AND STUDENTS AND NEW ADMIN

    public void createAdmin(String first_name, String last_name, String gender, String phone, String dob,
                            String emailAddress, String adminId, String password) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO admin(firstName, lastName, gender, phoneNumber, dob, emailAddress, adminID," +
                " password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        UsefulVariables.createAdminQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createAdminQuery.setString(1, first_name);
        UsefulVariables.createAdminQuery.setString(2, last_name);
        UsefulVariables.createAdminQuery.setString(3, gender);
        UsefulVariables.createAdminQuery.setString(4, phone);
        UsefulVariables.createAdminQuery.setString(5, dob);
        UsefulVariables.createAdminQuery.setString(6, emailAddress);
        UsefulVariables.createAdminQuery.setString(7, adminId);
        UsefulVariables.createAdminQuery.setString(8, password);


        UsefulVariables.createAdminQuery.execute();

    }

    public void createBranch(String unit, String address) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO collegeBranches(unit, address) VALUES (?, ?)";
        UsefulVariables.createBranchQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createBranchQuery.setString(1, unit);
        UsefulVariables.createBranchQuery.setString(2, address);

        UsefulVariables.createBranchQuery.execute();


    }

    public void createLecturer(String first_name, String last_name, String gender, String phone, String dob, String emailAddress,
                               String lecturerId, String password) throws Exception {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO lecturer(firstName, lastName, gender, phone, dob, emailAddress, idlecturer," +
                " password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        UsefulVariables.createLecturerQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createLecturerQuery.setString(1, first_name);
        UsefulVariables.createLecturerQuery.setString(2, last_name);
        UsefulVariables.createLecturerQuery.setString(3, gender);
        UsefulVariables.createLecturerQuery.setString(4, phone);
        UsefulVariables.createLecturerQuery.setString(5, dob);
        UsefulVariables.createLecturerQuery.setString(6, emailAddress);
        UsefulVariables.createLecturerQuery.setString(7, lecturerId);
        UsefulVariables.createLecturerQuery.setString(8, password);


        UsefulVariables.createLecturerQuery.execute();
    }


    public void createStudents(String first_name, String last_name, String gender, String phone, String dob,
                               String emailAddress, String studentId, String password, String collegeBranchName, String courseName, int courseYear,
                               boolean isPaidFull) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO student(firstName, lastName, gender, phone, dob, emailAddress, idstudent," +
                " password, collegeBranch, course, isPaidFull, courseYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        UsefulVariables.createStudentQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createStudentQuery.setString(1, first_name);
        UsefulVariables.createStudentQuery.setString(2, last_name);
        UsefulVariables.createStudentQuery.setString(3, gender);
        UsefulVariables.createStudentQuery.setString(4, phone);
        UsefulVariables.createStudentQuery.setString(5, dob);
        UsefulVariables.createStudentQuery.setString(6, emailAddress);
        UsefulVariables.createStudentQuery.setString(7, studentId);
        UsefulVariables.createStudentQuery.setString(8, password);
        UsefulVariables.createStudentQuery.setString(9, collegeBranchName);
        UsefulVariables.createStudentQuery.setString(10, courseName);
        UsefulVariables.createStudentQuery.setBoolean(11, isPaidFull);
        UsefulVariables.createStudentQuery.setInt(12, courseYear);


        UsefulVariables.createStudentQuery.execute();

    }


    // DELETING COURSES LECTURERS AND STUDENTS

    public void deleteStudent(String studentID) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "DELETE from student WHERE idstudent = '" + studentID + "'";

        UsefulVariables.deleteStudentQuery = UsefulVariables.con.prepareStatement(sql);


        UsefulVariables.deleteStudentQuery.execute();


    }

    public void deleteCourse(String collegeBranchUnit, String courseName) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "DELETE from course WHERE collegeBranchUnit = '" + collegeBranchUnit + "' AND name = '" + courseName + "'";

        UsefulVariables.deleteCourseQuery = UsefulVariables.con.prepareStatement(sql);


        UsefulVariables.deleteCourseQuery.execute();


    }

    public void deleteModule(String subject) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "DELETE from module WHERE subject = '" + subject + "'";

        UsefulVariables.deleteModuleQuery = UsefulVariables.con.prepareStatement(sql);


        UsefulVariables.deleteModuleQuery.execute();

    }

    public void deleteLecturer(String lecturerId) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        // this is givin an error

        String sql = "DELETE from lecturer WHERE idlecturer = '" + lecturerId + "'";

        UsefulVariables.deleteLecturerQuery = UsefulVariables.con.prepareStatement(sql);


        UsefulVariables.deleteLecturerQuery.execute();


    }

    // DELETING BRANCHES

    public void deleteBranch(String branchUnit) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "DELETE FROM collegeBranches WHERE unit = ?";
        UsefulVariables.deleteBranchQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.deleteBranchQuery.setString(1, branchUnit);

        UsefulVariables.deleteBranchQuery.execute();


    }

    // CREATING MODULES AND COURSES - ADDING STUDENTS TO MODULES
    public void createModule(String moduleName, String courseName, String collegeBranchUnit, String weekDay, String classHour, String lecturerId) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO module(subject, course, collegeBranch, weekDay, classHour, idlecturer) VALUES (?, ?, ?, ?, ?, ?)";

        UsefulVariables.createModuleQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createModuleQuery.setString(1, moduleName);
        UsefulVariables.createModuleQuery.setString(2, courseName);
        UsefulVariables.createModuleQuery.setString(3, collegeBranchUnit);
        UsefulVariables.createModuleQuery.setString(4, weekDay);
        UsefulVariables.createModuleQuery.setString(5, classHour);
        UsefulVariables.createModuleQuery.setString(6, lecturerId);

        UsefulVariables.createModuleQuery.execute();


    }

    public void createCourse(String collegeBranchUnit, String name, Double price) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO course(collegeBranchUnit, name, price) VALUES (?, ?, ?)";

        UsefulVariables.createCourseQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createCourseQuery.setString(1, collegeBranchUnit);
        UsefulVariables.createCourseQuery.setString(2, name);
        UsefulVariables.createCourseQuery.setDouble(3, price);

        UsefulVariables.createCourseQuery.execute();


    }

    public void addStudentToModule(String studentId, String moduleSubject) throws Exception {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "INSERT INTO studentInModule(idStudent, moduleName) VALUES (?, ?)";

        UsefulVariables.addStudentModuleQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.addStudentModuleQuery.setString(1, studentId);
        UsefulVariables.addStudentModuleQuery.setString(2, moduleSubject);


        UsefulVariables.addStudentModuleQuery.execute();


    }


    // to create course year

    public void createCourseYear(String year, String courseName, String moduleName) throws ClassNotFoundException, SQLException {
        Class.forName(UsefulVariables.CLASS_INFO);
        String sql = "INSERT INTO courseYear(year, course, moduleName) VALUES (?, ?, ?)";
        UsefulVariables.createCourseYear = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.createCourseYear.setString(1, year);
        UsefulVariables.createCourseYear.setString(2, courseName);
        UsefulVariables.createCourseYear.setString(3, moduleName);

        UsefulVariables.createCourseYear.execute();

    }


    // GETTING AND PRINTING LIST OF BRANCHES

    public ArrayList<CollegeBranch> getListOfBranches() {
        return listOfBranches;
    }

    public void printListOfBranches() {
        for (CollegeBranch cb : getListOfBranches()) {
            System.out.println("Branch " + cb.getUnit() + "\nLocated at: " + cb.getAddress());
        }
    }


    // Add payment to student
    public boolean addPayment(String idStudent, String paymentDate) throws SQLException, ClassNotFoundException {

        int count = 0;
        int numberOfInstallments = 0;


        Class.forName(UsefulVariables.CLASS_INFO);


        String sql = "SELECT numberOfInstallments FROM student WHERE idstudent = ?";
        UsefulVariables.addPaymentQuery = UsefulVariables.con.prepareStatement(sql);
        UsefulVariables.addPaymentQuery.setString(1, idStudent);
        ResultSet numberInstallmentsResult = UsefulVariables.addPaymentQuery.executeQuery();

        while(numberInstallmentsResult.next()) {
            numberOfInstallments = Integer.parseInt(numberInstallmentsResult.getString("numberOfInstallments"));
        }



        String sql1 = "SELECT * FROM payments WHERE idstudent = ?";
        UsefulVariables.addPaymentQuery = UsefulVariables.con.prepareStatement(sql1);
        UsefulVariables.addPaymentQuery.setString(1, idStudent);

        ResultSet result = UsefulVariables.addPaymentQuery.executeQuery();

        while(result.next()){
            count++;
        }



        if(numberOfInstallments - count > 0){
            String sql2 = "INSERT into payments(idstudent, paymentDate) VALUES (?,?)";
            UsefulVariables.addPaymentQuery = UsefulVariables.con.prepareStatement(sql2);

            UsefulVariables.addPaymentQuery.setString(1, idStudent);
            UsefulVariables.addPaymentQuery.setString(2, paymentDate);
            UsefulVariables.addPaymentQuery.execute();



            return true;
        } else {
            String sql2 = "UPDATE student SET isPaidFull = ? WHERE idstudent = ?";
            UsefulVariables.addPaymentQuery = UsefulVariables.con.prepareStatement(sql2);
            UsefulVariables.addPaymentQuery.setBoolean(1, true);
            UsefulVariables.addPaymentQuery.setString(2, idStudent);

            UsefulVariables.addPaymentQuery.execute();

            return false;
        }




    }


    // Set fees installments

    public void setInstallments(Integer installments, String studentId) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);
        String sql = "UPDATE student SET numberOfInstallments = ? WHERE idstudent = ?";
        UsefulVariables.setInstall = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.setInstall.setInt(1, installments);
        UsefulVariables.setInstall.setString(2, studentId);


        UsefulVariables.setInstall.execute();

    }


    // EDITING STUDENTS, LECTURERS

    public void editingStudent(String first_name, String last_name, String gender, String phone, String dob,
                               String emailAddress, String studentId, String password, String collegeBranchName, String courseName, int courseYear,
                               boolean isPaidFull, String col_idValue) throws ClassNotFoundException, SQLException {


        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "UPDATE student SET firstName = ?, lastName = ?, gender = ?, phone = ?, dob = ?, emailAddress = ?, idstudent = ?," +
                "password = ?, collegeBranch = ?, course = ?, isPaidFull = ?, courseYear = ? WHERE idstudent=?";


        UsefulVariables.editStudentQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.editStudentQuery.setString(1, first_name);
        UsefulVariables.editStudentQuery.setString(2, last_name);
        UsefulVariables.editStudentQuery.setString(3, gender);
        UsefulVariables.editStudentQuery.setString(4, phone);
        UsefulVariables.editStudentQuery.setString(5, dob);
        UsefulVariables.editStudentQuery.setString(6, emailAddress);
        UsefulVariables.editStudentQuery.setString(7, studentId);
        UsefulVariables.editStudentQuery.setString(8, password);
        UsefulVariables.editStudentQuery.setString(9, collegeBranchName);
        UsefulVariables.editStudentQuery.setString(10, courseName);
        UsefulVariables.editStudentQuery.setBoolean(11, isPaidFull);
        UsefulVariables.editStudentQuery.setInt(12, courseYear);
        UsefulVariables.editStudentQuery.setString(13, col_idValue);


        UsefulVariables.editStudentQuery.execute();
    }

    public void editingLecturer(String first_name, String last_name, String gender, String phone, String dob, String emailAddress,
                                String lecturerId, String password, String col_idValue) throws Exception {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "UPDATE lecturer SET firstName = ?, lastName = ?, gender = ?, phone = ?, dob = ?, emailAddress = ?, idlecturer = ?," +
                "password = ? WHERE idlecturer=?";

        UsefulVariables.editLecturerQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.editLecturerQuery.setString(1, first_name);
        UsefulVariables.editLecturerQuery.setString(2, last_name);
        UsefulVariables.editLecturerQuery.setString(3, gender);
        UsefulVariables.editLecturerQuery.setString(4, phone);
        UsefulVariables.editLecturerQuery.setString(5, dob);
        UsefulVariables.editLecturerQuery.setString(6, emailAddress);
        UsefulVariables.editLecturerQuery.setString(7, lecturerId);
        UsefulVariables.editLecturerQuery.setString(8, password);
        UsefulVariables.editLecturerQuery.setString(9, col_idValue);


        UsefulVariables.editLecturerQuery.execute();

    }

    public void editingBranch(String unit, String address, String col_unit) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "UPDATE collegeBranches SET unit = ?, address = ? WHERE unit=?";
        UsefulVariables.editBranchQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.editBranchQuery.setString(1, unit);
        UsefulVariables.editBranchQuery.setString(2, address);
        UsefulVariables.editBranchQuery.setString(3, col_unit);

        UsefulVariables.editBranchQuery.execute();

    }
    public void editingCourse(String collegeBranchUnit, String name, Double price, String col_courseName) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "UPDATE course SET collegeBranchUnit = ?, name = ?, price = ? WHERE name=?";

        UsefulVariables.editCourseQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.editCourseQuery.setString(1, collegeBranchUnit);
        UsefulVariables.editCourseQuery.setString(2, name);
        UsefulVariables.editCourseQuery.setDouble(3, price);
        UsefulVariables.editCourseQuery.setString(4, col_courseName);

        UsefulVariables.editCourseQuery.execute();

    }

    public void editingModule(String moduleName, String courseName, String collegeBranchUnit, String weekDay,
                              String classHour, String lecturerId, String col_moduleSubject) throws ClassNotFoundException, SQLException {

        Class.forName(UsefulVariables.CLASS_INFO);

        String sql = "UPDATE module SET subject = ?, course = ?, collegeBranch = ?, weekday = ?, classHour = ?, idlecturer = ? WHERE subject=?";

        UsefulVariables.editModuleQuery = UsefulVariables.con.prepareStatement(sql);

        UsefulVariables.editModuleQuery.setString(1, moduleName);
        UsefulVariables.editModuleQuery.setString(2, courseName);
        UsefulVariables.editModuleQuery.setString(3, collegeBranchUnit);
        UsefulVariables.editModuleQuery.setString(4, weekDay);
        UsefulVariables.editModuleQuery.setString(5, classHour);
        UsefulVariables.editModuleQuery.setString(6, lecturerId);
        UsefulVariables.editModuleQuery.setString(7, col_moduleSubject);

        UsefulVariables.editModuleQuery.execute();


    }


        public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }






}
