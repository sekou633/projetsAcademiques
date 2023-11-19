package view.graphics;

import controller.BlackjackController;
import model.participants.Player;
import model.participants.Players;
import view.Blackjack;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     BlackjackGUI is the first screen of the game.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class BlackjackGUI extends JFrame implements Blackjack {

    /**
     * The controller of the start screen.
     */
    private final BlackjackController controller;


    /**
     * Creates a new start screen.
     *
     * @param controller
     *         The controller of the start screen.
     */
    public BlackjackGUI(BlackjackController controller) {
        this.controller = controller;
        this.initComponents();
    }

    /**
     * Initializes the components of the start screen.
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(new JLabel("Blackjack"));
        this.pack();
        this.setLocationRelativeTo(null);
        this.getController().addListener(this);
    }

    /**
     * Show the rules of the game.
     *
     */
    public void showRules() {
        JOptionPane.showMessageDialog(this, "The rules of the game are the following:\n" +
                "The goal of the game is to beat the dealer's hand without going over 21.\n" +
                "Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\n" +
                "Each player starts with two cards, one of the dealer's cards is hidden until the end.\n" +
                "To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n");
    }


    /**
     * Gets the controller of the game.
     *
     * @return the controller of the game.
     */
    @Override
    public BlackjackController getController() {
        return this.controller;
    }

    /**
     * Initialize the view.
     */
    @Override
    public void initialize() {

        // Set the layout of the frame.
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Add the dealer component.
        this.add(new ParticipantComponent(this.controller.getDealer()));

        // Add the Actions component.
        this.add(new ActionsComponent(this.controller));

        // Panel that contains the players.
        JPanel playersPanel = new JPanel();

        playersPanel.setLayout(new GridLayout(1, this.getPlayers().size()));

        // Add the players components.
        for (Player player : this.controller.getPlayers()) {
            playersPanel.add(new ParticipantComponent(player));
        }

        // Add the players panel.
        this.add(playersPanel);

        // Pack the frame.
        this.pack();

        // Set the location of the frame.
        this.setLocationRelativeTo(null);

        // Build information players frame.
        JFrame informationPlayersFrame = new InformationsPlayers(new Players(this.controller.getPlayers()));

        // Show the information players frame.
        informationPlayersFrame.setVisible(true);



    }




    /**
     * Ask number of players.
     *
     * @return The number of players.
     */
    public int askNumberOfPlayers() {
        String input = JOptionPane.showInputDialog(this, "How many players?");

        if (input == null) {
            System.exit(0);
        }

        try {
            int numberOfPlayers = Integer.parseInt(input);
            if (numberOfPlayers < 1) {
                JOptionPane.showMessageDialog(this, "The number of players must be greater than 0.");
                return this.askNumberOfPlayers();
            }
            return numberOfPlayers;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The number of players must be an integer.");
            return this.askNumberOfPlayers();
        }
    }

    /**
     * Ask the player to enter his name and his money.
     *
     * @param i
     *        The number of the player.
     * @return player
     */
    public Player askPlayer(int i) {
        String name = JOptionPane.showInputDialog(this, "Player " + i + " enter your name");
        if (name == null) {
            System.exit(0);
        }
        int money = Integer.parseInt(JOptionPane.showInputDialog(this, "Player " + i + " enter your money"));

        while (money <= 0) {
            // Pop up a message to ask the player to enter a valid amount of money and wait for the player to click on OK.
            JOptionPane.showMessageDialog(this, "Please enter a valid amount of money (greater than 0)");
            money = Integer.parseInt(JOptionPane.showInputDialog(this, "Player " + i + " enter your money"));
        }

        return new Player(name, this.controller.getRules().getEvaluator(), money);
    }

    @Override
    public int askBet(Player player) {
        int maxBet = player.getMoney();
        int minBet = this.controller.getRules().getMinimumBet();
        if (maxBet > this.getRules().getMaximumBet()) {
            maxBet = this.getRules().getMaximumBet();
        }
        String input = JOptionPane.showInputDialog(this, "Player " + player.getName() + " you have " + player.getMoney() + "€. How much do you want to bet? (min : " + minBet + "€, max : " + maxBet + "€)");
        if (input == null) {
            System.exit(0);
        }
        try {
            int bet = Integer.parseInt(input);
            if (bet < minBet || bet > maxBet) {
                JOptionPane.showMessageDialog(this, "The bet must be between " + minBet + "€ and " + maxBet + "€.");
                return this.askBet(player);
            }
            else if (bet > player.getMoney()) {
                JOptionPane.showMessageDialog(this, "You don't have enough money.");
                return this.askBet(player);
            }
            return bet;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The bet must be an integer.");
            return this.askBet(player);
        }
    }

    /**
     * Ask a player to plau again.
     *
     * @param player
     *       The player.
     * @return True if the player wants to play again, false otherwise.
     */
    public boolean askPlayAgain(Player player) {
        int input = JOptionPane.showConfirmDialog(this, "Player " + player.getName() + " do you want to play again?");
        if (input == JOptionPane.YES_OPTION) {
            return true;
        } else if (input == JOptionPane.NO_OPTION) {
            return false;
        } else {
            System.exit(0);
            return false;
        }
    }

    /**
     * Show that we have no players.
     */
    @Override
    public void showNoPlayers() {

        // Pop up a message to inform the player that there is no player and wait for the player to click on OK.
        JOptionPane.showMessageDialog(this, "There is no player in the game. The game will be closed.");

        // Close the game.
        this.quitGame();

    }

    /**
     * Show the round
     */
    @Override
    public void showRound() {

            // Pop up a message to inform the player that the round is starting and wait for the player to click on OK.
            JOptionPane.showMessageDialog(this, "Round 0" + " is starting.");

            this.setVisible(true);


    }

    /**
     * Display the result of the round.
     */
    @Override
    public void showResult() {

        // Pop up a message to inform the player that the round is over and wait for the player to click on OK.
        JOptionPane.showMessageDialog(this, "Round 0" + " is over.");

        // Show the value of the dealer's hand and all players' hands and say if they won or lost.
        String message = "The dealer has " + this.getDealer().evaluateHand() + " points.\n";
        for (Player player : this.getPlayers()) {
            message += "Player " + player.getName() + " has " + player.evaluateHand() + " points.\n";
            if (this.getController().hasPlayerWon(player)) {
                message += "Player " + player.getName() + " won.\n";
            } else {
                message += "Player " + player.getName() + " lost.\n";
            }
            if (player.getMoney() == 0) {
                message += "Player " + player.getName() + " has no more money.\n";
            }
        }

        // Pop up a message to inform the player that the round is over and wait for the player to click on OK.
        JOptionPane.showMessageDialog(this, message);

        // Close the game.
        this.quitGame();


    }

    /**
     * Display a message that the bet of the player will bet "bet".
     *
     * @param player The player.
     * @param bet    The bet.
     */
    @Override
    public void displayBet(Player player, int bet) {

    }

    /**
     * Display that the player could not bet the amount and that it will be removed from the game.
     *
     * @param player The player.
     * @param bet    The bet.
     */
    @Override
    public void displayInvalidBet(Player player, int bet) {

    }

    /**
     * Display that we have no more players and that the game will end.
     */
    @Override
    public void displayNoPlayerLeft() {

    }










}
