package view.graphics;

import model.participants.Player;
import model.participants.Players;

import javax.swing.table.AbstractTableModel;

/**
 * <b>
 *     Adapter that permits to adapt the players to the JTable.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class PlayersAdaptater extends AbstractTableModel {

    /**
     * The players of the game.
     */
    private final Players players;

    /**
     * The columns of the table.
     */
    private final String[] columns = {"Name", "Money", "Bet"};

    /**
     * Creates a new adapter with the given players.
     *
     * @param players The players of the game.
     */
    public PlayersAdaptater(Players players) {
        this.players = players;
    }

    /**
     * Returns the number of rows of the table.
     *
     * @return The number of rows of the table.
     */
    @Override
    public int getRowCount() {
        return players.getNumberOfPlayers();
    }

    /**
     * Returns the number of columns of the table.
     *
     * @return The number of columns of the table.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Returns the name of the column at the given index.
     *
     * @param columnIndex The index of the column.
     * @return The name of the column at the given index.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Returns the value at the given row and column.
     *
     * @param rowIndex    The index of the row.
     * @param columnIndex The index of the column.
     * @return The value at the given row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = players.getPlayer(rowIndex);
        switch (columnIndex) {
            case 0:
                return player.getName();
            case 1:
                return player.getMoney();
            case 2:
                return player.getBet();
            default:
                return null;
        }
    }

    /**
     * Returns the class of the column at the given index.
     *
     * @param columnIndex The index of the column.
     * @return The class of the column at the given index.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }


}
