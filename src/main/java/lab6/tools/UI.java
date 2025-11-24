package lab6.tools;

import javafx.scene.image.Image;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class UI {
    private int currentBG = 1;

    private final ImageView background;
    private final Rectangle blackOverlay;
    private final HBox box = new HBox(5); // spacing = 5

    /**
     * Sets up the UI with a background image, overlay, and buttons to switch backgrounds.
     * Preconditions: width/height should be valid.
     * Postconditions: UI elements are created and positioned.
     */
    public UI(double width, double height) {

        // Background
        background = new ImageView(loadImage("bgs/bg1.png"));
        background.setFitWidth(width);
        background.setFitHeight(height);
        background.setPreserveRatio(false);

        // Overlay
        blackOverlay = new Rectangle(width, height, Color.BLACK);
        blackOverlay.setOpacity(0);

        // Buttons
        Button left = new Button("<");
        Button right = new Button(">");

        left.setOnAction(e -> switchBackground(-1));
        right.setOnAction(e -> switchBackground(1));

        // Add buttons to HBox
        box.getChildren().addAll(left, right);

        // Position top-left
        box.setTranslateX(160);
        box.setTranslateY(350);
    }

    /**
     * Changes the current background based on the given direction.
     * Preconditions: direction is usually -1 or 1.
     * Postconditions: background index updates and fade animation starts.
     */
    private void switchBackground(int direction) {
        currentBG += direction;

        int BG_COUNT = 3;
        if (currentBG > BG_COUNT) currentBG = 1;
        if (currentBG < 1) currentBG = BG_COUNT;

        fadeToBlack("bgs/bg" + currentBG + ".png");
    }

    /**
     * Fades the screen to black, swaps the image, then fades back in.
     * Preconditions: nextImage must be a valid file path.
     * Postconditions: background image changes with a fade effect.
     */
    private void fadeToBlack(String nextImage) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.8), blackOverlay);
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);

        fadeOut.setOnFinished(e -> {
            background.setImage(loadImage(nextImage));

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8), blackOverlay);
            fadeIn.setFromValue(1);
            fadeIn.setToValue(0);
            fadeIn.play();
        });

        fadeOut.play();
    }

    /**
     * Returns the background ImageView.
     */
    public ImageView getBackground() { return background; }

    /**
     * Returns the black overlay.
     */
    public Rectangle getOverlay() { return blackOverlay; }

    /**
     * Returns the buttons.
     */
    public Node getButtons() { return box; }

    /**
     * Loads an image from the 'resources/lab6/images/...' folder.
     * Preconditions: file must exist.
     * Postconditions: returns the loaded Image.
     */
    public static Image loadImage(String fileName) {
        return new javafx.scene.image.Image(UI.class.getResource("/lab6/images/" + fileName).toString());
    }
}
