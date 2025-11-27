package lab6.game;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lab6.tools.UI;

public class Baby extends Pet {
    private static final Image BABY_IMAGE = UI.loadImage("pets/baby.png");

    public Baby(int sceneSize) {
        super(sceneSize);
    }

    @Override
    protected Image getPetImage() {
        return BABY_IMAGE;
    }

    @Override
    protected double getImageWidth() {
        return 66;
    }

    @Override
    protected double getImageHeight() {
        return 76;
    }

    @Override
    protected double getAnimationLoop() {
        return 3;
    }

    /**
     * Moves the pet to a random spot and makes it do a couple of small hops.
     * Preconditions: sceneSize and UI elements must be initialized.
     * Postconditions: pet slides to a new position and plays hop animations.
     */
    @Override
    protected void move() {

        // Pick a random target
        double targetX = random.nextDouble() * (sceneSize - petView.getFitWidth());
        double targetY = sceneSize - bottomArea +
                random.nextDouble() * (bottomArea - petView.getFitHeight() - 5);

        // Sliding movement
        TranslateTransition move = new TranslateTransition(Duration.seconds(1.5), wrapper);
        move.setToX(targetX);
        move.setToY(targetY);
        move.setInterpolator(Interpolator.EASE_BOTH);

        // Baby hops
        SequentialTransition hops = new SequentialTransition();
        int hopCount = 4;

        for (int i = 0; i < hopCount; i++) {
            TranslateTransition up = new TranslateTransition(Duration.seconds(0.15), petView);
            up.setByY(-5);
            up.setCycleCount(2);
            up.setAutoReverse(true);
            hops.getChildren().add(up);
        }

        new ParallelTransition(move, hops).play();
    }
}
