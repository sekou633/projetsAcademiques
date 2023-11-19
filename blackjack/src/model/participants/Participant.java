package model.participants;

import model.BlackjackCardEvaluator;
import model.Constants;
import model.cards.Deck;
import model.cards.Hand;
import observer.ListenableObject;

/**
 * <b>
 *     A participant is a person or a computer that impact the process of a game.
 * </b>
 *
 * <p>
 *     Globally, we can distinguish two types of participants: players and dealers.
 *     A player is a person who plays a game. A player can be a human or a computer.
 *     A dealer is a person who deals the cards of a game. Currently, the dealer is always a computer.
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public abstract class Participant extends ListenableObject {


    /**
     * The name of the participant.
     */
    private final String name;

    /**
     * The hand of cards of the participant.
     */
    private final Hand hand;

    /**
     * The evaluator of the participant.
     */
    private final BlackjackCardEvaluator evaluator;

    /**
     * The money of the participant.
     */
    private int money;

    /**
     * Boolean that indicates if the participant can play.
     */
    private boolean canPlay;

    /**
     * Creates a new participant with the given name.
     *
     * @param name
     * 		The name of the participant.
     * @param hand
     *         The hand of cards of the participant.
     * @param evaluator
     *        The evaluator of the participant.
     * @param money
     *        The money of the participant.
     * @param canPlay
     *       Boolean that indicates if the participant can play.
     *
     * @throws NullPointerException if the name or the hand is null.
     * @throws NullPointerException if the
     */
    protected Participant(String name, Hand hand, BlackjackCardEvaluator evaluator, int money, boolean canPlay) {
        super();
        if (name == null) {
            throw new NullPointerException("The name of the participant cannot be null.");
        }

        if (hand == null) {
            throw new NullPointerException("The hand of the participant cannot be null.");
        }

        if (evaluator == null) {
            throw new NullPointerException("The evaluator of the participant cannot be null.");
        }

        this.name = name;
        this.hand = hand;
        this.money = money;
        this.canPlay = canPlay;
        this.evaluator = evaluator;
    }

    /**
     * Creates a new participant with the given name.
     *
     * @param name
     *         The name of the participant.
     * @param evaluator
     *       The evaluator of the participant.
     * @param money
     *       The money of the participant.
     */
    protected Participant(String name, BlackjackCardEvaluator evaluator, int money) {
        this(name, new Hand(), evaluator, money, true);
    }


    /**
     * Returns the name of the participant.
     *
     * @return the name of the participant.
     */
    public String getName(){
        return this.name;
    }


    /**
     * Returns the hand of cards of the participant.
     *
     * @return the hand of cards of the participant.
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Returns the money of the participant.
     *
     * @return the money of the participant.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Sets the money of the participant.
     *
     * @param money
     *       The money of the participant.
     *
     * @throws IllegalArgumentException if the money is negative.
     */
    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("The money of the participant cannot be negative.");
        }
        this.money = money;
        super.notifyListeners();
    }

    /**
     * Increases the money of the participant.
     *
     * @param money
     *      The money to increase.
     */
    public void increaseMoney(int money){
        this.setMoney(this.getMoney() + money);
    }

    /**
     * Decreases the money of the participant.
     *
     * @param money
     *      The money to decrease.
     */
    public void decreaseMoney(int money) {
        this.setMoney(this.getMoney() - money);
    }



    /**
     * Returns true if the participant can play, false otherwise.
     *
     * @return true if the participant can play, false otherwise.
     */
    public boolean canPlay() {
        return this.canPlay;
    }

    /**
     * Sets the canPlay attribute of the participant.
     *
     * @param canPlay
     *         The canPlay attribute of the participant.
     */
    void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
        super.notifyListeners();
    }



    /**
     * Clears the hand of the participant.
     *
     */
    public void clearHand() {
        this.getHand().clear();
        super.notifyListeners();
    }

    /**
     * Resets the participant.
     */
    public void reset() {
        this.getHand().clear();
        this.setCanPlay(true);
    }

    /**
     * Evaluates the player's hand.
     *
     * @return the value of the hand
     * @throws NullPointerException if the card evaluator is null.
     */
    public int evaluateHand() {

        return this.getHand().evaluate(this.evaluator);
    }

    /**
     * Takes the top card of the deck and adds it to the hand of the participant.
     *
     * @param deck
     *       The deck of cards.
     */
    public void takeCard(Deck deck) {
        this.getHand().addCard(deck.deal());
        super.notifyListeners();
    }


    /**
     * Returns true if the participant is a player, false otherwise.
     */
    public abstract boolean isPlayer();

    /**
     * Returns true if the participant is busted, false otherwise.
     *
     * @return true if the participant is busted, false otherwise.
     */
    public boolean isBusted() {
        return this.evaluateHand() > Constants.BUSTED_VALUE;
    }







}
