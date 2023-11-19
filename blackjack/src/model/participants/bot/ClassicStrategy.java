package model.participants.bot;

import model.cards.Deck;
import model.participants.Dealer;

public class ClassicStrategy implements BotStrategy {

    /**
     * The dealer of the game.
     */
    private final Dealer dealer;

    /**
     * The current bot.
     */
    private final Bot bot;

    /**
     * The bot that will use the strategy.
     *
     * @param dealer
     *       The dealer of the game.
     * @param bot
     *      The bot that will use the strategy.
     */
    public ClassicStrategy(Dealer dealer, Bot bot) {
        this.dealer = dealer;
        this.bot = bot;
    }

    @Override
    public void execute(Deck deck) {


    }
}

