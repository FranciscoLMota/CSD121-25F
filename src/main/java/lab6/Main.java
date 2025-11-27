package lab6;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.util.Duration;
import lab6.game.*;
import lab6.tools.FoodUI;
import lab6.tools.PetUI;
import lab6.tools.UI;


public class Main extends Application {

    public final int screenSize = 384;

    @Override
    public void start(Stage stage) {
        // Root pane
        Pane root = new Pane();

        // Pet setup
        final Pet[] mainPet = {new Baby(screenSize)};
        PetUI petUI = new PetUI();
        Pane petLayer = new Pane();
        petLayer.setUserData("PET");
        petLayer.getChildren().add(mainPet[0].getNode());

        //Pet Info
        Pane hudLayer = new Pane();
        hudLayer.setUserData("PET");
        hudLayer.getChildren().add(petUI.getNode());

        // Interactive UI
        UI ui = new UI(screenSize, screenSize);
        Pane bgButtonsLayer = new Pane();
        bgButtonsLayer.getChildren().add(ui.getButtons());
        Pane foodLayer = new Pane();
        foodLayer.setUserData("PET");
        FoodUI foodUI = new FoodUI(mainPet[0]);
        foodLayer.getChildren().add(foodUI.getNode());

        // Background layer
        Pane backgroundLayer = new Pane();
        backgroundLayer.getChildren().add(ui.getBackground());

        Pane overlayLayer = new Pane();
        overlayLayer.getChildren().add(ui.getOverlay());
        overlayLayer.setMouseTransparent(true);

        // Add layers to root in order (background -> foreground)
        root.getChildren().addAll(
                backgroundLayer,
                overlayLayer,
                petLayer,
                hudLayer,
                bgButtonsLayer,
                foodLayer
        );

        // Update UI
        Timeline uiUpdater = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e ->
                {
                    //Updates PetUI - Since I supply the pet here,
                    petUI.update(mainPet[0]);

                    //Check if the pet will flee
                    mainPet[0].checkFleePet(root);

                    //Check if the pet is ready to evolve
                    Pet evolved = mainPet[0].evolution(petLayer);

                    //If it evolved, update the pet and update the references in the Food UI
                    if (evolved != mainPet[0]) {
                        mainPet[0] = evolved;
                        foodUI.setPet(evolved);
                    }
                }));

        uiUpdater.setCycleCount(Timeline.INDEFINITE);
        uiUpdater.play();

        // Start pet animation
        mainPet[0].startAnimation();

        // Scene setup
        Scene scene = new Scene(root, screenSize, screenSize);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Pet Sim v0.1");
        stage.show();
    }

    static void main() {
        launch();
    }
}
