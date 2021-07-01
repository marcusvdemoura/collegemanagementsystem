package JavaProject.entities;

/*

DORSET COLLEGE

OBJECT ORIENTED PROGRAMING - CA3

Lecturer: John Rowley

STUDENTS:
Marcus Vinicius de Freitas Moura – 22415
Valeria Cardoso da Paz – 21214
Alexson Oliveira Silva – 21643




 */

public abstract class Person {
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String dob;
    private String emailAddress;
    private String id;
    private String password;

    public Person(String firstName, String lastName, String gender, String phone, String dob, String emailAddress, String id, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.id = id;
        this.password = password;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
