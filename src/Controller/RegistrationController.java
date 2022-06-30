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
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public Label regIdLbl;

    public Pane stDetailsPane;
    public Pane reservationDetailsPane;
    public Label alertLbl;
    public Button cancelBtn;
    public JFXComboBox<String> studentCmb;
    public JFXToggleButton studentToggle;
    public Pane fromExistingStPane;
    public JFXTabPane tabPane;
    public Tab processTab;

    private ReservationBO reservationBO;
    private StudentBO studentBO;
    private RoomBO roomBO;

    private ObservableList<ReservationTDM> reservationList = FXCollections.observableArrayList();
    private ObservableList<String> roomList = FXCollections.observableArrayList();
    private ObservableList<String> studentIdList = FXCollections.observableArrayList();
    private ObservableList<StudentTDM> studentList = FXCollections.observableArrayList();
    private ObservableList<CustomTDM> customList = FXCollections.observableArrayList();

    private Func STATUS;

    public void cancelCurrentStatus(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        switch (STATUS){
            case UPDATE_RESERVATION: clearUIForUpRes(); setRegId(); break;
            case UPDATE_STUDENT: clearUIForUpSt(); break;
        }
        STATUS = Func.RESERVE; alertLbl.setText(""); cancelBtn.setVisible(false);
    }

    private enum Func{
        UPDATE_STUDENT,UPDATE_RESERVATION,RESERVE
    }

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
        studentCmb.setItems(studentIdList);
        loadRooms();

        // loading data for tables
        loadAllReservations();
        loadAllStudents();
        loadStudentsForKMCheck();

        setRegId();

        STATUS = Func.RESERVE;

        // toggle btn on action
        studentToggle.setOnAction(e->{
            if(studentToggle.isSelected()){
                studentCmb.setDisable(false);
                stDetailsPane.setDisable(true);
            }else {
                studentCmb.setDisable(true);
                stDetailsPane.setDisable(false);
            }
                }
        );
    }

    public void registerStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // check is it an update
        switch (STATUS){
            case RESERVE: saveReservation(); loadStudentsForKMCheck(); break;
            case UPDATE_STUDENT : saveStudent();break;
            case UPDATE_RESERVATION: updateReservation(); loadStudentsForKMCheck(); break;
        }

    }

    // load rooms for combo
    private void loadRooms() throws SQLException, ClassNotFoundException {
        roomList.clear();
        ArrayList<RoomDTO> all = roomBO.getAll();
        for (RoomDTO dto:
                all) {
            if(dto.getQuantity()>0) {
                roomList.add(dto.getRoomTypeId());
            }
        }
    }

    public void cancelRegistration(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        reservationBO.delete(reservations.getSelectionModel().getSelectedItem().getResId());
        RoomDTO roomDTO = roomBO.search(reservations.getSelectionModel().getSelectedItem().getRoom());
        roomDTO.setQuantity(roomDTO.getQuantity()+1);
        roomBO.update(roomDTO);
        loadAllReservations();
    }

    // fillers
    public void updateRegistration(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!STATUS.equals(Func.RESERVE)){
            new Alert(Alert.AlertType.WARNING,"Please invoke the ongoing update before proceeding!").show();
            return;
        }
        ReservationTDM tdm = reservations.getSelectionModel().getSelectedItem();
        regIdLbl.setText(tdm.getResId());
        roomTypeCombo.getSelectionModel().select(tdm.getRoom());

        statusCmb.getSelectionModel().select(tdm.getStatus());
        if(tdm.isPaid()){
            paidRd.setSelected(true);
        }else payLaterRd.setSelected(true);

        // select student
        studentToggle.setSelected(true);
        studentToggle.setDisable(true);
        if(studentCmb.isDisable()){
            studentCmb.setDisable(false);
        }
        studentCmb.getSelectionModel().select(tdm.getStudent());
        // change button value
        register.setText("Update Reservation");
        STATUS = Func.UPDATE_RESERVATION;

        stDetailsPane.setDisable(true);
        // warnings
        alertLbl.setText("You're updating a reservation. Click cancel to invoke!");
        cancelBtn.setVisible(true);

        tabPane.getSelectionModel().select(processTab);
    }

    public void loadRoom(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RoomDTO roomDTO = roomBO.search(roomTypeCombo.getValue());
        if(roomDTO!=null){
            descriptionLbl.setText(roomDTO.getType());
            keyMoneyLbl.setText(String.valueOf(roomDTO.getKeyMoney()));
        }
    }

    public void removeStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservations = reservationBO.getAll();
        reservations.removeIf(n -> (!n.getStudent().getSId().equals(students.getSelectionModel().getSelectedItem().getSId())));
        for (ReservationDTO dto: reservations) {
            RoomDTO roomDTO = roomBO.search(dto.getRoom().getRoomTypeId());
            roomDTO.setQuantity(roomDTO.getQuantity()+1);
            roomBO.update(roomDTO);
        }
        studentBO.delete(students.getSelectionModel().getSelectedItem().getSId());
        loadAllStudents();
        loadAllReservations();
    }

    // fillers
    public void updateStudent(ActionEvent actionEvent) {
        if(!STATUS.equals(Func.RESERVE)){
            new Alert(Alert.AlertType.WARNING,"Please invoke the ongoing update before proceeding!").show();
            return;
        }
        StudentTDM tdm = students.getSelectionModel().getSelectedItem();
        String[] fullName = tdm.getSName().split(" ");
        LocalDate dob = LocalDate.parse(tdm.getDOB());
        stIdTxt.setText(tdm.getSId());
        addressTxt.setText(tdm.getAddress());
        contactTxt.setText(tdm.getContact());
        fNameTxt.setText(fullName[0]);
        lNameTxt.setText(fullName[1]);
        pickDate.setValue(dob);
        genderCmb.getSelectionModel().select(tdm.getGender());
        // change btn value
        register.setText("Update Student");
        // disable pane
        reservationDetailsPane.setDisable(true);
        // warnings
        alertLbl.setText("You're updating a student. Click cancel to invoke!");
        cancelBtn.setVisible(true);
        fromExistingStPane.setDisable(true);
        STATUS = Func.UPDATE_STUDENT;
        tabPane.getSelectionModel().select(processTab);
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
        studentIdList.clear();
        ArrayList<StudentDTO> all = studentBO.getAll();
        for (StudentDTO dto:
                all) {
            studentIdList.add(dto.getSId());
            studentList.add(new StudentTDM(dto.getSId(), dto.getSName(), dto.getAddress(), dto.getContact(), dto.getDOB().toString(), dto.getGender()));
        }
    }

    public void roomAvailabilityCheck(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // if room qty is greater than 0
        RoomDTO search = roomBO.search(roomTypeAvlCmb.getValue());
        if(search.getQuantity()>0){
            availabilityLbl.setText("YES");

        }else availabilityLbl.setText("NO");
        keyMoneyAvlLbl.setText(String.valueOf(search.getKeyMoney()));
    }

    private void loadStudentsForKMCheck(){
        customList.clear();
        ArrayList<CustomDTO> all = studentBO.loadStudentsWhoNeedToPayKM();
        for (CustomDTO dto:
                all) {
            System.out.println(dto);
            customList.add(new CustomTDM(dto.getStudentId(),dto.getSName(),dto.getDueValue(),dto.getRoomType(),dto.getDescription()));
        }
        System.out.println("reached end");
    }

    public void markStudentAsPaid(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = KMTbl.getSelectionModel().getSelectedItem().getStudentId();
        studentBO.search(id).getReservations();
    }

    private void setRegId() throws SQLException, ClassNotFoundException {
        regIdLbl.setText(reservationBO.generateNewID());
    }

    private void saveReservation() throws SQLException, ClassNotFoundException {
        StudentDTO student = new StudentDTO();
        ReservationDTO reservation = new ReservationDTO();
        // initializing room
        RoomDTO room = new RoomDTO();
        room.setRoomTypeId(roomTypeCombo.getValue());

        if(studentToggle.isSelected()){
            student.setSId(studentCmb.getValue());
        }else {
            // initializing student
            student.setSId(stIdTxt.getText());
            student.setSName(fNameTxt.getText() + " " + lNameTxt.getText());
            student.setContact(contactTxt.getText());
            student.setAddress(addressTxt.getText());
            student.setDOB(pickDate.getValue());
            student.setGender(genderCmb.getValue());
            studentBO.save(student);

            loadAllStudents();
        }

        // initializing reservation
        reservation.setResId(regIdLbl.getText());
        reservation.setStatus(statusCmb.getValue());
        reservation.setPaid(paidRd.isArmed());
        reservation.setStudent(student);
        reservation.setRoom(room);
        reservationBO.save(reservation);
        System.out.println("reservation saved");

        // updating room qty
        RoomDTO roomDTO = roomBO.search(room.getRoomTypeId());
        roomDTO.setQuantity(roomDTO.getQuantity()-1);
        roomBO.update(roomDTO);

        clearUI();

        loadRooms();
        loadAllReservations();

        setRegId();
    }

    private void saveStudent() throws SQLException, ClassNotFoundException {
        StudentDTO student = new StudentDTO();

        // initializing student
        student.setSId(stIdTxt.getText());
        student.setSName(fNameTxt.getText() + " " + lNameTxt.getText());
        student.setContact(contactTxt.getText());
        student.setAddress(addressTxt.getText());
        student.setDOB(pickDate.getValue());
        student.setGender(genderCmb.getValue());

        studentBO.update(student);

        clearUIForUpSt();

        loadAllStudents();

        STATUS = Func.RESERVE;
    }

    private void updateReservation() throws SQLException, ClassNotFoundException {
        ReservationDTO reservationDTO = reservationBO.search(regIdLbl.getText());
        if(!reservationDTO.getRoom().getRoomTypeId().equals(roomTypeCombo.getValue())){
            // then qty++ for prev room type
            RoomDTO room = roomBO.search(reservationDTO.getRoom().getRoomTypeId());
            room.setQuantity(room.getQuantity()+1);
            roomBO.update(room);
            System.out.println("updated prev room qty");
            // qty-- from new type
            room = roomBO.search(roomTypeCombo.getValue());
            room.setQuantity(room.getQuantity()-1);
            roomBO.update(room);
            System.out.println("updated new room qty");
        }

        ReservationDTO reservation = new ReservationDTO();
        RoomDTO room = new RoomDTO();
        StudentDTO st = new StudentDTO();
        room.setRoomTypeId(roomTypeCombo.getValue());
        st.setSId(studentCmb.getValue());
        // initializing reservation
        reservation.setResId(regIdLbl.getText());
        reservation.setStatus(statusCmb.getValue());
        reservation.setPaid(paidRd.isArmed());
        reservation.setRoom(room);
        reservation.setStudent(st);
        reservationBO.update(reservation);

        clearUIForUpRes();
        setRegId();

        loadAllReservations();

        STATUS = Func.RESERVE;
    }

    private void clearUIForUpSt(){
        register.setText("Register");
        stIdTxt.clear();
        fNameTxt.clear();
        addressTxt.clear();
        contactTxt.clear();
        lNameTxt.clear();
        pickDate.getEditor().clear();
        genderCmb.getSelectionModel().clearSelection();

        // enable
        reservationDetailsPane.setDisable(false);

        // warnings
        alertLbl.setText("");
        cancelBtn.setVisible(false);
        fromExistingStPane.setDisable(false);
    }

    private void clearUIForUpRes(){
        register.setText("Register");
        roomTypeCombo.getSelectionModel().clearSelection();
        statusCmb.getSelectionModel().clearSelection();
        keyMoneyLbl.setText("");
        descriptionLbl.setText("");
        paidRd.setSelected(true);
        studentCmb.getSelectionModel().clearSelection();
        studentToggle.setDisable(false);
        studentToggle.setSelected(false);

        // enable
        stDetailsPane.setDisable(false);
        studentCmb.setDisable(true);
        // warnings
        alertLbl.setText("");
        cancelBtn.setVisible(false);
        fromExistingStPane.setDisable(false);
    }

    private void clearUI(){
        stIdTxt.clear();
        fNameTxt.clear();
        addressTxt.clear();
        contactTxt.clear();
        lNameTxt.clear();
        pickDate.getEditor().clear();
        genderCmb.getSelectionModel().clearSelection();
        roomTypeCombo.getSelectionModel().clearSelection();
        statusCmb.getSelectionModel().clearSelection();
        keyMoneyLbl.setText("");
        descriptionLbl.setText("");
        paidRd.setSelected(true);
        studentCmb.getSelectionModel().clearSelection();
    }
}
