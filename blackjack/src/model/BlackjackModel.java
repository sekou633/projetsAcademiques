package model;

import model.cards.Deck;
import model.cards.FactoryDeck;
import model.participants.Dealer;
import model.participants.Player;
import model.participants.Players;
import model.rules.ClassicRules;
import model.rules.Rules;
import observer.ListenableObject;

import java.util.Iterator;

/**
 * <b>
 *     Class that modelize a blackjack game.
 * </b>
 *
 *
 * @author 22013393
 * @version 1.0
 */
public class BlackjackModel extends ListenableObject{


    /**
     * The List of players.
     */
    private final Players players;

    /**
     * The dealer of the game.
     */
    private final Dealer dealer;

    /**
     * The deck of the game.
     */
    private final Deck deck;

    /**
     * The rules of the game.
     */
    private final Rules rules;

    /**
     * Boolean that indicates if the game is over.
     */
    private boolean gameOver;


    /**
     * The iterator of the players.
     */
    private Iterator<Player> iterator;

    /**
     * Creates a new blackjack game with the given players, dealer, deck and rules.
     *
     * @param players
     * 		The players of the game.
     * @param dealer
     * 		The dealer of the game.
     * @param deck
     * 		The deck of the game.
     * @param rules
     * 		The rules of the game.
     * @param gameOver
     *         Boolean that indicates if the game is over.
     *
     * @throws NullPointerException if the players, dealer, deck or rules is null.
     */
    public BlackjackModel(Players players, Dealer dealer, Deck deck, Rules rules, boolean gameOver) {
        if (players == null) {
            throw new NullPointerException("The players cannot be null.");
        }
        if (dealer == null) {
            throw new NullPointerException("The dealer cannot be null.");
        }
        if (deck == null) {
            throw new NullPointerException("The deck cannot be null.");
        }
        if (rules == null) {
            throw new NullPointerException("The rules cannot be null.");
        }
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
        this.rules = rules;
        this.gameOver = gameOver;
        this.iterator = players.iterator();
    }

    /**
     * Creates a new blackjack game with the given dealer
     *
     * @param dealer
     */
    public BlackjackModel(Dealer dealer) {
        this(new Players(), dealer, FactoryDeck.createDeck32(), new ClassicRules(), false);
    }

    /**
     * Adds a player to the game.
     *
     * @param player
     */
    public void addPlayer(Player player) {
        this.players.addPlayer(player);
        super.notifyListeners();
    }

    /**
     * Returns the players of the game.
     *
     * @return
     * 		The players of the game.
     */
    public Players getPlayers() {
        return this.players;
    }

    /**
     * Returns the dealer of the game.
     *
     * @return
     * 		The dealer of the game.
     */
    public Dealer getDealer() {
        return this.dealer;
    }

    /**
     * Returns the deck of the game.
     *
     * @return
     *         The deck of the game.
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Returns the rules of the game.
     *
     * @return
     * 		The rules of the game.
     */
    public Rules getRules() {
        return this.rules;
    }

    /**
     * Returns true if the game is over.
     *
     * @return
     * 		True if the game is over.
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Returns the iterator of the players.
     *
     * @return
     * 		The iterator of the players.
     */
    public Iterator<Player> getIterator() {
        return this.iterator;
    }

    /**
     * Sets the iterator of the players.
     *
     * @param iterator
     * 		The iterator of the players.
     */
    public void setIterator(Iterator<Player> iterator) {
        this.iterator = iterator;
    }

    /**
     * Sets the game over.
     *
     * @param gameOver
     * 		True if the game is over.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Returns the next player.
     *
     * @return
     * 		The next player.
     */
    public Player getNextPlayer() {
        return this.iterator.next();
    }

    /**
     * Returns true if the iterator has a next player.
     *
     * @return
     * 		True if the iterator has a next player.
     */
    public boolean hasNextPlayer() {
        return this.iterator.hasNext();
    }

    /**
     * Returns true if it is the dealer's turn.
     *
     * @return
     *         True if it is the dealer's turn.
     */
    public boolean isDealerTurn() {
        return !this.iterator.hasNext();
    }

    /**
     * Break all action of the current player if it is dealer's turn.
     */
    public void breakAllAction() {
        if (this.isDealerTurn()) {
            return;
        }
    }

    /**
     * The current player hits.
     */
    public void hit() {
        // We check if the current player is the dealer we break all action.
        this.breakAllAction();

        // The current player can hit now but we don't know change immediately the current player.
        // So we save the current player.
        Player currentPlayer = this.iterator.next();



    }



}
