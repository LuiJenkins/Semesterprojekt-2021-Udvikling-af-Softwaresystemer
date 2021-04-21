package presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import logic.ApplicationFacade;
import logic.Program;

public class DashboardController implements startInterface{
    @FXML
    public Button logout_button;
    public Label role_loggedin;
    //
    @FXML
    public AnchorPane program_pane;
    public ListView program_list;
    public TextArea program_preview;
    public TextField newprogram_name;
    public Button edit_chosen_program;
    public Button delete_chosen_program;
    public Button accept_chosen_program;
    public Button deny_chosen_program;
    //
    @FXML
    public AnchorPane category_pane;
    public ListView category_list;
    public TextArea program_preview_second;
    public Button edit_chosen_category;
    public Button delete_chosen_category;
    public Button back_to_programs;
    public Button send_program_to_review;
    //
    @FXML
    public AnchorPane person_pane;
    public ListView person_list;
    public ListView category_preview;
    public TextField person_name;
    public TextField person_desc;
    public Button set_person_name;
    public Button add_new_person_with_name;
    public Button add_person_to_category;
    public Button remove_person_from_category;
    public Button remove_person_from_database;
    public Button back_to_category;

    public Program selectedProgram;

    public void logout() {
        Main.SwitchScene(0);
    }

    public void programListClick() {
        selectedProgram = (Program)program_list.getSelectionModel().getSelectedItem();
        if (selectedProgram != null) {
            program_preview.setText(selectedProgram.toText());
        }
    }


    public void makeProgram() {
        if (newprogram_name.getText() != null) {
            System.out.println("Making new program: "+newprogram_name.getText());
            ApplicationFacade.makeNewProgram(1,newprogram_name.getText());
        }
        program_list.setItems(Main.getAllPrograms());
    }
    public void editChosenProgram() {

    }
    public void deleteChosenProgram() {
        ApplicationFacade.deleteProgram(selectedProgram);
        program_list.setItems(Main.getAllPrograms());
    }
    public void acceptChosenProgram() {

    }
    public void denyChosenProgram() {

    }

    public void editChosenCategory() {

    }
    public void deleteChosenCategory() {

    }
    public void goBackToPrograms() {

    }
    public void sendCreditToReview() {

    }

    public void setPersonName() {

    }
    public void makeNewPersonWithName() {

    }
    public void addPersonToCategory() {

    }
    public void removePersonFromCategory() {

    }
    public void deletePersonFromDatabase() {

    }
    public void goBackToCategory() {

    }

    @Override
    public void start() {
        program_pane.setVisible(true);
        category_pane.setVisible(false);
        person_pane.setVisible(false);
        program_list.setItems(Main.getAllPrograms());
    }
}
