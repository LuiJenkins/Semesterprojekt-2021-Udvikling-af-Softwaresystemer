package presentation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.*;

public class MainmenuController {
    @FXML
    public TextField username;
    public TextField password;
    public Button login_button;

    public TextField search_term;
    public Button search_button;
    public ListView<Program> suggestions;

    public TextArea credits;

    public void login() {
        System.out.println("Login Event");
        Main.SwitchScene(1);
    }
    public void search() {

    }
}
