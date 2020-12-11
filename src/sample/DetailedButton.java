package sample;

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
    private String buttonId; //todo boolean isClicked instead of buttonID?

    public DetailedButton(int posX, int posY) {
        this.status = 0;
        this.posX = posX;
        this.posY = posY;
        this.buttonId = posX+","+posY;

        this.setId("button");

        //Button design
        this.setPrefWidth(30.0);
        this.setMinWidth(30.0);
        this.setMaxWidth(30.0);

        this.setPrefHeight(30.0);
        this.setPrefHeight(30.0);
        this.setMaxHeight(30.0);

        //this.getStylesheets().add("style.css");
    }

    public int getStatus() {
        return status;
    }

    public void setBomb() {
        this.status = -1;
        //this.setText("B"); //todo remove test
    }

    public boolean incrementStatus() {
        if (this.status < 0) {
            return false;
        } else {
            this.status++;
            //this.setText(String.valueOf(this.status)); //todo remove test
            return true;
        }
    }

    public void click() {

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getButtonId() {
        return this.buttonId;
    }
}
