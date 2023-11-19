package controller;


import model.participants.Dealer;
import model.participants.Participant;
import model.participants.Player;
import model.cards.Deck;
import model.rules.Rules;
import observer.ListenableObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the abstract class for the BlackjackController. It contains all
 * methods that are common to both GUI and console versions of the game.
 *
 *
 * @author 22013393
 * @version 1.0
 */
public class BlackjackController extends ListenableObject{

    /**
     * The rules of the game.
     */
    private final Rules rules;

    /**
     * The list of players.
     */
    private final List<Player> players;

    /**
     * The dealer.
     */
    private final Dealer dealer;

    /**
     * The deck.
     */
    private final Deck deck;

    /**
     * The index of the current player.
     */
    private int currentPlayerIndex;

    /**
     * Boolean that indicates if it is the dealer's turn.
     */
    private boolean isDealerTurn;

    /**
     * Boolean that indicates if the game is over.
     */
    private boolean isGameOver;


    /**
     * Constructor for the class.
     *
     * @param rules              The rules of the game.
     * @param dealer             The dealer.
     * @param deck               the deck.
     *
     */
    public BlackjackController(Rules rules, Dealer dealer, Deck deck) {
        super();
        this.rules = rules;
        this.players = new ArrayList<>();
        this.dealer = dealer;
        this.deck = deck;
        this.currentPlayerIndex = -1;
        this.isDealerTurn = false;
    }



    /**
     * Returns the rules of the game.
     *
     * @return The rules of the game.
     */
    public Rules getRules() {
        return this.rules;
    }

    /**
     * Returns the list of players.
     *
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the dealer.
     *
     * @return The dealer.
     */
    public Dealer getDealer() {
        return this.dealer;
    }

    /**
     * Returns the deck.
     *
     * @return The deck.
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Returns the index of the current player.
     *
     * @return The index of the current player.
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Returns the current player.
     *
     * @return The current player
     * @throws IllegalStateException if the current player index is greater than the number of players.
     */
    public Player getCurrentPlayer() {
        if (this.currentPlayerIndex >= this.players.size()) {
            throw new IllegalStateException("Current player index cannot be greater than the number of players");
        }
        return this.players.get(this.currentPlayerIndex);
    }

    /**
     * Returns a boolean that indicates if it is the dealer's turn.
     *
     * @return A boolean that indicates if it is the dealer's turn.
     */
    public boolean isDealerTurn() {
        return this.isDealerTurn;
    }

    /**
     * Returns a boolean that indicates if the game is over.
     *
     * @return A boolean that indicates if the game is over.
     */
    public boolean isGameOver() {
        return this.isGameOver;
    }

