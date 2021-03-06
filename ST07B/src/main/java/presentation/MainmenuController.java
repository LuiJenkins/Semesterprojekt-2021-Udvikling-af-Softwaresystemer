package presentation;
/*
A Controler of the systems main screen, the main screen displays the credits for a ordinary viewer
If you log on to the system you switch to the DashboardController

*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import logic.*;

public class MainmenuController implements startInterface{
    @FXML
    public TextField username;
    public TextField password;
    public Button login_button;

    public TextField search_term;
    public Button search_button;
    public ListView<Object> suggestions;

    public TextArea credits;
    public ImageView qrcode;

    public RadioButton searchCredit;
    public RadioButton searchPerson;

    private String SearchMode = "credit";
    public Program selectedProgram;
    public Person selectedPerson;

    public void login() {
        System.out.println("Login Event");
        ApplicationFacade.logUserIn(username.getText(), password.getText());
        if(LoginHandler.currentUser.isAllowed(1)) Main.SwitchScene(1);
    }
    public void search() {
        if (selectedProgram != null) {
            Main.displayCredits(selectedProgram);
        }
    }
    public void updateSearch() {
        if (suggestions.getSelectionModel().getSelectedItem() instanceof Program) {
            selectedProgram = (Program)suggestions.getSelectionModel().getSelectedItem();
            search_term.setText(selectedProgram.toString());
            Main.displayCredits(selectedProgram);
            try {
                qrcode.setImage(QRFacade.GetQRCode("https://www.google.com/search?q="+selectedProgram.getName().replace(' ','+'),128,128));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (suggestions.getSelectionModel().getSelectedItem() instanceof Person) {
            selectedPerson = (Person)suggestions.getSelectionModel().getSelectedItem();
            search_term.setText(selectedPerson.toString());
            Main.displayPersonInfo(selectedPerson);
        }
    }
    public void switchSearch(ActionEvent event) {
        System.out.println(event.getSource().toString().contains("Søg Krediteringer"));
        if (event.getSource().toString().contains("Søg Krediteringer")) {
            suggestions.setItems(Main.getAllApprovedPrograms());
            searchCredit.setSelected(true);
            searchPerson.setSelected(false);
            SearchMode="credit";
            search_button.setText("Vis Program");
        } else {
            suggestions.setItems(Main.getAllPersons());
            searchCredit.setSelected(false);
            searchPerson.setSelected(true);
            SearchMode="person";
            search_button.setText("Vis Person");
        }
    }

    public void KeyPressedInSearch() {
        if (SearchMode.equals("credit")) {
            suggestions.setItems(Main.searchProgram(search_term.getText()));
        } else if (SearchMode.equals("person")) {
            suggestions.setItems(Main.searchPerson(search_term.getText()));
        }
    }

    @Override
    public void start() {
        suggestions.setItems(Main.getAllApprovedPrograms());
        searchCredit.setSelected(true);
        searchPerson.setSelected(false);
    }
}
