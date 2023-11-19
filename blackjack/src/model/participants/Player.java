package model.participants;

import model.BlackjackCardEvaluator;
import model.cards.Deck;
import model.cards.Hand;

/**
 * <b>
 *     Player is a participant of the game that can bet.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class Player extends Participant {

    /**
     * The bet of the player.
     */
    private int bet;

    /**
     * Creates a new player
     *
     * @param name
     * 		The name of the player.
     * @param evaluator
     *       The evaluator of the player.
     * @param money
     *         The money of the player.
     * @param bet
     *       The bet of the player.
     * @param hand
     *       The hand of the player.
     * @param canPlay
     *      Boolean that indicates if the player can play.
     */
    public Player(String name, BlackjackCardEvaluator evaluator, int money, int bet, Hand hand, boolean canPlay) {
        super(name, hand, evaluator, money, canPlay);
        this.bet = bet;
    }

    /**
     * Creates a new player with the given name and money.
     *
     * @param name
     *         The name of the player.
     * @param evaluator
     *       The evaluator of the player.
     * @param money
     *        The money of the player.
     */
    public Player(String name, BlackjackCardEvaluator evaluator, int money) {
        this(name, evaluator, money, 0, new Hand(), true);
    }

    /**
     * Returns the bet of the player.
     *
     * @return The bet of the player.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the bet of the player.
     *
     * @param bet
     *        The bet of the player.
     * @throws IllegalArgumentException if the bet is negative.
     * @throws IllegalArgumentException if the bet is greater than the money.
     */
    public void setBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        if (bet > getMoney()) {
            throw new IllegalArgumentException("The bet cannot be greater than the money.");
        }
        this.bet = bet;
        super.notifyListeners();
    }

    /**
     * Increases the bet of the player.
     *
     * @param bet
     *       The bet to increase.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void increaseBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        setBet(getBet() + bet);
    }

    /**
     * Decreases the bet of the player.
     *
     * @param bet
     *       The bet to decrease.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void decreaseBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        setBet(getBet() - bet);
    }

    /**
     * Doubles the bet of the player.
     *
     */
    public void doubleBet() {
        setBet(getBet() * 2);
    }

    /**
     * Returns a string representation of the player.
     *
     * @return A string representation of the player.
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + getName() + ", money=" + getMoney() + ", bet=" + bet + ", hand=" + getHand() + ", canPlay=" + canPlay() + '}';
    }

    @Override
    public boolean isPlayer() {
        return true;
    }



    /**
     * Hit a card to the player from the given deck.
     *
     * @param deck
     *       The deck to hit the card from.
     * @throws NullPointerException if the deck is null.
     */
    public void hit(Deck deck) {
        if (deck == null) {
            throw new NullPointerException("The deck cannot be null.");
        }
        this.takeCard(deck);
    }

    /**
     * Double the bet of the player and hit a card to the player from the given deck.
     *
     * @param deck
     *      The deck to hit the card from.
     * @throws NullPointerException if the deck is null.
     */
    public void doubleHit(Deck deck) {
        if (deck == null) {
            throw new NullPointerException("The deck cannot be null.");
        }
        this.doubleBet();
        this.hit(deck);
        this.stand();
    }

    /**
     * Stand the player.
     *
     */
    public void stand() {
        setCanPlay(false);
    }

    /**
     * The player bets the given amount of money.
     *
     * @param bet
     *       The amount of money to bet.
     * @throws IllegalArgumentException if the bet is negative.
     */
    public void placeBet(int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("The bet cannot be negative.");
        }
        // Set the bet
        setBet(bet);
        // Decrease the money
        this.decreaseMoney(bet);
    }

    /**
     * Resets the player.
     *
     */
    @Override
    public void reset() {
        super.reset();
        this.setBet(0);
    }



}
