package lab6;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public boolean canEvolve = true;


    @Override
    public void start(Stage stage) {
        // Root pane
        Pane root = new Pane();

        // Pet setup
        Pet mainPet = new Baby(screenSize);
        PetUI petUI = new PetUI();

        //Pet Info
        Pane petLayer = new Pane();
        petLayer.getChildren().add(mainPet.getNode());
        Pane hudLayer = new Pane();
        hudLayer.getChildren().add(petUI.getNode());

        // Interactive UI
        Pane bgButtonsLayer = new Pane();
        UI ui = new UI(screenSize, screenSize, null);
        bgButtonsLayer.getChildren().add(ui.getButtons());

        Pane foodLayer = new Pane();
        FoodUI foodUI = new FoodUI(mainPet);
        foodLayer.getChildren().add(foodUI.getNode());

        // Evolve content
        Pane evolveLayer = new Pane();
        Button evolveButton = new Button("Evolve");
        evolveButton.setOnAction(e -> evolvePet(petLayer, mainPet, evolveButton));
        evolveButton.setDisable(true);

        evolveButton.setTranslateX(320);
        evolveButton.setTranslateY(350);
        evolveLayer.getChildren().add(evolveButton);

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
                evolveLayer,
                bgButtonsLayer,
                foodLayer
        );

        // Update UI
        Timeline uiUpdater = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e ->
                {
                    checkEvolution(mainPet, evolveButton);
                    petUI.update(mainPet.getHunger(), mainPet.getHappiness(), mainPet.getAge());
                }));
        uiUpdater.setCycleCount(Timeline.INDEFINITE);
        uiUpdater.play();

        // Start pet animation
        mainPet.startAnimation();

        // Scene setup
        Scene scene = new Scene(root, screenSize, screenSize);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Pet Sim v0.1");
        stage.show();
    }

    private void checkEvolution(Pet mainPet, Button evolveButton) {
        if (mainPet.getAge() > 100 && canEvolve) {
            evolveButton.setDisable(false);
        }
    }

    private void evolvePet(Pane petLayer, Pet mainPet, Button evolveButton) {

        petLayer.getChildren().remove(mainPet.getNode());

        int h = mainPet.getHappiness();

        if (h > 65) {
            mainPet = new GoodCarePet(this.screenSize);
        } else if (h > 33) {
            mainPet = new MidCarePet(this.screenSize);
        } else {
            mainPet = new BadCarePet(this.screenSize);
        }

        petLayer.getChildren().add(mainPet.getNode());
        mainPet.startAnimation();
        canEvolve = false;

        evolveButton.setDisable(true);
    }

    static void main() {
        launch();
    }
}
