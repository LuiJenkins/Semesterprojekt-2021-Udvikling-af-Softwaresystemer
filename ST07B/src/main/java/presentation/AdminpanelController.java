package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.ApplicationFacade;
import logic.CurrentUser;
import logic.Program;
import logic.nextGenPersistance.PersistanceFacade;

public class AdminpanelController {
    @FXML
    public Button adminlogout_button;
    public ListView user_list;
    public Button delete_user;
    public Button edit_user;
    public Button create_user;
    public TextField create_password;
    public TextField create_username;
    public TextField producer_id;
    public TextField full_name;
    public Button accept;
    public Button cancel;
    public RadioButton choose_producer;
    public RadioButton choose_maintainer;
    public RadioButton choose_admin;

    public CurrentUser currentSelectedUser;
    public int selectedRoleID = 0;

    public void logout() {
        ApplicationFacade.logUserOut();
        Main.SwitchScene(0);
    }
    public void userListClicked() {
        currentSelectedUser = (CurrentUser) user_list.getSelectionModel().getSelectedItem();
        create_password.setText(currentSelectedUser.password);
        create_username.setText(currentSelectedUser.userName);
        producer_id.setText(currentSelectedUser.producerID+"");
        full_name.setText(currentSelectedUser.getFullName());
        if (currentSelectedUser.getUserRole() == 1) {
            choose_admin.setSelected(false);
            choose_maintainer.setSelected(false);
            choose_producer.setSelected(true);
        }
        if (currentSelectedUser.getUserRole() == 2) {
            choose_admin.setSelected(false);
            choose_maintainer.setSelected(true);
            choose_producer.setSelected(false);
        }
        if (currentSelectedUser.getUserRole() == 3) {
            choose_admin.setSelected(true);
            choose_maintainer.setSelected(false);
            choose_producer.setSelected(false);
        }
    }
    public void deleteUser() {
        if (currentSelectedUser != null) {
            PersistanceFacade.currentUserMapper.removeFromDB(currentSelectedUser.userID);
        }
        user_list.setItems(Main.getAllUsers());
    }
    public void editUser() {
        if (currentSelectedUser != null) {
            int producerid;
            try {
                producerid = Integer.parseInt(producer_id.getText());
            } catch (NumberFormatException e) {
                producerid = 0;
            }
            if (create_username != null) {currentSelectedUser.setUserName(create_username.getText());}
            if (create_password != null) {currentSelectedUser.setPassword(create_password.getText());}
            if (full_name != null) {currentSelectedUser.setFullName(full_name.getText());}
            if (producerid >= 1 && producerid <= 3) {currentSelectedUser.setProducerID(producerid);}
            if (selectedRoleID >= 1 && selectedRoleID <= 3) {currentSelectedUser.setUserRole(selectedRoleID);}
            PersistanceFacade.currentUserMapper.addToDB(currentSelectedUser);
        }
        user_list.setItems(Main.getAllUsers());
    }
    public void createUser() {
        int maxid = 0;
        int producerid = Integer.parseInt(producer_id.getText());
        for (CurrentUser cu : Main.getAllUsers()) {
            if (cu.getUserID() > maxid) {maxid = cu.getUserID();}
            if (create_username.getText().equals(cu.userName)) {return;}
        }
        if (create_username.getText() != null && create_password.getText() != null && full_name.getId() != null && selectedRoleID != 0) {
            if (selectedRoleID == 1 && producerid == 0) {return;}
            CurrentUser currentuser = new CurrentUser(maxid + 1, create_username.getText(), create_password.getText(), full_name.getText(), selectedRoleID, producerid);
            PersistanceFacade.currentUserMapper.addToDB(currentuser);
        }
        user_list.setItems(Main.getAllUsers());
    }
    public void chooseUserId(ActionEvent event) {
        String s = event.toString().toString();
        if (s.contains("Producer")) {
            if (selectedRoleID == 1) {
                unselect();
            } else {
                choose_admin.setSelected(false);
                choose_maintainer.setSelected(false);
                choose_producer.setSelected(true);
                selectedRoleID = 1;
            }
        }
        if (s.contains("Maintainer")) {
            if (selectedRoleID == 2) {
                unselect();
            } else {
                choose_admin.setSelected(false);
                choose_maintainer.setSelected(true);
                choose_producer.setSelected(false);
                selectedRoleID = 2;
            }
        }
        if (s.contains("Admin")) {
            if (selectedRoleID == 3) {
                unselect();
            } else {
                choose_admin.setSelected(true);
                choose_maintainer.setSelected(false);
                choose_producer.setSelected(false);
                selectedRoleID = 3;
            }
        }
    }
    private void unselect() {
        choose_admin.setSelected(false);
        choose_maintainer.setSelected(false);
        choose_producer.setSelected(false);
        selectedRoleID = 0;
    }

    public void start() {
        user_list.setItems(Main.getAllUsers());
    }
}
