package model.participants.bot;

import model.BlackjackCardEvaluator;
import model.participants.Player;

/**
 * <b>
 *     Bot is a participant of the game that can play automatically.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class Bot extends Player {

    /**
     * Creates a new bot with the given name and money.
     *
     * @param name      The name of the bot.
     * @param evaluator The evaluator of the bot.
     * @param money     The money of the bot.
     */
    public Bot(String name, BlackjackCardEvaluator evaluator, int money) {
        super(name, evaluator, money);
    }



}