package view;

import controller.BlackjackController;
import model.participants.Dealer;
import model.participants.Player;
import model.rules.Rules;
import observer.Listenable;
import observer.Listener;

import java.util.List;

public interface Blackjack extends Listener {




    /**
     * Gets the controller of the game.
     *
     * @return the controller of the game.
     */
    BlackjackController getController();


    /**
     * Returns the list of players.
     *
     * @return the list of players.
     */
    default List<Player> getPlayers() {
        return this.getController().getPlayers();
    }


    /**
     * Returns the dealer.
     *
     * @return the dealer.
     */
    default Dealer getDealer() {
        return this.getController().getDealer();
    }

    /**
     * Returns the rules of the game.
     *
     * @return the rules of the game.
     */
    default Rules getRules() {
        return this.getController().getRules();
    }

    /**
     * Initialize the view.
     */
    void initialize();

    /**
     * Ask the number of players.
     *
     * @return The number of players.
     */
    int askNumberOfPlayers();

    /**
     * Ask the player to enter his name and his money.
     *
     * @param i
     *        The number of the player.
     * @return The player.
     */
    Player askPlayer(int i);

    /**
     * Ask a player to enter his bet.
     *
     * @param player
     *       The player.
     * @return The bet.
     */
    public abstract int askBet(Player player);

    /**
     * Ask all the players to enter their bet.
     */
    default void askBets() {

        this.getPlayers().removeIf(player ->
                {
                    int nbTentative = 0;
                    int bet = this.askBet(player);
                    while (!this.getController().placeBet(player, bet)) {
                        if (nbTentative == 3) {
                            System.out.println("You have exceeded the number of attempts.");
                            return true;
                        }
                        System.out.println("The bet is not valid.");
                        bet = this.askBet(player);
                        nbTentative++;
                    }
                    return false;

                }
                );
    }


    /**
     * Ask the player to play again.
     *
     * @param player
     *       The player.
     * @return True if the player wants to play again.
     */
    boolean askPlayAgain(Player player);

    /**
     * Show that we have no players.
     */
    void showNoPlayers();

    /**
     * Show the round
     */
    void showRound();

    /**
     * Display the result of the round.
     */
    void showResult();

    /**
     * Display a message that the bet of the player will bet "bet".
     * @param player The player.
     * @param bet The bet.
     */
    void displayBet(Player player, int bet);

    /**
     * Display that the player could not bet the amount and that it will be removed from the game.
     * @param player The player.
     * @param bet The bet.
     */
    void displayInvalidBet(Player player, int bet);

    /**
     * Display that we have no more players and that the game will end.
     *
     */
    void displayNoPlayerLeft();

    /**
     * Show the rules of the game.
     *
     */
    void showRules();

    /**
     * End the game.
     */
    default void endGame(){
        // Collect or pay the bets.
        this.getPlayers().forEach(player -> this.getController().collectOrPayBets(player));

        // Show the result.
        this.showResult();

        // Ask if the player wants to play again.
        this.getPlayers().removeIf(player -> !this.askPlayAgain(player));

        // If there is no more players, end the game.
        if (this.getPlayers().isEmpty()) {
            this.displayNoPlayerLeft();
            this.showNoPlayers();
            this.quitGame();
        } else {
            // Start a new round.
            this.startGame();
        }
    }

    /**
     * Quit the game.
     */
    default void quitGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    /**
     * Start the game.
     */
    default void startGame(){
        this.askBets();
        this.getController().startGame();
        // Show the round.
        this.showRound();
    }

    /**
     * Launch the game.
     *
     */
    default void launch() {

        int numberOfPlayers = this.askNumberOfPlayers();

        // Create the players and add them to the game.
        for (int i = 0; i < numberOfPlayers; i++) {
            this.getController().addPlayer(this.askPlayer(i));
        }

        // If we have no players, we quit the game.
        if (this.getPlayers().isEmpty()) {
            this.showNoPlayers();
            this.quitGame();
        }

        this.initialize();

        // Show the rules of the game.
        this.showRules();

        this.startGame();

    }

    @Override
    default void notify(Listenable listenable) {
        if (getController().isGameOver()) {
            this.endGame();
        }
    }
}
