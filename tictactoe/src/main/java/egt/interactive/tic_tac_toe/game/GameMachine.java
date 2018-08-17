package egt.interactive.tic_tac_toe.game;

import egt.interactive.tic_tac_toe.ai_strategy.AiStrategy;
import egt.interactive.tic_tac_toe.board.Board;
import egt.interactive.tic_tac_toe.connection.DBQueryExecutor;
import egt.interactive.tic_tac_toe.drawer.BoardDrawer;
import egt.interactive.tic_tac_toe.game_messages.GameMessages;
import egt.interactive.tic_tac_toe.io.IO;

interface GameMachine {

    void insertCash(final int coins);

    void setState(final States state);

    IO getIO();

    DBQueryExecutor getDBQueryExecutor();

    void setOutcome(final GameOutcome outcome);

    States getState();

    int getCoins();

    Board getBoard();

    GameOutcome getOutCome();

    void takeMoney(final int money);

    BoardDrawer getDrawer();

    AiStrategy getAi();

    GameMessages getMessages();
}
