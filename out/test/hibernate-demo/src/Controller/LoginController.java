package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public ImageView register;
    public ImageView mgm;
    public AnchorPane loader;

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
}
