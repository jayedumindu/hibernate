package Controller;

import BO.BoFactory;
import BO.custom.RoomBO;
import DTOs.RoomDTO;
import TDMs.RoomTDM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManagementController {

    private final RoomBO roomBo;

    public JFXTextField roomId;
    public JFXTextField roomType;
    public JFXTextField keyMoney;
    public Spinner<Integer> noOfRooms;

    public JFXButton saveBtn;

    public TableView<RoomTDM> availability;
    public TableColumn<RoomTDM,String> roomTypeAvl;
    public TableColumn<RoomTDM,String> description;
    public TableColumn<RoomTDM,Double> keyMoneyAvl;
    public TableColumn<RoomTDM,Integer> qty;
    public JFXTabPane tabPane;
    public Tab addRoomTab;
    public Tab inventoryTab;
    public Label alertLbl;
    public Button cancel;

    private SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);

    private ObservableList<RoomTDM> roomList = FXCollections.observableArrayList();

    public RoomManagementController() throws IOException {
        roomBo = (RoomBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        factory.setValue(1);
        noOfRooms.setValueFactory(factory);

        availability.setItems(roomList);

        roomTypeAvl.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        description.setCellValueFactory(new PropertyValueFactory<>("type"));
        keyMoneyAvl.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadAll();
    }

    public void loadAll() throws SQLException, ClassNotFoundException {
        roomList.clear();
        ArrayList<RoomDTO> all = roomBo.getAll();
        for (RoomDTO dto:
             all) {
            roomList.add(new RoomTDM(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getQuantity()));
        }
    }

    public void rmvRoom(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RoomTDM selectedItem = availability.getSelectionModel().getSelectedItem();
        if(roomBo.delete(selectedItem.getRoomTypeId())){
            new Alert(Alert.AlertType.INFORMATION,"deleted!").show();
            roomList.remove(selectedItem);
        }else {
            new Alert(Alert.AlertType.ERROR,"interrupted!").showAndWait();
        }
    }

    // filler
    public void updateRoom(ActionEvent actionEvent) {
        RoomTDM selectedItem = availability.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().select(addRoomTab);

        roomId.setText(selectedItem.getRoomTypeId());
        roomType.setText(selectedItem.getType());
        keyMoney.setText(String.valueOf(selectedItem.getKeyMoney()));

        factory.setValue(selectedItem.getQuantity());

        saveBtn.setText("SAVE CHANGES");

        alertLbl.setText("You're updating a Room, click cancel to invoke!");
        cancel.setVisible(true);
    }

    public void addRoom(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RoomDTO room = new RoomDTO();
        room.setRoomTypeId(roomId.getText());
        room.setType(roomType.getText());
        room.setKeyMoney(Double.parseDouble(keyMoney.getText()));
        room.setQuantity(noOfRooms.getValueFactory().getValue());
        System.out.println(room.getQuantity());
        // check if user is updating a existing room
        if(roomBo.exist(room.getRoomTypeId())){
            // then update
            System.out.println("room exists..updating!");
            if(roomBo.update(room)){
                new Alert(Alert.AlertType.INFORMATION,"updated!").show();
                alertLbl.setText("");
                cancel.setVisible(false);
            }else {
                new Alert(Alert.AlertType.ERROR,"updating interrupted!").showAndWait();
            }
        }
        else {
            // else save
            if(roomBo.save(room)){
                new Alert(Alert.AlertType.INFORMATION,"saved!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"saving interrupted!").showAndWait();
            }
        }
        clearUI();
        loadAll();
    }

    private void clearUI(){
        roomId.clear();
        roomType.clear();
        keyMoney.clear();
        noOfRooms.getValueFactory().setValue(1);
    }

    public void cancelUpdate(ActionEvent actionEvent) {
        clearUI();
        alertLbl.setText("");
        cancel.setVisible(false);
        saveBtn.setText("SAVE");
    }
}
