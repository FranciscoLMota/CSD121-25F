import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.time.*;
import java.nio.file.*;

public class Lab1 {

    void main() {
        //Gets the quantity of cards to be drawn
        int quantity = getQuantity();

        //Loads the full deck
        List<Map<String, String>> deck = getDeck();

        //Loops through the quantity to print in the console and save the card to a file.
        for (int i = 1; i < quantity + 1; i++) {

            //Prints the Card number
            IO.println("====== CARD #" + i + " ======");

            //Generates the number of the card and its position
            int randomNumber = cardSelector();
            int meaning = meaningSelector();

            //Get the card selected
            var card = deck.get(randomNumber);

            //Deals with printing the card
            printCard(card, meaning);

            //Deals with recording the card to a file
            recordReadings(card, meaning);

        }

    }

    /**
     * Prompts the user for how many cards they would like to draw.
     * If user replies anything besides an integer, prompts the user again.
     *
     * @return Number of cards to be drawn
     */
    int getQuantity() {
        String response = IO.readln("How many cards you want to draw? (Must be an integer): ");

        // If it fails to get an integer, retries the request.
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            IO.println("Input must be an integer!");
            return getQuantity();
        }

    }

    /**
     * Selects a number between 0 and 78 representing a card in a tarot deck.
     * The cards are organized in Major arcana, Suits of Wands, Cups, Swords and Pentacles
     *
     * @return Number of the card selected
     */
    int cardSelector() {
        // Gets a random integer between 0 and 78
        Random random = new Random();
        return random.nextInt(78);
    }

    /**
     * Select which way the card will be facing: 0 - Upright, 1 - Reversed
     *
     * @return Integer between 0 and 1
     */
    int meaningSelector() {
        // Gets a random integer between 0 and 1
        Random random = new Random();
        return random.nextInt(2);
    }

    /**
     * Gets the list from the file and converts it to be used by Java
     *
     * @return List of all the cards and its properties
     */
    List<Map<String, String>> getDeck() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            // Load deck.json from resources
            InputStream inputStream = Lab1.class.getResourceAsStream("/deck.json");

            // Creates a Map with each card to return as a List of Maps (String => String)
            return mapper.readValue(
                    inputStream,
                    new TypeReference<>() {
                    }
            );
        } catch (Exception e) {
            IO.println("An error occurred getting the deck: " + e.getMessage());
        }

        return null;
    }

    /**
     * Prints the selected card information on the console.
     *
     * @param card     Card selected
     * @param position Select the position of the card, 0 => Upright; 1 => Reversed
     */
    void printCard(Map<String, String> card, int position) {
        try {
            IO.print("Card: " + card.get("name"));
            if (position == 1) {
                IO.print(" (Reversed)");
            }
            IO.print("\n");
            IO.println("Suit: " + card.get("suit"));
            IO.println("Description: " + card.get("description"));

            //If the position is 0 - Upright, prints the regular Interpretation
            if (position == 0) {
                IO.println("Interpretation: " + card.get("interpretation"));

            } else {  //If the position is 1 - Reversed, prints the reverse interpretation of the card
                IO.println("Interpretation: " + card.get("reversed"));
            }
            IO.println();
        } catch (RuntimeException e) {
            IO.println("An error occurred printing the card: " + e.getMessage());
        }

    }

    /**
     * Writes down the recordings to a file.
     *
     * @param card     Card selected
     * @param position Select the position of the card, 0 => Upright; 1 => Reversed
     */
    void recordReadings(Map<String, String> card, int position) {
        //
        String filePath = "readings.txt";
        File file = new File(filePath);
        var createdFile = Paths.get(filePath);

        //Tries to create a file just in  case the file does not exist.
        try {
            var newFile = file.createNewFile();
        } catch (IOException e) {
            IO.println("An error occurred while creating the file: " + e.getMessage());
        }

        //Gets all the previous content from the file into the lines variable and adds more information on the new cards
        try {
            List<String> lines = Files.readAllLines(createdFile);
            lines.add("===========");
            lines.add("Time of reading: " + LocalDateTime.now());

            String name = "Card: " + card.get("name");
            if (position == 1) {
                name += " (Reversed)";
            }
            lines.add(name);

            lines.add("Suit: " + card.get("suit"));
            lines.add("Description: " + card.get("description"));

            //If the position is 0 - Upright, prints the regular Interpretation
            if (position == 0) {
                lines.add("Interpretation: " + card.get("interpretation"));
            } else { //If the position is 1 - Reversed, prints the reverse interpretation of the card
                lines.add("Interpretation: " + card.get("reversed"));
            }
            lines.add("");
            Files.write(createdFile, lines, StandardOpenOption.WRITE);

        } catch (Exception e) {
            IO.println("An error occurred while writing to file: " + e.getMessage());
        }


    }
}