package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;

public class Controller {

    //Where the buttons are drawn
    @FXML
    GridPane board;

    //Buttons are stored in 2D array
    DetailedButton[][] matrix;

    /**
     * Draws a board of buttons in layout.
     * @param length number of buttons on X-axis
     * @param heigth number of buttons on Y-axis
     */
    public void drawboard(byte length, byte heigth) {
        //Create event for buttons
        EventHandler<ActionEvent> click = event -> {
            DetailedButton currentDetailedButton = (DetailedButton) event.getSource();
            //todo: placeholder for method when button is clicked
            System.out.println("Pos: (" + currentDetailedButton.getPosX() + "," + currentDetailedButton.getPosY() + "), status: " + currentDetailedButton.getStatus());
        };

        matrix = new DetailedButton[length][heigth];
        for(byte x = 0; x < length; x++) {
            for(byte y = 0; y < heigth; y++) {
                //DetailedButton extends Button, but adds more information (see class)
                DetailedButton currentDetailedButton = new DetailedButton(x, y);

                //Adds method when button is clicked
                currentDetailedButton.addEventHandler(ActionEvent.ACTION, click);

                //Add to matrix
                matrix[x][y] = currentDetailedButton;

                //Add the button to board
                board.add(currentDetailedButton, x, y);
            }
        }
    }
}

