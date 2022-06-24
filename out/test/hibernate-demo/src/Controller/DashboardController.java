package Controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    public TextField username;
    public PasswordField pwd;
    public JFXToggleButton pwdToggleBtn;
    public AnchorPane mainPane;

    public void loadSignUp(MouseEvent event) {

    }

    public void getLoggedIn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("./UI/login.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
