package egt.interactive.tic_tac_toe.game;

import egt.interactive.tic_tac_toe.ai_strategy.AiStrategy;
import egt.interactive.tic_tac_toe.ai_strategy.RandomStrategy;
import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.board.Board;
import egt.interactive.tic_tac_toe.board.TicTacToeBoard;
import egt.interactive.tic_tac_toe.connection.DBQueryExecutor;
import egt.interactive.tic_tac_toe.drawer.BoardDrawer;
import egt.interactive.tic_tac_toe.drawer.TicTacToeBoardDrawer;
import egt.interactive.tic_tac_toe.game_messages.GameMessages;
import egt.interactive.tic_tac_toe.game_utils.BoardGameUtils;
import egt.interactive.tic_tac_toe.io.ConsoleIO;
import egt.interactive.tic_tac_toe.io.IO;

public class TicTacToe implements GameMachine, BoardArcadeGame {
    private final IO io;
    private final DBQueryExecutor connection;
    private final BoardGameUtils gameUtils;
    private final GameMessages messages;
    private int coins;
    private int totalMoneyCollected;

    private TicTacToe(final Builder builder) {
	this.io = builder.io;
	this.connection = builder.connection;
	this.messages = new GameMessages();
	this.gameUtils = builder.gameUtils;
	this.gameUtils.setDrawer(new TicTacToeBoardDrawer(io));
	this.gameUtils.setState(States.STAND_BY);
    }

    public static class Builder {
	private IO io = new ConsoleIO();
	private DBQueryExecutor connection = new DBQueryExecutor();
	private BoardGameUtils gameUtils = new BoardGameUtils(new RandomStrategy(), new TicTacToeBoard());

	public Builder io(final IO val) {
	    io = val;
	    return this;
	}

	public Builder board(final Board val) {
	    gameUtils.setBoard(val);
	    return this;
	}

	public Builder aiStrategy(final AiStrategy val) {
	    gameUtils.setStrategy(val);
	    return this;
	}

	public Builder connection(final DBQueryExecutor val) {
	    connection = val;
	    return this;
	}

	public Builder gameUtils(final BoardGameUtils val) {
	    gameUtils = val;
	    return this;
	}

	public BoardArcadeGame build() {
	    return new TicTacToe(this);
	}
    }

    @Override
    public int putCoins(final int coins) {
	return this.gameUtils.getState().putCoins(this, coins) ? this.returnExtraMoney() : coins;

    }

    @Override
    public boolean playMove(final int move) {
	return this.gameUtils.getState().playMove(this, move);
    }

    @Override
    public int returnMoney() {
	if (this.gameUtils.getState().returnMoney(this)) {
	    final int temp = this.coins;
	    this.coins = 0;
	    return temp;
	}
	return 0;
    }

    @Override
    public boolean giveName(final String name) {
	return this.gameUtils.getOutcome().giveName(this, name);

    }

    @Override
    public boolean endGame() {
	return this.gameUtils.getState().endGame(this);
    }

    @Override
    public Board getBoard() {
	return this.gameUtils.getBoard();
    }

    @Override
    public States getState() {
	return this.gameUtils.getState();
    }

    @Override
    public int getCoins() {
	return this.coins;
    }

    @Override
    public void insertCash(final int coins) {
	this.coins += coins;

    }

    @Override
    public void setState(final States state) {
	this.gameUtils.setState(state);

    }

    @Override
    public void takeMoney(final int money) {
	this.totalMoneyCollected += money;

    }

    private int returnExtraMoney() {
	if (this.coins > 10) {
	    return coins - 10;
	}
	return 0;
    }

    @Override
    public GameOutcome getOutCome() {
	return this.gameUtils.getOutcome();
    }

    @Override
    public IO getIO() {
	return this.io;
    }

    @Override
    public void setOutcome(final GameOutcome outcome) {
	this.gameUtils.setOutcome(outcome);

    }

    @Override
    public BoardDrawer getDrawer() {
	return this.gameUtils.getDrawer();
    }

    @Override
    public int getTotalMoney() {
	return this.totalMoneyCollected;
    }

    @Override
    public AiStrategy getAi() {
	return this.gameUtils.getStrategy();
    }

    @Override
    public DBQueryExecutor getDBQueryExecutor() {
	return this.connection;
    }

    @Override
    public GameMessages getMessages() {
	return this.messages;
    }

}
