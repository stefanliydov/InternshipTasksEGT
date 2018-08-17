package egt.interactive.tic_tac_toe.arcade;

import egt.interactive.tic_tac_toe.board.Board;

public interface BoardArcadeGame {

    public int putCoins(final int coins);

    public boolean playMove(final int move);

    public int getCoins();

    public int returnMoney();

    public boolean giveName(final String name);

    public boolean endGame();

    public int getTotalMoney();

    public Board getBoard();
}
