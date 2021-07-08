package JavaProject.queriesSQL;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415


 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsefulVariables {

    public final static String DB_NAME = "collegeManagementSystem";

    public final static String URL = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME +"?autoReconnect=true&useSSL=false";
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "M@rcus2020";

    public final static String CLASS_INFO = "com.mysql.cj.jdbc.Driver";


    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static PreparedStatement getAllAssignments;
    public static PreparedStatement getSelectedAssignment;
    public static PreparedStatement editAssignment;
    public static PreparedStatement deleteAssignment;
    public static PreparedStatement addGrade;
    public static PreparedStatement getSelectedGrade;
    public static PreparedStatement getAllGrades;
    public static PreparedStatement createExam;
    public static PreparedStatement editExam;
    public static PreparedStatement deleteExam;
    public static PreparedStatement getSelectedExam;
    public static PreparedStatement deleteBranchQuery;
    public static PreparedStatement editBranchQuery;
    public static PreparedStatement createStudentQuery;
    public static PreparedStatement deleteStudentQuery;
    public static PreparedStatement editStudentQuery;
    public static PreparedStatement createLecturerQuery;
    public static PreparedStatement deleteLecturerQuery;
    public static PreparedStatement editLecturerQuery;
    public static PreparedStatement createAdminQuery;
    public static PreparedStatement createModuleQuery;
    public static PreparedStatement deleteModuleQuery;
    public static PreparedStatement editModuleQuery;
    public static PreparedStatement createCourseQuery;
    public static PreparedStatement deleteCourseQuery;
    public static PreparedStatement editCourseQuery;
    public static PreparedStatement addStudentModuleQuery;
    public static PreparedStatement createCourseYear;
    public static PreparedStatement setInstall;
    public static PreparedStatement createBranchQuery;
    public static PreparedStatement createAssignmentQuery;
    public static PreparedStatement getBranches;
    public static PreparedStatement getCourses;
    public static PreparedStatement getLecturersId;
    public static PreparedStatement getModules;
    public static PreparedStatement getAllStudents;
    public static PreparedStatement getAllLecturers;
    public static PreparedStatement getAllCourses;
    public static PreparedStatement getAllBranches;
    public static PreparedStatement getAllModules;
    public static PreparedStatement getAllCourseYear;
    public static PreparedStatement getPayments;
    public static PreparedStatement addPaymentQuery;
    public static PreparedStatement getStudents;
    public static PreparedStatement getAmountInstallments;





    public UsefulVariables() throws SQLException {
    }
}
