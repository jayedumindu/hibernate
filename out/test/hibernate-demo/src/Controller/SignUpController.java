package Controller;

import BO.BoFactory;
import BO.custom.UserBO;
import DTOs.UserDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    public TextField username;
    public TextField pwd;
    public TextField pwdAgain;

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
    }
}
