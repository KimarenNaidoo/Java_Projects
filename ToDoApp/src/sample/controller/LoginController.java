package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animation.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignUpButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try
            {
                loader.load();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        loginButton.setOnAction(event -> {
            String loginText = loginUsername.getText().trim();
            String loginPswd = loginPassword.getText().trim();

            User user = new User();
            user.setUsername(loginText);
            user.setPassword(loginPswd);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;

            try
            {
                while (userRow.next())
                {
                    counter++;
                    String name = userRow.getString("firstname");
                    System.out.println("Welcome " + name);
                }

                if (counter == 1)
                {
                    showAddItemScreen();
                }
                else if (counter == 0)
                {
                    Shaker shakerUser = new Shaker(loginUsername);
                    Shaker shakerPswd = new Shaker(loginPassword);
                    shakerUser.shake();
                    shakerPswd.shake();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        });

    }

    private void showAddItemScreen()
    {
        loginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));

        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}

