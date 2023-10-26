package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 
 * @author Tabitha
 */

public class ViewCase 
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a Case instance with number 1, an example Item, and button text "Case 1"
        Case aCase = new Case(1, new Item(/* construct your Item here */), "Case 1");

        // Create a JavaFX button with the text from the Case
        Button button = new Button(aCase.toString());

        // Add event handling for button click
        button.setOnAction(event -> {
            aCase.openCase();
            System.out.println("Case opened: " + aCase.displayCase());
        });

        // Set up the JavaFX scene and stage
        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setTitle("Case GUI");
        primaryStage.show();
    }
}
