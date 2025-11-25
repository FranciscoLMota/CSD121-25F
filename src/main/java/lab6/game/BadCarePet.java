package lab6.game;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lab6.tools.UI;

public class BadCarePet extends Pet {


    private static final Image BAD_CARE_IMAGE = UI.loadImage("pets/Batchi.png");

    public BadCarePet(int screenSize) {
        super(screenSize);
        this.isEvolved = true;
    }

    public BadCarePet(int sceneSize, int hunger, int happiness, int age) {
        super(sceneSize);
        this.isEvolved = true;
        this.hunger = hunger;
        this.happiness = happiness;
        this.age = age;
    }

    @Override
    protected Image getPetImage() {
        return BAD_CARE_IMAGE;
    }

    @Override
    protected double getImageWidth() {
        return 128;
    }

    @Override
    protected double getImageHeight() {
        return 88;
    }

    @Override
    protected double getAnimationLoop() {
        return 5;
    }

    /**
     * Moves the pet to a random spot and makes it do a couple of small hops.
     * Preconditions: sceneSize and UI elements must be initialized.
     * Postconditions: pet slides to a new position and plays hop animations.
     */
    @Override
    protected void move() {

        TranslateTransition idleBob = null;
        if (idleBob == null) {
            idleBob = new TranslateTransition(Duration.seconds(3), petView);
            idleBob.setByY(-25);
            idleBob.setCycleCount(Animation.INDEFINITE);
            idleBob.setAutoReverse(true);
            idleBob.play();
        }

        double targetX = random.nextDouble() * (sceneSize - petView.getFitWidth());
        double targetY = sceneSize - bottomArea +
                random.nextDouble() * (bottomArea - petView.getFitHeight() - 10);

        TranslateTransition move = new TranslateTransition(Duration.seconds(1.5), wrapper);
        move.setToX(targetX);
        move.setToY(targetY);
        move.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition strongBob = new TranslateTransition(Duration.seconds(0.7), petView);
        strongBob.setByY(-22);             // bigger up/down
        strongBob.setCycleCount(2);        // lasts through movement
        strongBob.setAutoReverse(true);

        ParallelTransition flyingMove = new ParallelTransition(move, strongBob);
        flyingMove.play();
    }
}
