package lab6.game;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lab6.tools.UI;

public class MidCarePet extends Pet {


    private static final Image MID_CARE_IMAGE = UI.loadImage("pets/Tigaotchi.png");

    public MidCarePet(int sceneSize) {
        super(sceneSize);
    }

    @Override
    protected Image getPetImage() {
        return MID_CARE_IMAGE;
    }

    @Override
    protected double getImageWidth() {
        return 126;
    }

    @Override
    protected double getImageHeight() {
        return 112;
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
                random.nextDouble() * (bottomArea - petView.getFitHeight() - 10);

        // Sliding movement
        TranslateTransition move = new TranslateTransition(Duration.seconds(1.5), wrapper);
        move.setToX(targetX);
        move.setToY(targetY);
        move.setInterpolator(Interpolator.EASE_BOTH);

        //hops
        SequentialTransition hops = new SequentialTransition();
        int hopCount = 3;

        for (int i = 0; i < hopCount; i++) {
            TranslateTransition up = new TranslateTransition(Duration.seconds(0.20), petView);
            up.setByY(-10);
            up.setCycleCount(2);
            up.setAutoReverse(true);
            hops.getChildren().add(up);
        }

        new ParallelTransition(move, hops).play();
    }
}
