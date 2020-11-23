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
    private int status;
    private int posX;
    private int posY;

    public DetailedButton(int posX, int posY) {
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
    public int getStatus() {
        return status;
    }

    public void setBomb() {
        this.status = -1;
    }

    public boolean incrementStatus() {
        if (this.status < 0) {
            return false;
        } else {
            this.status++;
            return true;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
