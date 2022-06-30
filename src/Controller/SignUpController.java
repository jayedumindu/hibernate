package Controller;

import BO.BoFactory;
import BO.custom.UserBO;
import DTOs.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpController {

    public TextField username;
    public TextField pwd;
    public TextField pwdAgain;
    public AnchorPane mainPane;

    private UserBO userBO;

    public void initialize() throws IOException {
        userBO = (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);
    }

    public void signup(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserDTO dto = new UserDTO();
        if(pwd.getText().equalsIgnoreCase(pwdAgain.getText())){
            dto.setPassword(pwd.getText());
            dto.setUsername(username.getText());
        }
        userBO.save(dto);
        clearUI();
    }

    public void animateWhenEntered(MouseEvent event) {
    }

    public void animateWhenExited(MouseEvent event) {
    }

    public void backToDashboard(MouseEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/UI/dashboard.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void clearUI(){
        username.clear();
        pwd.clear();
        pwdAgain.clear();
    }
}
