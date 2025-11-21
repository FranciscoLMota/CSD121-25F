# Reflections

## 2.5. Why can you change the type of the returned value in promptForPlayer without changing the return type in the function signature?

We can change the value from Player to HumanPlayer because the HumanPlayer class is an extention of the Player Class, meaning it IS a Player, so the return is STILL a Player class, but just a subtype of said class.

## 2.9. Explain why the error occurred initially and why adding the abstract method signature fixes the error
The error occurs because the Player class does not have an implementation for the method requested, thus the code does not know how to handle it properly.

Adding the abstract method tells the compiler to check on the subclass of the instance on how to handle the method called.

## 5. Explain in detail (using the terminology we have discussed in class) how it is possible that neither our main method nor our TicTacToeGame class need change at all when adding new Player types to our game. Your discussion must include an explanation of how the single call to pickNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are.

Since the main method requires only the abstract class of Player, there is no need to change the code in the Main function when adding new players to the game, as those players will be subclasses of the superclass Player.

The pickNextMove will work regardless of type because it will call the function on Player but dispatches at runtime to the correct subclass. which overwrite the function to add their own logic, but all receive the same parameters and return the same type of content (The Position information).