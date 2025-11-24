package lab6.tools;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PetUI {

    private final VBox uiBox = new VBox(5);
    private final ProgressBar hungerBar = new ProgressBar(1);
    private final ProgressBar ageBar = new ProgressBar(1);
    private final ProgressBar happinessBar = new ProgressBar(1);

    /**
     * Builds the labels and progress bars for pet stats.
     * Preconditions UI components (hungerBar, happinessBar, ageBar, uiBox) must be initialized.
     * Postconditions: uiBox is positioned at top left.
     */
    public PetUI() {
        Label hungerLabel = new Label("Hunger:     ");
        Label happinessLabel = new Label("Happiness:");
        Label ageLabel = new Label("Age:          ");

        hungerLabel.setStyle("-fx-background-color: #FFFFFF;");
        happinessLabel.setStyle("-fx-background-color: #FFFFFF;");
        ageLabel.setStyle("-fx-background-color: #FFFFFF;");

        hungerBar.setPrefWidth(150);
        happinessBar.setPrefWidth(150);
        ageBar.setPrefWidth(150);

        // Create Hbox so that the labels are next to the bar
        HBox hungerBox = new HBox(5, hungerLabel, hungerBar);
        HBox happinessBox = new HBox(5, happinessLabel, happinessBar);
        HBox ageBox = new HBox(5, ageLabel, ageBar);

        //Adds them all to a Vbox so that they are stacked on top of each other
        uiBox.getChildren().addAll(hungerBox, happinessBox, ageBox);

        uiBox.setTranslateX(10);
        uiBox.setTranslateY(10);
    }

    /**
     * Returns the node representing the petUI.
     * Postconditions:
     * - Returns a node containing the options.
     */
    public Node getNode() {
        return uiBox;
    }

    /**
     * Updates the bars.
     */
    public void update(int hunger, int happiness, int age) {
        hungerBar.setProgress(hunger / 100.0);
        happinessBar.setProgress(happiness / 100.0);
        ageBar.setProgress(age / 100.0);
    }
}