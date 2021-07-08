package JavaProject.tableviewPOJO;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415

 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PaymentsMaster {

    private final SimpleIntegerProperty paymentid = new SimpleIntegerProperty();
    private final SimpleStringProperty studentid = new SimpleStringProperty();
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleStringProperty paidInstallment = new SimpleStringProperty();


    public PaymentsMaster() {
    }


    public int getPaymentid() {
        return paymentid.get();
    }

    public SimpleIntegerProperty paymentidProperty() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid.set(paymentid);
    }

    public String getStudentid() {
        return studentid.get();
    }

    public SimpleStringProperty studentidProperty() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid.set(studentid);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPaidInstallment() {
        return paidInstallment.get();
    }

    public SimpleStringProperty paidInstallmentProperty() {
        return paidInstallment;
    }

    public void setPaidInstallment(String paidInstallment) {
        this.paidInstallment.set(paidInstallment);
    }
}
