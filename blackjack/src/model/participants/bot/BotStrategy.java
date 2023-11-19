package model.participants.bot;

import model.cards.Deck;

/**
 * <b>
 *     The strategy that the bot will use.
 * </b>
 *
 * <p>
 *     The strategy is used to determine what action will be performed by the bot.
 *     Different strategies can be used to determine the action of the bot.
 *     So all the strategies must implement this interface.
 * </p>
 */
public interface BotStrategy {

    /**
     * Excecute the strategy.
     *
     * @param deck
     *        The deck of cards.
     */
    void execute(Deck deck);

}