    /**
     * Update the value of the boolean that indicates if the game is over.
     *
     * @param isGameOver
     *         The new value of the boolean.
     */
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
        super.notifyListeners();
    }

    /**
     * Sets the index of the current player.
     *
     * @param currentPlayerIndex The index of the current player.
     * @throws IllegalArgumentException if the current player index is less than 0.
     * @throws IllegalArgumentException if the current player index is greater than the number of players.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex < 0) {
            throw new IllegalArgumentException("Current player index cannot be less than 0");
        }
        if (currentPlayerIndex > this.players.size()) {
            throw new IllegalArgumentException("Current player index cannot be greater than the number of players");
        }
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Sets the boolean that indicates if it is the dealer's turn.
     *
     * @param isDealerTurn Boolean that indicates if it is the dealer's turn.
     */
    public void setIsDealerTurn(boolean isDealerTurn) {
        this.isDealerTurn = isDealerTurn;
    }

    /**
     * Next player.
     *
     * @return a boolean that indicates if the next player is the dealer.
     */
    public boolean nextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % (this.players.size() + 1);
        return this.currentPlayerIndex == this.players.size();
    }


    /**
     * Adds a player to the list of players.
     *
     * @param player The player to add.
     * @throws IllegalArgumentException if the player is null.
     */
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.players.add(player);
        if (this.currentPlayerIndex == -1) {
            this.currentPlayerIndex = 0;
        }
    }

    /**
     * Add a player of the given name and initial balance to the list of players.
     *
     * @param name The name of the player.
     * @param balance The initial balance of the player.
     * @throws IllegalArgumentException if the player is null.
     */
    public void addPlayer(String name, int balance) {
        if (name == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.players.add(new Player(name, this.rules.getEvaluator(), balance));
        if (this.currentPlayerIndex == -1) {
            this.currentPlayerIndex = 0;
        }
    }

    /**
     * The current player bets.
     *
     * @param player The player.
     * @param bet    The bet.
     * @return A boolean that indicates if the bet is valid.
     * @throws IllegalArgumentException if the current player not in the list of players.
     * @throws IllegalArgumentException if current player is dealer.
     */
    public boolean placeBet(Player player, int bet) {
        boolean result = false;
        if (!this.players.contains(player)) {
            throw new IllegalArgumentException("Player not in the list of players");
        }
        if (this.isDealerTurn) {
            throw new IllegalArgumentException("Current player cannot be dealer");
        }

        // If the bet is valid, accept it.
        if (this.rules.isValidBet(bet)) {
            this.getCurrentPlayer().placeBet(bet);
            this.nextPlayer();
            result = true;
        }
        return result;
    }

    /**
     * Deal a card to a player.
     *
     * @param player The player.
     * @throws IllegalStateException if the deck is empty.
     */
    public void deal(Player player) {
        if (this.deck.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        player.takeCard(this.deck);
    }

    /**
     * Deal a card to the dealer.
     *
     * @throws IllegalStateException if the deck is empty.
     */
    public void dealToDealer() {
        if (this.deck.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        this.dealer.takeCard(this.deck);
    }

    /**
     * The current player stands.
     *
     * @throws IllegalStateException if the current player is the dealer.
     */
    public void stand() {
        if (this.isDealerTurn) {
            throw new IllegalStateException("Current player cannot be the dealer");
        }
        // The current player stands.
        this.getCurrentPlayer().stand();

        // We move to the next player.
        this.setIsDealerTurn(this.nextPlayer());
    }

    /**
     * The current player hits.
     *
     * @throws IllegalStateException if the current player is the dealer.
     */
    public void hit() {
        if (this.isDealerTurn) {
            throw new IllegalStateException("Current player cannot be the dealer");
        }

        // The current player hits.
        this.getCurrentPlayer().hit(this.deck);

        // We check if the current player has busted.
        if (this.getRules().isBusted(this.getCurrentPlayer())) {
            // We move to the next player.
            this.setIsDealerTurn(this.nextPlayer());
        }
    }

    /**
     * The current player doubles.
     *
     * @throws IllegalStateException if the current player is the dealer.
     */
    public void doubleDown() {
        if (this.isDealerTurn) {
            throw new IllegalStateException("Current player cannot be the dealer");
        }

        // The current player doubles.
        this.getCurrentPlayer().doubleHit(this.deck);

        // We check if the current player has busted.
        if (this.getRules().isBusted(this.getCurrentPlayer())) {
            // We move to the next player.
            this.setIsDealerTurn(this.nextPlayer());
        }
    }

    /**
     * The dealer plays.
     *
     * @throws IllegalStateException if the current player is not the dealer.
     */
    public void dealerPlay() {
        if (!this.isDealerTurn) {
            throw new IllegalStateException("Current player must be the dealer");
        }

        // The dealer plays.
        this.dealer.takeCardsUntil(this.deck, this.getRules().getDealerStandLimit());

        // The game is over.
        this.setGameOver(true);

    }

    /**
     * Starts a new round.
     *
     * @throws IllegalStateException if the deck is empty.
     */
    public void startGame() {
        if (this.deck.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }

        // We shuffle the deck.
        this.deck.shuffle();

        // We deal the cards.
        for (int i = 0; i < this.getRules().getInitialCardsPerPlayer(); i++) {
            for (Player player : this.players) {
                this.deal(player);
            }
        }

        // We deal the cards to the dealer.
        for (int i = 0; i < this.getRules().getNumberOfCardsToDealToDealer(); i++) {
            this.dealToDealer();
        }


        // We set the current player index to 0.
        this.setCurrentPlayerIndex(0);

        // We set the boolean that indicates if it is the dealer's turn to false.
        this.setIsDealerTurn(false);

        // The game is not over.
        this.setGameOver(false);
    }

    /**
     * Get the value of the hand of the participant.
     *
     * @param participant The participant.
     * @return The value of the hand of the participant.
     */
    public int getHandValue(Participant participant) {
        return participant.evaluateHand();
    }

    /**
     * Collect or pay the bets.
     *
     * @param player The player.
     * @throws IllegalStateException if the current player is not in the list of players.
     */
    public void collectOrPayBets(Player player) {
        if (!this.players.contains(player)) {
            throw new IllegalStateException("Current player must be in the list of players");
        }

        this.getRules().collectBet(player, this.dealer);

    }

    /**
     * Check if the player has won.
     *
     * @param player The player.
     * @return A boolean that indicates if the player has won.
     * @throws IllegalStateException if the current player is not in the list of players.
     */
    public boolean hasPlayerWon(Player player) {
        if (!this.players.contains(player)) {
            throw new IllegalStateException("Current player must be in the list of players");
        }

        return this.getRules().getWinner(player, this.dealer) == player;
    }


    /**
     * Clear the hands of the participants.
     */
    public void clearHands() {
        for (Player player : this.players) {
            player.clearHand();
        }
        this.dealer.clearHand();
    }




    public void quitGame() {
        System.exit(0);
    }
}
