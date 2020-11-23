package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
     * Draws a board of buttons, generates the desired amount of bombs in random positions. The buttons are saved in instance-variable matrix[][]
     *
     * @param length number of buttons on X-axis
     * @param heigth number of buttons on Y-axis
     */
    public void drawboard(int length, int heigth, int bombQuantity) {
        addButtons(length, heigth);
        List<Integer> bombIndexes = generateBombIndexes(bombQuantity, length * heigth - 1);
        addBombs(bombIndexes, length, heigth);
    }

    /**
     * Draws buttons and assigns click-method
     * @param length number of buttons on x-axis
     * @param heigth number of buttons on y-axis
     */
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

    /**
     * Randomizes a unique index for each bomb
     * @param bombQuantity number of bombs
     * @param buttonIndexBoundry the biggest index for the board. For example, a board of 3 by 3 buttons has a one-dimensional index from 0 to 8, which makes the biggest index: 8.
     * @return a list of indexes where bombs should be placed
     */
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

    /**
     * Turns one-dimensional index of bombs into two dimensional index, where (0,0) is top left and (length-1, heigth-1) is bottom right.
     * This method sets the status -1 (symbolizes a bomb) on requested indexes.
     *
     * The following example board with <B>length: 3</B> and <B>height: 3</B> visualizes the one-dimensional index in two dimensions.
     * 0 3 6
     * 1 4 7
     * 2 5 8
     * @param bombIndexes which indexes should be a bomb
     * @param length number of buttons on x-axis
     * @param heigth number of buttons on y-axis
     *
     */
    private void addBombs(List<Integer> bombIndexes, int length, int heigth) {
        bombIndexes.forEach(bomb -> {
            int xPos;
            int yPos;
            xPos = bomb / heigth;
            yPos = bomb % heigth;
            matrix[xPos][yPos].setBomb();

            try {
                runMethodOnNeighbors(xPos, yPos, length - 1, heigth - 1, DetailedButton.class.getMethod("incrementStatus"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Finds all neighbors of a target button and runs a method on neighbor buttons
     * @param xPos x-coordinate of target button
     * @param yPos y-coordinate of target button
     * @param xMaxPos the biggest x-coordinate on the board
     * @param yMaxPos the biggest y-coordinate on the board
     * @param method a method to run on all found neighbor buttons
     * @throws IllegalAccessException see method.invoke
     * @throws IllegalArgumentException see method.invoke
     * @throws InvocationTargetException see method.invoke
     * @throws NullPointerException see method.invoke
     * @throws ExceptionInInitializerError see method.invoke
     */
    private void runMethodOnNeighbors(int xPos, int yPos, int xMaxPos, int yMaxPos, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NullPointerException, ExceptionInInitializerError {
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
                method.invoke(matrix[xPos][yPos++]);
                method.invoke(matrix[xPos--][yPos]);
                method.invoke(matrix[xPos][yPos]);
            } else if (yPos == yMaxPos) {   //2. SW
                yPos--;
                method.invoke(matrix[xPos++][yPos]);
                method.invoke(matrix[xPos][yPos++]);
                method.invoke(matrix[xPos][yPos]);
            } else {                        //3. W
                yPos--;
                method.invoke(matrix[xPos++][yPos]);
                method.invoke(matrix[xPos][yPos++]);
                method.invoke(matrix[xPos][yPos++]);
                method.invoke(matrix[xPos--][yPos]);
                method.invoke(matrix[xPos][yPos]);
            }
        } else if (xPos == xMaxPos) {       //Alla fall på höger sida
            if (yPos == 0) {                //4. NE
                yPos++;
                method.invoke(matrix[xPos--][yPos]);
                method.invoke(matrix[xPos][yPos--]);
                method.invoke(matrix[xPos][yPos]);
            } else if (yPos == yMaxPos) {   //5. SE
                xPos--;
                method.invoke(matrix[xPos][yPos--]);
                method.invoke(matrix[xPos++][yPos]);
                method.invoke(matrix[xPos][yPos]);
            } else {                        //6. E
                yPos++;
                method.invoke(matrix[xPos--][yPos]);
                method.invoke(matrix[xPos][yPos--]);
                method.invoke(matrix[xPos][yPos--]);
                method.invoke(matrix[xPos++][yPos]);
                method.invoke(matrix[xPos][yPos]);
            }
        } else if (yPos == 0) {             //7. Överkant, men inte ett hörn
            xPos++;
            method.invoke(matrix[xPos][yPos++]);
            method.invoke(matrix[xPos--][yPos]);
            method.invoke(matrix[xPos--][yPos]);
            method.invoke(matrix[xPos][yPos--]);
            method.invoke(matrix[xPos][yPos]);
        } else if (yPos == yMaxPos) {       //8. Underkant, men inte ett hörn
            xPos--;
            method.invoke(matrix[xPos][yPos--]);
            method.invoke(matrix[xPos++][yPos]);
            method.invoke(matrix[xPos++][yPos]);
            method.invoke(matrix[xPos][yPos++]);
            method.invoke(matrix[xPos][yPos]);
        } else {                            //9. Någonstans i mitten
            xPos--;
            method.invoke(matrix[xPos][yPos--]);
            method.invoke(matrix[xPos++][yPos]);
            method.invoke(matrix[xPos++][yPos]);
            method.invoke(matrix[xPos][yPos++]);
            method.invoke(matrix[xPos][yPos++]);
            method.invoke(matrix[xPos--][yPos]);
            method.invoke(matrix[xPos--][yPos]);
            method.invoke(matrix[xPos][yPos]);
        }
    }
}

