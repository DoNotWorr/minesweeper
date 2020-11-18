package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class DetailedButton extends Button {
    /**
     * Extends button istället för att innehålla button
     */
    //private Button button;
    //bomb: -1
    //0-8: neighbor bombs.
    private byte status;
    private byte posX;
    private byte posY;

    public DetailedButton(byte posX, byte posY) {
        //this.button = new Button();
        this.status = 0;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Extends button istället för att innehålla button
     */
    /*public Button getButton() {
        return button;
    }*/
    public byte getStatus() {
        return status;
    }

    public byte getPosX() {
        return posX;
    }

    public byte getPosY() {
        return posY;
    }
}
