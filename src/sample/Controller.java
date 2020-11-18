package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {

    //Where the buttons are drawn
    @FXML
    GridPane board;

    public void drawboard(byte length, byte height) {
        for(byte x = 0; x < length; x++) {
            for(byte y = 0; y < height; y++) {
                //Creates a new button
                Button currentButton = new Button();

                //Adds button at (x,y) coordinate in the gridpane
                board.add(currentButton, x, y);
            }
        }

    }
}

