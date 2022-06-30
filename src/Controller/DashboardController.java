package Controller;

import BO.BoFactory;
import BO.custom.UserBO;
import DTOs.UserDTO;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class DashboardController {

    public TextField username;
    public PasswordField pwd;
    public JFXToggleButton pwdToggleBtn;
    public AnchorPane mainPane;

    private UserBO userBO;

    public void initialize() throws IOException {
        userBO = (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);
    }

    public void loadSignUp(MouseEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/UI/signup.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void getLoggedIn(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        UserDTO dto = userBO.search(username.getText());
        checkup(dto);
    }

    private void checkup(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        if(dto!=null){
            if(pwd.getText().equalsIgnoreCase(dto.getPassword())){
                // log in successful
                Stage stage = (Stage) mainPane.getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/UI/login.fxml")));
                stage.setScene(new Scene(root));
                stage.show();
            }else  new Alert(Alert.AlertType.ERROR,"Password is incorrect!").show();
        }else new Alert(Alert.AlertType.ERROR,"User not found!").show();
    }
}
