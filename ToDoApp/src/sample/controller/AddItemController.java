package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.animation.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label notTaskLabel;

    @FXML
    private ImageView addButton;

    @FXML
    void initialize() {

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();
            System.out.println("Clicked");
            addButton.relocate(0, 0);
            notTaskLabel.relocate(0, 0);
            addButton.setOpacity(0);
            notTaskLabel.setOpacity(0);

            try
            {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));
                rootPane.getChildren().setAll(formPane);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });

    }
}
