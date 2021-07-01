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

import javafx.beans.property.SimpleStringProperty;

public class CollegeBranchMaster {

    private final SimpleStringProperty branchUnit = new SimpleStringProperty();
    private final SimpleStringProperty address = new SimpleStringProperty();

    public CollegeBranchMaster() {
    }

    public String getBranchUnit() {
        return branchUnit.get();
    }

    public SimpleStringProperty branchUnitProperty() {
        return branchUnit;
    }

    public void setBranchUnit(String branchUnit) {
        this.branchUnit.set(branchUnit);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
