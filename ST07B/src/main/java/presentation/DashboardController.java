package presentation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import logic.ApplicationFacade;
import logic.Category;
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
    public TextField new_category_name;
    public Button edit_chosen_category;
    public Button add_category;
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
    public Category selectedCategory;

    public void logout() {
        Main.SwitchScene(0);
    }

    public void programListClick() {
        selectedProgram = (Program)program_list.getSelectionModel().getSelectedItem();
        if (selectedProgram != null) {
            program_preview.setText(selectedProgram.toText());
            program_preview_second.setText(selectedProgram.toText());
        }
    }
    public void categoryListClick() {
        selectedCategory = (Category)category_list.getSelectionModel().getSelectedItem();
        System.out.println("selected category"+selectedCategory);
    }

    public void makeProgram() {
        if (newprogram_name.getText() != null) {
            System.out.println("Making new program: "+newprogram_name.getText());
            ApplicationFacade.makeNewProgram(1,newprogram_name.getText());
        }
        program_list.setItems(Main.getAllPrograms());
    }
    public void editChosenProgram() {
        setpage(1);
        category_list.setItems(Main.getAllCategoryForProgram(selectedProgram));
        program_preview.setText(selectedProgram.toText());
        program_preview_second.setText(selectedProgram.toText());
    }
    public void deleteChosenProgram() {
        ApplicationFacade.deleteProgram(selectedProgram);
        program_list.setItems(Main.getAllPrograms());
    }
    public void acceptChosenProgram() {

    }
    public void denyChosenProgram() {

    }
    public void addCategory() {
        if (new_category_name.getText() != null) {
            ApplicationFacade.makeNewCategory(selectedProgram,new_category_name.getText());
            System.out.println("Making new category: "+new_category_name.getText());
        }
        category_list.setItems(Main.getAllCategoryForProgram(selectedProgram));
        program_preview.setText(selectedProgram.toText());
        program_preview_second.setText(selectedProgram.toText());
    }
    public void editChosenCategory() {

    }
    public void deleteChosenCategory() {
        ApplicationFacade.deleteCategory(selectedCategory,selectedProgram);
        category_list.setItems(Main.getAllCategoryForProgram(selectedProgram));
        program_preview.setText(selectedProgram.toText());
        program_preview_second.setText(selectedProgram.toText());

    }
    public void goBackToPrograms() {
        setpage(0);
        program_preview.setText(selectedProgram.toText());
        program_preview_second.setText(selectedProgram.toText());
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
    public void setpage(int page) {
        if (page == 0) {
            program_pane.setVisible(true);
            category_pane.setVisible(false);
            person_pane.setVisible(false);
        } else if (page == 1) {
            program_pane.setVisible(false);
            category_pane.setVisible(true);
            person_pane.setVisible(false);
        } else if (page == 2) {
            program_pane.setVisible(false);
            category_pane.setVisible(false);
            person_pane.setVisible(true);
        }
    }
}
