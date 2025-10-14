package main.java.lab3.game;

/**
 * Represents the different states a spot in the board can be
 * PX represents a space where the player 'X' has a mark
 * PO represents a space where the player 'O' has a mark
 * Empty represents a space with no marks
 */
public enum Play {
    EMPTY, PX, PO;

    @Override
    public String toString() {
        return switch (this) {
            case EMPTY -> "-";
            case PX -> "X";
            case PO -> "O";
        };
    }
}
