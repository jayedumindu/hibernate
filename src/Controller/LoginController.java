package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public ImageView register;
    public ImageView mgm;
    public AnchorPane loader;
    public AnchorPane mainPane;

    public void initialize() throws IOException {

    }

    public void loadSubView(MouseEvent event) throws IOException {
        loader.getChildren().clear();
        ImageView view = (ImageView) event.getSource();
        String URI = null;
        switch (view.getId()) {
            case "mgm" : URI = "./UI/roomManagement.fxml" ; break;
            case "register" : URI = "./UI/registration.fxml" ; break;
        }
        Node pane = FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource(URI)));
        loader.getChildren().add(pane);
    }

    public void moseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void iconAnimationWhenEntered(MouseEvent event) {
    }

    public void iconAnimationWhenExited(MouseEvent event) {
    }

    public void backToLogin(MouseEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/UI/dashboard.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
