package model.participants;

import observer.Listenable;
import observer.ListenableObject;
import observer.Listener;

import java.util.*;

/**
 * <b>
 *     Players is the class that contains all the players of the game.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class Players extends ListenableObject implements Iterable<Player>, Listener{

    /**
     * The players of the game.
     */
    private final List<Player> players;

    /**
     * Creates a new list of players with the given players.
     *
     * @param players
     * 		The players of the game.
     *
     * @throws NullPointerException if the players is null.
     */
    public Players(List<Player> players) {
        super();
        if (players == null) {
            throw new NullPointerException("The players cannot be null.");
        }
        this.players = players;
        this.players.forEach(player -> player.addListener(this));
    }

    /**
     * Creates a new list of players.
     */
    public Players() {
        this(new ArrayList<>());
    }

    /**
     * Adds a player to the list.
     *
     * @param player
     * 		The player to add.
     *
     * @throws NullPointerException if the player is null.
     */
    public void addPlayer(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        players.add(player);
        super.notifyListeners();
    }

    /**
     * Removes a player from the list.
     *
     * @param player
     * 		The player to remove.
     *
     * @throws NullPointerException if the player is null.
     */
    public void removePlayer(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        players.remove(player);
        super.notifyListeners();
    }

    /**
     * Returns the players of the game.
     *
     * @return
     * 		The players of the game.
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the number of players.
     *
     * @return
     * 		The number of players.
     */
    public int getNumberOfPlayers() {
        return this.players.size();
    }

    /**
     * Returns the player at the given index.
     *
     * @param index
     * 		The index of the player.
     *
     * @return
     * 		The player at the given index.
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Player getPlayer(int index) {
        if (index < 0 || index >= this.players.size()) {
            throw new IndexOutOfBoundsException("The index is out of bounds because it is " + index + " and the size is " + this.players.size() + ".");
        }
        return this.players.get(index);
    }

    /**
     * Returns the player with the given name.
     *
     * @param name
     * 		The name of the player.
     *
     * @return
     * 		The player with the given name.
     *
     * @throws NullPointerException if the name is null.
     */
    public Player getPlayer(String name) {
        if (name == null) {
            throw new NullPointerException("The name cannot be null.");
        }
        for (Player player : this.players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Returns the index of the given player.
     *
     * @param player
     * 		The player to get the index of.
     *
     * @return
     * 		The index of the given player.
     *
     * @throws NullPointerException if the player is null.
     */
    public int getPlayerIndex(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        return this.players.indexOf(player);
    }

    /**
     * Indicates whether the given player is in the list.
     *
     * @param player
     *         The player to check.
     * @return True if the player is in the list, false otherwise.
     */
    public boolean contains(Player player) {
        return this.players.contains(player);
    }

    /**
     * Returns the next player in the list.
     *
     * @param player
     * 		The player to get the next player of.
     *
     * @return
     * 		The next player in the list.
     *
     * @throws NullPointerException if the player is null.
     */
    public Player getNextPlayer(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        int index = this.players.indexOf(player);
        if (index == -1) {
            throw new IllegalArgumentException("The player is not in the list.");
        }
        return this.players.get((index + 1) % this.players.size());
    }

    /**
     * Returns the previous player in the list.
     *
     * @param player
     * 		The player to get the previous player of.
     *
     * @return
     * 		The previous player in the list.
     *
     * @throws NullPointerException if the player is null.
     */
    public Player getPreviousPlayer(Player player) {
        if (player == null) {
            throw new NullPointerException("The player cannot be null.");
        }
        int index = this.players.indexOf(player);
        if (index == -1) {
            throw new IllegalArgumentException("The player is not in the list.");
        }
        return this.players.get((index - 1 + this.players.size()) % this.players.size());
    }

    /**
     * Returns the player with the highest score.
     *
     * @return
     * 		The player with the highest score.
     */
    public Player getWinner() {
        Player winner = null;
        for (Player player : this.players) {
            if (winner == null || player.evaluateHand() > winner.evaluateHand()) {
                winner = player;
            }
        }
        return winner;
    }

    /**
     * Returns the player with the lowest score.
     *
     * @return
     * 		The player with the lowest score.
     */
    public Player getLoser() {
        Player loser = null;
        for (Player player : this.players) {
            if (loser == null || player.evaluateHand() < loser.evaluateHand()) {
                loser = player;
            }
        }
        return loser;
    }

    /**
     * Sorts the players
     *
     * @param comparator
     *         The comparator to use.
     * @throws NullPointerException if the comparator is null.
     */
    public void sort(Comparator<Player> comparator) {
        if (comparator == null) {
            throw new NullPointerException("The comparator cannot be null.");
        }
        this.players.sort(comparator);
        super.notifyListeners();
    }

    /**
     * Sorts the players by their score.
     */
    public void sortByScore() {
        this.sort((player1, player2) -> player1.evaluateHand() - player2.evaluateHand());
    }

    /**
     * Sorts the players by their name.
     */
    public void sortByName() {
        this.sort((player1, player2) -> player1.getName().compareTo(player2.getName()));
    }

    /**
     * Sorts the players by their money.
     */
    public void sortByMoney() {
        this.sort((player1, player2) -> player1.getMoney() - player2.getMoney());
    }

    /**
     * Sorts the players by their bet.
     */
    public void sortByBet() {
        this.sort((player1, player2) -> player1.getBet() - player2.getBet());
    }


    @Override
    public String toString() {
        return this.players.toString();
    }

    @Override
    public Iterator<Player> iterator() {
        return this.players.iterator();
    }


    @Override
    public void notify(Listenable listenable) {
        super.notifyListeners();
    }
}
