package presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {
    @FXML
    public Button logout_button;
    public Label role_loggedin;
    //
    @FXML
    public ListView program_list;
    public TextArea program_preview;
    public TextField newprogram_name;
    public Button edit_chosen_program;
    public Button delete_chosen_program;
    public Button accept_chosen_program;
    public Button deny_chosen_program;
    //
    @FXML
    public ListView category_list;
    public TextArea program_preview_second;
    public Button edit_chosen_category;
    public Button delete_chosen_category;
    public Button back_to_programs;
    public Button send_program_to_review;
    //
    @FXML
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


    public void logout() {

    }
    public void makeProgram() {

    }
    public void editChosenProgram() {

    }
    public void deleteChosenProgram() {

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
}
