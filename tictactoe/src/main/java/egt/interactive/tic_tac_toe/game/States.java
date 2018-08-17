package egt.interactive.tic_tac_toe.game;

import java.util.List;

import egt.interactive.tic_tac_toe.player_points.PlayerPoints;

public enum States {

    STAND_BY {
	private final static int NEEDED_MONEY = 10;

	@Override
	public boolean putCoins(final GameMachine game, final int coins) {
	    if (!isMoneyValid(coins)) {
		return false;
	    }

	    game.insertCash(coins);
	    if (isMoneyEnough(game)) {
		game.setState(GAME_IN_PROGRESS);
		game.takeMoney(NEEDED_MONEY);
		drawBoard(game);
	    } else {
		askForMoreMoney(game);
	    }
	    return true;
	}

	@Override
	public boolean returnMoney(final GameMachine game) {
	    return true;
	}

	private void drawBoard(final GameMachine game) {
	    game.getDrawer().drawBoard(game.getBoard());
	}

	private void askForMoreMoney(final GameMachine game) {
	    final int moneyToAdd = NEEDED_MONEY - game.getCoins();
	    game.getIO().write(String.format("Please insert %d more", moneyToAdd));
	}

	private boolean isMoneyValid(final int coins) {
	    return coins > 0;
	}

	public boolean isMoneyEnough(final GameMachine game) {
	    return game.getCoins() >= NEEDED_MONEY;
	}
    },
    GAME_IN_PROGRESS {

	@Override
	public boolean playMove(final GameMachine game, final int move) {
	    if (!game.getBoard().isMoveValid(move) || !game.getBoard().isFieldFree(move)) {
		invalidField(game);
		return false;
	    }

	    addPlayerMove(move, game);
	    if (hasPlayerWon(move, game) || isGameDraw(game)) {
		drawBoard(game);
		finishGame(game);
		return true;
	    }

	    final Integer aIMove = addAIMove(game);
	    drawBoard(game);
	    if (isGameOver(aIMove, game)) {
		finishGame(game);
	    }

	    return true;
	}

	public void addPlayerMove(final int move, final GameMachine game) {
	    game.getBoard().addMove(move, game.getMessages().getPlayerSign());
	}

	private void finishGame(final GameMachine game) {
	    game.getOutCome().finishGame(game);
	    game.setState(END_GAME);
	}

	private void drawBoard(final GameMachine game) {
	    game.getDrawer().drawBoard(game.getBoard());
	}

	private boolean hasPlayerWon(final int move, final GameMachine game) {
	    if (isWinner(move, game)) {
		// player wins
		game.setOutcome(GameOutcome.PLAYER_WINS);
		return true;
	    }
	    return false;
	}

	private boolean isGameDraw(final GameMachine game) {
	    if (game.getBoard().isFull()) {
		// game is draw
		game.setOutcome(GameOutcome.DRAW);
		return true;
	    }
	    return false;
	}

	private boolean isGameOver(final int aIMove, final GameMachine game) {
	    if (isWinner(aIMove, game)) {
		// aiWins
		game.setOutcome(GameOutcome.AI_WINS);
		return true;
	    }
	    return false;
	}

	private boolean isWinner(final int move, final GameMachine game) {
	    final int size = game.getBoard().getSize();
	    String[][] fields = game.getBoard().getAllFields();
	    final int x = move / size;
	    final int y = move % size;
	    final String sign = fields[x][y];
	    return this.checkCol(fields, x, sign, move, size) || this.checkRow(fields, y, sign, move, size)
		    || this.checkDiag(fields, x, y, sign, move, size)
		    || this.checkAntiDiag(fields, x, y, sign, move, size);
	}

	private boolean checkAntiDiag(final String[][] fields, final int x, final int y, final String sign,
		final int move, final int size) {
	    if (x + y == size - 1) {
		for (int i = 0; i < size; i++) {
		    if (fields[i][(size - 1) - i] != sign) {
			return false;
		    }
		}
		return true;
	    }
	    return false;
	}

	private boolean checkDiag(final String[][] fields, final int x, final int y, final String sign, final int move,
		final int size) {
	    if (x == y) {
		for (int i = 0; i < size; i++) {
		    if (fields[i][i] != sign) {
			return false;
		    }
		}
		return true;
	    }
	    return false;
	}

	private boolean checkRow(final String[][] fields, final int y, final String sign, final int move,
		final int size) {
	    for (int i = 0; i < size; i++) {
		if (fields[i][y] != sign) {
		    return false;
		}
	    }
	    return true;
	}

	private boolean checkCol(final String[][] fields, final int x, final String sign, final int move,
		final int size) {
	    for (int i = 0; i < size; i++) {
		if (fields[x][i] != sign) {
		    return false;
		}
	    }
	    return true;
	}

	private Integer addAIMove(final GameMachine game) {
	    final Integer aIMove = game.getAi().playAIMove(game.getBoard());
	    if (aIMove != null) {
		game.getBoard().addMove(aIMove, game.getMessages().getAiSign());
	    }
	    return aIMove;
	}

	private void invalidField(final GameMachine game) {
	    game.getIO().write(game.getMessages().getInvalidMoveMessage());

	}
    },

    END_GAME {

	@Override
	public boolean endGame(final GameMachine game) {
	    printTopThreePlayers(game);
	    game.setState(STAND_BY);
	    return true;
	}

	private void printTopThreePlayers(final GameMachine game) {
	    final List<PlayerPoints> topThreePlayers = game.getDBQueryExecutor().getTopThreePlayers();
	    final StringBuilder sb = new StringBuilder();
	    sb.append("   Hall of Fame").append(System.lineSeparator());
	    int count = 1;
	    for (PlayerPoints pp : topThreePlayers) {

		sb.append(count++).append(". ").append(String.format("%-12s %-12s", pp.getName(), pp.getPoints()))
			.append(System.lineSeparator());

	    }

	    game.getIO().write(sb.toString());

	}
    };

    public boolean putCoins(final GameMachine game, final int coins) {
	return false;
    }

    public boolean playMove(final GameMachine game, final int move) {
	return false;
    }

    public boolean returnMoney(final GameMachine game) {
	return false;
    }

    public boolean endGame(final GameMachine game) {
	return false;
    }

}