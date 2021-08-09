package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.database.DatabaseHandler;
import sample.model.User;

public class SignupController
{
    @FXML
    private TextField signupFirstName;

    @FXML
    private TextField signupLastName;

    @FXML
    private TextField signupUsername;

    @FXML
    private TextField signupLocation;

    @FXML
    private CheckBox signupCheckBoxMale;

    @FXML
    private CheckBox signupCheckBoxFemale;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Button signupButton;

    @FXML
    void initialize() {
        signupButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String gender;

        if(signupCheckBoxFemale.isSelected())
            gender = "Female";
        else
            gender = "Male";

        User user = new User(signupFirstName.getText().trim(), signupLastName.getText().trim(), signupUsername.getText().trim(), signupPassword.getText().trim(), signupLocation.getText().trim(), gender);
        databaseHandler.signUpUser(user);
    }
}
