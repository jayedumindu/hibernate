package Controller;

import BO.BoFactory;
import BO.custom.ReservationBO;
import BO.custom.RoomBO;
import BO.custom.StudentBO;
import DTOs.ReservationDTO;
import DTOs.RoomDTO;
import DTOs.StudentDTO;
import TDMs.RoomTDM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationController {

    public JFXTextField stIdTxt;
    public JFXTextField fNameTxt;
    public JFXTextField addressTxt;
    public JFXTextField contactTxt;
    public JFXTextField lNameTxt;
    public JFXDatePicker pickDate;
    public JFXComboBox<String> roomTypeCombo;
    public JFXButton register;
    public Label keyMoneyLbl;
    public RadioButton paidRd;
    public RadioButton payLaterRd;
    public Label descriptionLbl;
    public JFXComboBox genderCmb;

    public TableView reservations;
    public TableColumn resId;
    public TableColumn resDate;
    public TableColumn stId;
    public TableColumn roomType;
    public TableColumn status;
    public TableColumn paid;
    
    public TableColumn sId;
    public TableColumn contact;
    public TableColumn dob;
    public TableColumn address;
    public TableColumn lName;
    public TableColumn fName;
    public TableView students;

    public JFXComboBox statusCmb;

    private ReservationBO reservationBO;
    private StudentBO studentBO;
    private RoomBO roomBO;

    private ObservableList<String> roomList = FXCollections.observableArrayList();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        reservationBO = (ReservationBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.RESERVATION);
        studentBO = (StudentBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STUDENT);
        roomBO = (RoomBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM);

        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("Male");
        genderList.add("Female");
        genderCmb.setItems(genderList);

        ObservableList<String> statusList = FXCollections.observableArrayList();
        statusList.add("Paid");
        statusList.add("Pending");
        statusList.add("Booked");
        statusList.add("Deposit");
        statusCmb.setItems(statusList);

        final ToggleGroup group = new ToggleGroup();
        paidRd.setToggleGroup(group);
        payLaterRd.setToggleGroup(group);
        paidRd.setSelected(true);

        roomTypeCombo.setItems(roomList);
        loadRooms();
    }

    public void registerStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        StudentDTO student = new StudentDTO();
        ReservationDTO reservation = new ReservationDTO();
        // initializing student
        student.setSId(stIdTxt.getText());
        student.setContact(contactTxt.getText());
        student.setAddress(addressTxt.getText());
        student.setDOB(pickDate.getValue());
        student.setGender(genderCmb.getValue().toString());
        studentBO.save(student);
        // initializing room
        RoomDTO room = new RoomDTO();
        room.setRoomTypeId(roomTypeCombo.getValue());
        // initializing reservation
        reservation.setResId(reservationBO.generateNewID());
        reservation.setStatus(statusCmb.getValue().toString());
        reservation.setPaid(paidRd.isArmed());
        reservation.setStudent(student);
        reservation.setRoom(room);
        reservationBO.save(reservation);
        System.out.println("reservation saved");

        // updating room qty

    }

    // load rooms for combo
    private void loadRooms() throws SQLException, ClassNotFoundException {
        roomList.clear();
        ArrayList<RoomDTO> all = roomBO.getAll();
        for (RoomDTO dto:
                all) {
            roomList.add(dto.getRoomTypeId());
        }
    }

    public void cancelRegistration(ActionEvent actionEvent) {
    }

    public void updateRegistration(ActionEvent actionEvent) {
    }

    public void loadRoom(ActionEvent actionEvent) {
    }

    public void removeStudent(ActionEvent actionEvent) {
    }

    public void updateStudent(ActionEvent actionEvent) {
    }
}
