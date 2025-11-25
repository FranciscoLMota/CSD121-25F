package lab6.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Pet {

    protected int age = 0;
    protected int hunger = 90;
    protected int happiness = 90;
    protected final Pane wrapper = new Pane();
    protected final ImageView petView = new ImageView();
    protected final Random random = new Random();
    protected final int timeToFlee = 60;
    protected boolean isEvolved = false;
    protected boolean canFlee = true;
    protected int timeUnhappy = 0;

    protected int sceneSize;
    protected final int bottomArea = 250;
    protected final double animationLoop;

    //Image of the pet and their size
    protected abstract Image getPetImage();

    protected abstract double getImageWidth();

    protected abstract double getImageHeight();

    protected abstract double getAnimationLoop();

    protected abstract void move();

    /**
     * Creates a new Pet object, initializes its visuals, position, and animation loop.
     * Preconditions: sceneSize must be a positive integer.
     * Postconditions:
     * - The pet's image view is created and added to the wrapper node.
     * - The pet is positioned at the bottom-center of the scene.
     * - The animation loop timing value is retrieved and stored.
     */
    public Pet(int sceneSize) {
        this.sceneSize = sceneSize;
        this.animationLoop = getAnimationLoop();

        petView.setImage(getPetImage());
        petView.setFitWidth(getImageWidth());
        petView.setFitHeight(getImageHeight());
        petView.setPreserveRatio(true);

        wrapper.getChildren().add(petView);

        double startX = (sceneSize - getImageWidth()) / 2;
        double startY = sceneSize - bottomArea + (bottomArea - getImageHeight()) / 2;

        wrapper.setTranslateX(startX);
        wrapper.setTranslateY(startY);
    }

    /**
     * Returns the Node representing the pet.
     * Postconditions:
     * - Returns a Node containing with pet image.
     */
    public Node getNode() {
        return wrapper;
    }


    public Pet evolution(Pane petLayer) {
        if(!this.isEvolved && this.age > 100) {
            petLayer.getChildren().remove(this.getNode());

            int h = this.getHappiness();
            Pet newPet;
            if (h > 65) {
                newPet = new GoodCarePet(this.sceneSize, this.hunger, this.happiness, this.age);
            } else if (h > 33) {
                newPet = new MidCarePet(this.sceneSize, this.hunger, this.happiness, this.age);
            } else {
                newPet = new BadCarePet(this.sceneSize, this.hunger, this.happiness, this.age);
            }

            petLayer.getChildren().add(newPet.getNode());
            newPet.startAnimation();

            return newPet;
        }

        return this;
    }

    public void setCanFlee(boolean canFlee) {
        this.canFlee = canFlee;
    }

    public boolean canFlee() {
        return canFlee;
    }

    public void setTimeUnhappy(int timeUnhappy) {
        this.timeUnhappy = timeUnhappy;
    }

    public int getTimeUnhappy() {
        return timeUnhappy;
    }

    /**
     * Retrieves the pet's current hunger.
     * Postconditions: Returns an integer representing hunger between 0 and 100.
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Retrieves the pet's current happiness.
     * Postconditions: Returns an integer representing happiness between 0 and 100.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Retrieves the pet's current age.
     * Postconditions: Returns an positive integer representing age (used for evolution).
     */
    public int getAge() {
        return age;
    }

    /**
     * Starts the pet's animation loop, triggering stat updates.
     * Preconditions: JavaFX runtime has to be active.
     * Postconditions:
     * - A Timeline animation begins running indefinitely.
     * - Every loop updates hunger, happiness, movement, and age.
     */
    public void startAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(animationLoop), e -> {
                    updateStats();
                    move();
                    this.age = this.age + 25;
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Feeds the pet using the provided Food object, increasing hunger and happiness.
     * Preconditions: Food must be non-null
     * Postconditions: Hunger and happiness are updated by food attributes.
     */
    public void feed(Food food) {
        changeHunger(food.hungerAmount());
        changeHappiness(food.happinessAmount());
    }

    /**
     * Updates the pet's stats which are reduced by a random amount between 0 and 9.
     * Postconditions: Hunger and happiness decreases by 0–9.
     */
    protected void updateStats() {
        Random random = new Random();
        changeHunger(-random.nextInt(10));      // gets hungry every loop
        changeHappiness(-random.nextInt(10));   // gets less happy every loop
    }

    /**
     * Adjusts the pet's hunger level by the specified amount while making sure it stays between 0 and 100.
     * Preconditions: Amount must be an integer.
     * Postconditions: Hunger is updated but stays between 0 and 100.
     */
    protected void changeHunger(int amount) {
        hunger += amount;
        if (hunger < 0) hunger = 0;
        if (hunger > 100) hunger = 100;
    }

    /**
     * Adjusts the pet's happiness level by the specified amount while making sure it stays between 0 and 100.
     * Preconditions: Amount must be an integer.
     * Postconditions: Happiness is updated but stays between 0 and 100.
     */
    protected void changeHappiness(int amount) {
        happiness += amount;
        if (happiness < 0) happiness = 0;
        if (happiness > 100) happiness = 100;
    }

    public void checkFleePet(Pane root) {
        if((this.getHappiness() < 20 || this.getHunger() < 20 ) && this.canFlee()) {
            if(this.getTimeUnhappy() > timeToFlee) {

                List<Node> toRemove = new ArrayList<>();

                for (Node node : root.getChildren()) {
                    if ("PET".equals(node.getUserData())) {
                        toRemove.add(node);
                    }
                }

                root.getChildren().removeAll(toRemove);

                Label fleeLabel = new Label("Your pet fled due to neglect!");
                fleeLabel.setStyle("-fx-background-color: #353839;-fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: \"Verdana\";  -fx-padding: 10px;");
                fleeLabel.setTranslateX(60);
                fleeLabel.setTranslateY(170);
                root.getChildren().add(fleeLabel);
                this.setCanFlee(false);
            }
            this.setTimeUnhappy(this.getTimeUnhappy() + 1);
            IO.println("Time unhappy: " + this.getTimeUnhappy());
        } else {
            this.setTimeUnhappy(0);
        }
    }


}
