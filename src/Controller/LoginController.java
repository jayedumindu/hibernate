package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public ImageView register;
    public ImageView mgm;
    public AnchorPane loader;

    private Root registerScene;
    private Root mgmScene;

    public void initialize() throws IOException {
        registerScene = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("UI/registration.fxml")));
        mgmScene = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("UI/roomManagement.fxml")));
    }

    public void loadSubView(MouseEvent event) throws IOException {
        loader.getChildren().clear();
        ImageView view = (ImageView) event.getSource();
        switch (view.getId()) {
            case "mgm" : loader.getChildren().add((Node)mgmScene);
            case "register" : loader.getChildren().add((Node)registerScene);
        }
    }
}
