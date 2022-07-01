package Controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
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
        if(event.getSource() instanceof Text){
            Text txt = (Text) event.getSource();
            DropShadow glow = new DropShadow();
            glow.setColor(Color.ORANGERED);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            txt.setEffect(glow);
        }
    }

    public void mouseExited(MouseEvent event) {
        if(event.getSource() instanceof Text){
            Text txt = (Text) event.getSource();
            txt.setEffect(null);
        }
    }

    public void iconAnimationWhenEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();


            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();


        }
    }

    public void iconAnimationWhenExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            //icon.setEffect(null);
        }
    }

    public void backToLogin(MouseEvent event) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/UI/dashboard.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
