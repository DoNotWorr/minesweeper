package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Minesweeper");

        //Number of buttons on X- and Y-axis
        int length = 10;
        int heigth = 10;
        int bombQuantity = 20;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        //Draws detailed buttons on the board and stores buttons in
        controller.drawboard(length, heigth, bombQuantity);

        Scene scene = new Scene(root, 300, 275);
        //CSS to style buttons etc
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * todo testmetod
     * @param length
     * @param heigth
     */
    public static void testXYcoordinates(int length, int heigth) {
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < length; x++) {
                System.out.printf("%3d", (x * (length + 1 ) + y));
            }
            System.out.println();
        }


        System.out.println();

        for (int i = 0; i < length * heigth; i++) {
            int x;
            int y = 0;
            x = i / heigth;
            y = i % heigth;


            System.out.println("Värde: " + i + " (" + x + "," + y + ")");
        }
    }
}
