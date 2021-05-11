package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.ApplicationFacade;

public class AdminpanelController {
    @FXML
    public Button adminlogout_button;
    public ListView user_list;
    public Button delete_user;
    public Button edit_user;
    public Button create_user;
    public TextField create_password;
    public TextField create_username;
    public Button accept;
    public Button cancel;
    public RadioButton choose_producer;
    public RadioButton choose_maintainer;
    public RadioButton choose_admin;

    public void logout() {
        ApplicationFacade.logUserOut();
        Main.SwitchScene(0);
    }
    public void userListClicked() {

    }
    public void deleteUser() {

    }
    public void editUser() {

    }
    public void createUser() {

    }
    public void accept() {

    }
    public void cancel() {

    }
    public void chooseUserId(ActionEvent e) {
        String s = e.toString().toString();
        if (s.contains("Producer")) {
            choose_admin.setSelected(false);
            choose_maintainer.setSelected(false);
            choose_producer.setSelected(true);
        }
        if (s.contains("Maintainer")) {
            choose_admin.setSelected(false);
            choose_maintainer.setSelected(true);
            choose_producer.setSelected(false);
        }
        if (s.contains("Admin")) {
            choose_admin.setSelected(true);
            choose_maintainer.setSelected(false);
            choose_producer.setSelected(false);
        }
    }


    public void start() {
        user_list.setItems(Main.getAllUsers());
    }
}
