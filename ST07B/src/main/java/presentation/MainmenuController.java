package presentation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.*;

public class MainmenuController implements startInterface{
    @FXML
    public TextField username;
    public TextField password;
    public Button login_button;

    public TextField search_term;
    public Button search_button;
    public ListView<Program> suggestions;

    public TextArea credits;
    public Program selectedProgram;

    public void login() {
        System.out.println("Login Event");
        Main.SwitchScene(1);
    }
    public void search() {
        if (selectedProgram != null) {
            Main.displayCredits(selectedProgram);
        }
    }
    public void updateSearch() {
        selectedProgram = suggestions.getSelectionModel().getSelectedItem();
        search_term.setText(selectedProgram.toString());

    }

    @Override
    public void start() {

    }
}
