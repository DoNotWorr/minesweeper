package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Controller {

    //Where the buttons are drawn
    @FXML
    GridPane board;

    public void drawboard(byte length, byte height) {
        for(byte x = 0; x < length; x++) {
            //todo fortsätt här
            ArrayList<Button> buttonRow = new ArrayList<>();
            for(byte y = 0; y < height; y++) {
                //todo fortsätt här
                ArrayList<Button> buttonColumn = new ArrayList<>();
                //Creates a new button
                Button currentButton = new Button();

                //Adds button at (x,y) coordinate in the gridpane
                board.add(currentButton, x, y);
            }
        }
    }
}

