import AdventureModel.AdventureGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import views.AccessibilityView;
import views.AdventureGameView;
import views.SaveView;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;

/**
 * Class AdventureGameApp.
 */
public class AdventureGameApp extends  Application {

    AdventureGame model;
    AdventureGameView view;
    //AccessibilityView accessibilityView;
    private BasicOptionPaneUI.ButtonAreaLayout gridPane;

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * JavaFX is a Framework, and to use it we will have to
     * respect its control flow!  To start the game, we need
     * to call "launch" which will in turn call "start" ...
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //change the name of the game if you want to try something bigger!
        //VBox root = new VBox(5);
        //root.setPadding(new Insets(10));
        //root.setAlignment(Pos.CENTER);



         //Create a ToggleGroup to hold the list of ToggleButtons. This will allow us to allow the selection of only one
        // ToggleButton at a time
        Button accessible = new Button("Accessible Mode");
        accessible.setAccessibleRole(AccessibleRole.BUTTON);
        accessible.setAccessibleRoleDescription("Accessible Mode");
        accessible.setAccessibleText("Play in the accessible mode");
        accessible.setAccessibleHelp("If you are visually impaired person, please choose this option");
        accessible.setFocusTraversable(true);
        accessible.setPrefSize(100, 50);
        accessible.setFont(new Font("Arial", 25));
        accessible.setStyle("-fx-background-color: #17871b; -fx-text-fill: yellow;");
        accessible.setOnAction(e -> {
            this.model = new AdventureGame("TinyGame");
            this.view = new AdventureGameView(model, primaryStage);
        });

        Button simple = new Button("Simple Mode");
        simple.setAccessibleRole(AccessibleRole.BUTTON);
        simple.setAccessibleRoleDescription("Simple Mode");
        simple.setAccessibleText("Play the game");
        simple.setAccessibleHelp("If you aren't visually impaired person, please choose this option");
        simple.setFocusTraversable(true);
        simple.setPrefSize(100, 50);
        simple.setFont(new Font("Arial", 25));
        simple.setStyle("-fx-background-color: #17871b; -fx-text-fill: yellow;");
        simple.setOnAction(e -> {
            this.model = new AdventureGame("TinyGame");
            this.view = new AdventureGameView(model, primaryStage);
        });
    }
}
