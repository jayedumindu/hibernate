package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RegistrationController {
    public JFXTextField stIdTxt;
    public JFXTextField fNameTxt;
    public JFXTextField addressTxt;
    public JFXTextField contactTxt;
    public JFXTextField lNameTxt;
    public JFXDatePicker pickDate;
    public JFXComboBox roomTypeCombo;
    public JFXButton register;
    public Label keyMoneyLbl;
    public RadioButton paidRd;
    public RadioButton payLaterRd;
    public Label descriptionLbl;
    public TableView reservations;
    public TableColumn resId;
    public TableColumn resDate;
    public TableColumn stId;
    public TableColumn roomType;
    public TableColumn status;
    public TableColumn paid;
    public TableView availability;
    public TableColumn roomTypeAvl;
    public TableColumn description;
    public TableColumn keyMoney;
    public TableColumn qty;

    public void registerStudent(ActionEvent actionEvent) {
    }

    public void cancelRegistration(ActionEvent actionEvent) {
    }

    public void updateRegistration(ActionEvent actionEvent) {
    }
}
