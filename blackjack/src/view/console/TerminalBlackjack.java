package view.console;

import controller.BlackjackController;
import model.participants.Player;

import observer.Listenable;
import observer.Listener;
import view.Blackjack;

import java.util.Scanner;

/**
 * <b>
 *     A blackjack is a console view of a blackjack game.
 * </b>
 *
 *
 */
public class TerminalBlackjack implements Listener, Blackjack {


    /**
     * The controller of the game.
     */
    private final BlackjackController controller;

    /**
     * Creates a new Blackjack
     *
     * @param controller
     *         The controller of the blackjack.
     */
    public TerminalBlackjack(BlackjackController controller) {
        this.controller = controller;
    }

    @Override
    public BlackjackController getController() {
        return this.controller;
    }


    @Override
    public void initialize() {

        // Add the listener.
        this.getDealer().addListener(this);

        // Add the listener.
        this.getPlayers().forEach(player -> player.addListener(this));
    }

    @Override
    public int askNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        // Check if the input is an integer.
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public Player askPlayer(int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + index + " name : ");

        // Ask the name.
        String name = scanner.nextLine();

        // Ask the money.
        System.out.println("Player " + index + " money : ");
        int money = 0;
        // Check if the money is valid.
        while (money <= 0) {
            try {
                money = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid money.");
                System.out.println("Player " + index + " money : ");
            }
        }

        return new Player(name, this.getRules().getEvaluator(), money);
    }


    @Override
    public int askBet(Player player) {
        int maxBet = this.getRules().getMaximumBet();
        if (player.getMoney() < maxBet) {
            maxBet = player.getMoney();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + player.getName() + " you have " + player.getMoney() + "€. How much do you want to bet? (min : " + this.getRules().getMinimumBet() + "€, max : " + maxBet + "€)");
        int bet = 0;
        while (bet <= 0 || bet > maxBet) {

            // Check if the input is an integer.
            if (sc.hasNextInt()) {
                bet = sc.nextInt();
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }

        }
        return bet;
    }

    /**
     * Ask the player to play again.
     *
     * @param player The player.
     * @return True if the player wants to play again.
     */
    @Override
    public boolean askPlayAgain(Player player) {
        return false;
    }

    /**
     * Show that we have no players.
     */
    @Override
    public void showNoPlayers() {

        System.out.println("I'm sorry but we have no players. The game will be closed.");

    }

    /**
     * Show the round
     */
    @Override
    public void showRound() {
        // boolean that indicates if it is the dealer's turn.
        boolean dealerTurn = this.getController().isDealerTurn();
        if(dealerTurn){
            System.out.println("Dealer's turn.");
            this.getController().dealerPlay();

        } else {
            System.out.println("Player's turn.");
            this.askPlayerToPlay();
            this.showRound();
        }

    }

    /**
     * Display the result of the round.
     */
    @Override
    public void showResult() {

        System.out.println("Result : ");
        // Display the dealer hand value.
        System.out.println("Dealer hand value : " + this.getDealer().evaluateHand());

        // Display the players hand value.
        this.getPlayers().forEach(player -> {
            System.out.println(player.getName() + " hand value : " + player.evaluateHand());
        });

        // Say if the player won or lost.
        this.getPlayers().forEach(player -> {
            if (this.getController().hasPlayerWon(player)) {
                System.out.println(player.getName() + " won !");
            } else {
                System.out.println(player.getName() + " lost !");
            }
            // Pay or collect the money.
            System.out.println(player.getName() + " money : " + player.getMoney());
        });
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

    /**
     * Show the rules of the game.
     */
    @Override
    public void showRules() {
        // Clear the screen.
        this.clearScreen();

        System.out.println("New round !");

        // Introduction.
        System.out.println("Welcome to Blackjack !");
        System.out.println("The goal of the game is to get a hand as close to 21 as possible without going over.");
        System.out.println("The dealer will deal " + this.getRules().getInitialCardsPerPlayer()+ " cards to each player and " + this.getRules().getNumberOfCardsToDealToDealer() + " cards to himself.");
        System.out.println("The dealer will then ask each player if he wants to hit, stand or double his bet.");
        System.out.println("If the player hits, he will get another card.");
        System.out.println("If the player stands, he will not get any more cards.");
        System.out.println("If the player doubles, he will double his bet and get one more card.");
    }


    /**
     * Ask Player's name and his money.
     *
     * @param index
     *        The index of the player.
     * @return The player.
     */


    /**
     * Clear the screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    /**
     * Ask a player to playAgain.
     *
     * @param player
     *       The player.
     * @return True if the player wants to play again.
     */
    public boolean askPlayerToPlayAgain(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getName() + " do you want to play again ? (y/n)");
        String answer = scanner.nextLine();

        while (!answer.matches("[yYnN]")) {
            System.out.println("Please enter a valid answer.");
            System.out.println(player.getName() + " do you want to play again ? (y/n)");
            answer = scanner.nextLine();
        }

        return answer.matches("[yY]");

    }

    /**
     * Ask the player to play.
     *
     */
    private void askPlayerToPlay() {
        System.out.println("Player " + this.getController().getCurrentPlayer().getName() + " turn.");
        // Ask the possible events.
        System.out.println("What do you want to do ?");

        System.out.println("0 : Hit");
        System.out.println("1 : Stand");
        System.out.println("2 : Double");

        // Ask the player to choose an event.
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 2) {
            // Check if the input is an integer.
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }

        switch (choice) {
        case 0:
            this.getController().hit();
            break;

        case 1:
            this.getController().stand();
            break;

        case 2:
            this.getController().doubleDown();
            break;
        }
    }



    /**
     * Updates the blackjack.
     *
     */
    public void update(){
        // Delete the terminal.
        System.out.print("\033[H\033[2J");

        // Display the players the hand, the bet and the money
        this.getPlayers().forEach(player -> {
            System.out.println(player.getName() + " : \n" + player.getHand().toString() + "\nBet : " + player.getBet() + "\nMoney : " + player.getMoney() + "\n");
        });

        // Display the dealer hand, the bet and the money
        System.out.println("Dealer : \n" + this.getDealer().getHand().toString() + "\nMoney : " + this.getDealer().getMoney() + "\n");

    }

    /**
     * Ask to continue the game.
     */
    public void askToContinue() {
        // Press enter to continue.
        System.out.println("Press enter to continue.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }


    @Override
    public void notify(Listenable listenable) {

        // display the blackjack only if the deck updates.
        this.update();
    }

    public void displayNoPlayer() {
        System.out.println("No player left.");
    }
}
