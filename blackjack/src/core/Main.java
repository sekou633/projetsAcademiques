package core;


import controller.BlackjackController;
import model.cards.FactoryDeck;
import model.participants.Dealer;
import model.rules.ClassicRules;
import model.rules.Rules;
import view.Blackjack;
import view.console.TerminalBlackjack;
import view.graphics.BlackjackGUI;

public class Main {

    public static void main(String[] args) {

//        Player player = new Player("John", 1000);
//        Rules rules = new ClassicRules();
//
//        BlackjackController controller = new BlackjackController(rules, List.of(player), new Player("Dealer", 1000),
//                FactoryDeck.createDeck32());
//
//
//        Blackjack blackjack = new Blackjack(controller);
//
//        blackjack.init();
//
//        controller.startGame();
//
//        blackjack.display();


        Rules rules = new ClassicRules();

        Dealer dealer = new Dealer("Dealer", rules.getEvaluator(), 1000);

        BlackjackController controller = new BlackjackController(rules, dealer, FactoryDeck.createDeck32());

        TerminalBlackjack blackjack = new TerminalBlackjack(controller);
        // Blackjack blackjack = new BlackjackGUI(controller);

        blackjack.launch();

//
//        Blackjack blackjack = new Blackjack(controller);
//
//        controller.init();
//
//        controller.play();







    }

}

