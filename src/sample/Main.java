package sample;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Minesweeper");

        //Number of buttons on X- and Y-axis
        byte length = 3;
        byte heigth = 3;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        //Draws buttons on the board
        controller.drawboard(length, heigth);

        primaryStage.setScene(new Scene(root, 300, 275));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
