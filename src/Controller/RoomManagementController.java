package Controller;

import BO.BoFactory;
import BO.SuperBO;
import BO.custom.RoomBO;
import DTOs.RoomDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Room;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class RoomManagementController {

    private final RoomBO roomBo;

    public JFXTextField roomId;
    public JFXTextField roomType;
    public JFXTextField keyMoney;
    public Spinner<Integer> noOfRooms;
    public JFXButton saveBtn;
    public TableView<Room> availability;
    public TableColumn<Room,String> roomTypeAvl;
    public TableColumn<Room,String> description;
    public TableColumn<Room,Double> keyMoneyAvl;
    public TableColumn<Room,Integer> qty;

    public RoomManagementController() throws IOException {
        roomBo = (RoomBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM);
    }

    public void initialize(){
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        factory.setValue(1);
        noOfRooms.setValueFactory(factory);

        roomTypeAvl.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        description.setCellValueFactory(new PropertyValueFactory<>("type"));
        keyMoneyAvl.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void rmvRoom(ActionEvent actionEvent) {
    }

    public void updateRoom(ActionEvent actionEvent) {
    }

    public void addRoom(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RoomDTO ac = new RoomDTO("RM-001", "AC", 35000, 20);
        ac.setQuantity(30);
        for (RoomDTO en:
            roomBo.getAll()) {
            System.out.println(en);
        }
    }
}
