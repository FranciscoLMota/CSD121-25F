package lab6.tools;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lab6.game.Food;
import lab6.game.Pet;

public class FoodUI {

    private final VBox box = new VBox(5); // spacing = 5

    /**
     * Constructs the FoodUI panel, creating food objects, generating
     * their corresponding buttons, and positioning them inside a VBox.
     * Preconditions:
     * - pet must be a valid Pet instance.
     * Postconditions:
     * - Creates buttons for each option of food available
     * - Positions the UI in the left bottom of screen.
     */
    public FoodUI(Pet pet) {
        // Create foods
        Food burger = new Food("Burger", 10, 5, UI.loadImage("food/Burger.png"));
        Food treat = new Food("Treat", 0, 15, UI.loadImage("food/Treat.png"));
        Food salad = new Food("Salad", 20, -10, UI.loadImage("food/Salad.png"));

        // Create buttons
        Button burgerBtn = createFoodButton(burger, pet);
        Button treatBtn = createFoodButton(treat, pet);
        Button saladBtn = createFoodButton(salad, pet);

        // Add buttons to VBox
        box.getChildren().addAll(burgerBtn, treatBtn, saladBtn);

        // Position
        box.setTranslateX(10);
        box.setTranslateY(265);
    }

    /**
     * Returns the node representing the foodUI.
     * Postconditions:
     * - Returns a node containing the options.
     */
    public VBox getNode() {
        return box;
    }

    /**
     * Creates a button that displays the food and feeds the pet when clicked.
     * Preconditions:
     * - food and pet must be non-null and food.image() must be an Image object.
     * Postconditions:
     * - A Button is created that sends pet.feed(food)
     */
    private Button createFoodButton(Food food, Pet pet) {
        ImageView view = new ImageView(food.image());
        view.setFitWidth(25);
        view.setFitHeight(25);

        Button btn = new Button();
        btn.setGraphic(view);
        btn.setOnAction(e -> pet.feed(food));

        return btn;
    }
}
