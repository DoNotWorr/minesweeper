package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Controller {

    //Where the buttons are drawn
    @FXML
    GridPane board;

    //Buttons are stored in 2D array
    DetailedButton[][] matrix;

    /**
     * Draws a board of buttons in layout.
     *
     * @param length number of buttons on X-axis
     * @param heigth number of buttons on Y-axis
     */
    public void drawboard(int length, int heigth, int bombQuantity) {
        addButtons(length, heigth);
        List<Integer> bombIndexes = generateBombIndexes(bombQuantity, length * heigth - 1);
        addBombs(bombIndexes, length, heigth);
    }

    private void addButtons(int length, int heigth) {
        //Create event for buttons
        EventHandler<ActionEvent> click = event -> {
            DetailedButton currentDetailedButton = (DetailedButton) event.getSource();
            //todo: placeholder for method when button is clicked
            System.out.println("Pos: (" + currentDetailedButton.getPosX() + "," + currentDetailedButton.getPosY() + "), status: " + currentDetailedButton.getStatus());
        };

        matrix = new DetailedButton[length][heigth];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < heigth; y++) {
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

    private List<Integer> generateBombIndexes(int bombQuantity, int buttonIndexBoundry) {
        List<Integer> bombIndexes = new ArrayList<>();
        List<Integer> buttonIndex = new ArrayList<>();
        for (int i = 0; i < buttonIndexBoundry; i++) {
            buttonIndex.add(i);
        }
        Collections.shuffle(buttonIndex);

        for (int i = 0; i < bombQuantity; i++) {
            bombIndexes.add(buttonIndex.get(i));
        }
        return bombIndexes;
    }

    private void addBombs(List<Integer> bombIndexes, int length, int heigth) {
        bombIndexes.forEach(bomb -> {
            int xPos;
            int yPos;
            xPos = bomb / heigth;
            yPos = bomb % heigth;
            matrix[xPos][yPos].setBomb();

            findBombNeighbors(xPos, yPos, length - 1, heigth - 1);
        });
    }

    private void findBombNeighbors(int xPos, int yPos, int xMaxPos, int yMaxPos) {
/*
         Det finns 9 olika fall att kontrollera.

         * utgångsposition (bomb)
         x = utanför spelbrädet
         0 = rutor som behöver kontrolleras

         X X X  X X X   X X X
         X * 0	0 * 0	0 * X
         X 0 0	0 0 0	0 0 X

         X 0 0	0 0 0	0 0 X
         X * 0	0 * 0	0 * X
         X 0 0	0 0 0	0 0 X

         X 0 0	0 0 0	0 0 X
         X * 0	0 * 0	0 * X
         X X X	X X X	X X X
*/
        if (xPos == 0) {                    //Alla fall på vänster sida
            if (yPos == 0) {                //1. NW
                xPos++;
                matrix[xPos][yPos++].incrementStatus();
                matrix[xPos--][yPos].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            } else if (yPos == yMaxPos) {   //2. SW
                yPos--;
                matrix[xPos++][yPos].incrementStatus();
                matrix[xPos][yPos++].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            } else {                        //3. W
                yPos--;
                matrix[xPos++][yPos].incrementStatus();
                matrix[xPos][yPos++].incrementStatus();
                matrix[xPos][yPos++].incrementStatus();
                matrix[xPos--][yPos].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            }
        } else if (xPos == xMaxPos) {       //Alla fall på höger sida
            if (yPos == 0) {                //4. NE
                yPos++;
                matrix[xPos--][yPos].incrementStatus();
                matrix[xPos][yPos--].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            } else if (yPos == yMaxPos) {   //5. SE
                xPos--;
                matrix[xPos][yPos--].incrementStatus();
                matrix[xPos++][yPos].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            } else {                        //6. E
                yPos++;
                matrix[xPos--][yPos].incrementStatus();
                matrix[xPos][yPos--].incrementStatus();
                matrix[xPos][yPos--].incrementStatus();
                matrix[xPos++][yPos].incrementStatus();
                matrix[xPos][yPos].incrementStatus();
            }
        } else if (yPos == 0) {             //7. Överkant, men inte ett hörn
            xPos++;
            matrix[xPos][yPos++].incrementStatus();
            matrix[xPos--][yPos].incrementStatus();
            matrix[xPos--][yPos].incrementStatus();
            matrix[xPos][yPos--].incrementStatus();
            matrix[xPos][yPos].incrementStatus();
        } else if (yPos == yMaxPos) {       //8. Underkant, men inte ett hörn
            xPos--;
            matrix[xPos][yPos--].incrementStatus();
            matrix[xPos++][yPos].incrementStatus();
            matrix[xPos++][yPos].incrementStatus();
            matrix[xPos][yPos++].incrementStatus();
            matrix[xPos][yPos].incrementStatus();
        } else {                            //9. Någonstans i mitten
            xPos--;
            matrix[xPos][yPos--].incrementStatus();
            matrix[xPos++][yPos].incrementStatus();
            matrix[xPos++][yPos].incrementStatus();
            matrix[xPos][yPos++].incrementStatus();
            matrix[xPos][yPos++].incrementStatus();
            matrix[xPos--][yPos].incrementStatus();
            matrix[xPos--][yPos].incrementStatus();
            matrix[xPos][yPos].incrementStatus();
        }
    }
}

