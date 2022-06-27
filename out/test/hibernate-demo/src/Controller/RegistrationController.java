package Controller;

import BO.BoFactory;
import BO.custom.ReservationBO;
import BO.custom.RoomBO;
import BO.custom.StudentBO;
import DTOs.CustomDTO;
import DTOs.ReservationDTO;
import DTOs.RoomDTO;
import DTOs.StudentDTO;
import TDMs.CustomTDM;
import TDMs.ReservationTDM;
import TDMs.StudentTDM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public JFXComboBox<String> genderCmb;

    public TableView<ReservationTDM> reservations;
    public TableColumn<ReservationTDM,String> resId;
    public TableColumn<ReservationTDM, Date> resDate;
    public TableColumn<ReservationTDM, String> stId;
    public TableColumn<ReservationTDM, String> roomType;
    public TableColumn<ReservationTDM, String> status;
    public TableColumn<ReservationTDM, Boolean> paid;

    public TableView<StudentTDM> students;
    public TableColumn<StudentTDM,String> sId;
    public TableColumn<StudentTDM,String> contact;
    public TableColumn<StudentTDM, LocalDate> dob;
    public TableColumn<StudentTDM,String> address;
    public TableColumn<StudentTDM,String> fName;
    public TableColumn<StudentTDM,String> gender;

    public JFXComboBox<String> statusCmb;
    public JFXComboBox<String> roomTypeAvlCmb;

    public Label availabilityLbl;
    public Label keyMoneyAvlLbl;

    public TableView<CustomTDM> KMTbl;
    public TableColumn<CustomTDM,String> KmSid;
    public TableColumn<CustomTDM,String> KmName;
    public TableColumn<CustomTDM,Double> KmValue;
    public TableColumn<CustomTDM,String> KmRoomType;
    public TableColumn<CustomTDM,String> KmRoomDescription;

    private ReservationBO reservationBO;
    private StudentBO studentBO;
    private RoomBO roomBO;

    private ObservableList<ReservationTDM> reservationList = FXCollections.observableArrayList();
    private ObservableList<String> roomList = FXCollections.observableArrayList();
    private ObservableList<StudentTDM> studentList = FXCollections.observableArrayList();
    private ObservableList<CustomTDM> customList = FXCollections.observableArrayList();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        reservations.setItems(reservationList);
        students.setItems(studentList);
        KMTbl.setItems(customList);

        sId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        dob.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        fName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        resDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        stId.setCellValueFactory(new PropertyValueFactory<>("student"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("room"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        paid.setCellValueFactory(new PropertyValueFactory<>("paid"));

        KmSid.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        KmName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        KmValue.setCellValueFactory(new PropertyValueFactory<>("dueValue"));
        KmRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        KmRoomDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

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
        roomTypeAvlCmb.setItems(roomList);
        loadRooms();

        // loading data for tables
        loadAllReservations();
        loadAllStudents();
        loadStudentsForKMCheck();
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
        RoomDTO roomDTO = roomBO.search(room.getRoomTypeId());
        roomDTO.setQuantity(roomDTO.getQuantity());
        roomBO.update(roomDTO);
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

    public void cancelRegistration(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        reservationBO.delete(reservations.getSelectionModel().getSelectedItem().getResId());
        loadAllReservations();
    }

    public void updateRegistration(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void loadRoom(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomBO.search(roomTypeCombo.getValue());
        System.out.println(roomDTO);
        descriptionLbl.setText(roomDTO.getType());
        keyMoneyLbl.setText(String.valueOf(roomDTO.getKeyMoney()));
    }

    public void removeStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        studentBO.delete(students.getSelectionModel().getSelectedItem().getSId());
        loadAllStudents();
    }

    public void updateStudent(ActionEvent actionEvent) {
    }

    private void loadAllReservations() throws SQLException, ClassNotFoundException {
        reservationList.clear();
        ArrayList<ReservationDTO> all = reservationBO.getAll();
        for (ReservationDTO dto:
                all) {
            reservationList.add(new ReservationTDM(dto.getResId(), dto.getDate().toString(), dto.getStudent().getSId(), dto.getRoom().getRoomTypeId(), dto.getStatus(), dto.isPaid()));
        }
    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        studentList.clear();
        ArrayList<StudentDTO> all = studentBO.getAll();
        for (StudentDTO dto:
                all) {
            studentList.add(new StudentTDM(dto.getSId(), dto.getSName(), dto.getAddress(), dto.getContact(), dto.getDOB().toString(), dto.getGender()));
        }
    }

    public void roomAvailabilityCheck(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // if room qty is greater than 0
        RoomDTO search = roomBO.search(roomTypeAvlCmb.getValue().toString());
        if(search.getQuantity()>0){
            availabilityLbl.setText("YES");

        }else availabilityLbl.setText("NO");
        keyMoneyAvlLbl.setText(String.valueOf(search.getKeyMoney()));
    }

    private void loadStudentsForKMCheck(){
        customList.clear();
        List<CustomDTO> all = studentBO.loadStudentsWhoNeedToPayKM();
        for (CustomDTO dto:
                all) {
            System.out.println(dto);
            customList.add(new CustomTDM(dto.getStudentId(),dto.getSName(),dto.getDueValue(),dto.getRoomType(),dto.getDescription()));
        }
    }

    public void markStudentAsPaid(ActionEvent actionEvent) {
    }
}
