package egt.interactive.tic_tac_toe;

import static egt.interactive.tic_tac_toe.resources.MyReflection.isOutcomeCorrect;
import static egt.interactive.tic_tac_toe.resources.MyReflection.isStateCorrect;
import static egt.interactive.tic_tac_toe.resources.TicTacToeFactory.createNewGameWithBoard;
import static egt.interactive.tic_tac_toe.resources.TicTacToeFactory.createNewPaidTicTacToeGame;
import static egt.interactive.tic_tac_toe.resources.TicTacToeFactory.createNewTicTacToeGame;
import static egt.interactive.tic_tac_toe.resources.TicTacToeFactory.createNewTicTacToeGameWithStrategy;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import egt.interactive.tic_tac_toe.ai_strategy.AiStrategy;
import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.board.Board;
import egt.interactive.tic_tac_toe.board.TicTacToeBoard;
import egt.interactive.tic_tac_toe.game.GameOutcome;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.resources.DBQueryHelper;
import egt.interactive.tic_tac_toe.resources.RandomNumberGenerator;

public class PlayMoveTests {
    private static final int TARGET_MONEY = 10;
    private static final String DUMMY_NAME = "Dummy name";
    private static final DBQueryHelper dbHelper = new DBQueryHelper();

    @Test
    public void playMoveShouldPutCrossCorectlyOnTheBoard() {
	BoardArcadeGame game = createNewPaidTicTacToeGame();
	for (int i = 0; i < 9; i++) {
	    game.playMove(i);
	    final String a = game.getBoard().getField(i);
	    assertEquals(a, "X");
	    game = createNewPaidTicTacToeGame();
	}
    }

    @Test
    public void playMoveShouldThenHaveComputerPlay() {
	final AiStrategy strategy = new AiStrategy() {
	    public int count = 0;

	    @Override
	    public Integer playAIMove(final Board board) {
		return count++;
	    }
	};
	final BoardArcadeGame game = createNewTicTacToeGameWithStrategy(strategy);
	game.putCoins(TARGET_MONEY);

	int playerMove = 4;

	for (int i = 0; i < 3; i++) {
	    Assert.assertEquals(game.getBoard().getField(i), " ");
	    game.playMove(playerMove++);
	    Assert.assertEquals(game.getBoard().getField(i), "O");
	}
    }

    @Test
    public void playMoveShouldHaveGameEndIfTPlayerWinsAndTakeName() {
	dbHelper.dropCreateEmptyTables();

	final AiStrategy strategy = new AiStrategy() {
	    public int count = 0;

	    @Override
	    public Integer playAIMove(Board board) {
		return count++;
	    }
	};
	final BoardArcadeGame game = createNewTicTacToeGameWithStrategy(strategy);
	game.putCoins(TARGET_MONEY);

	int playerMove = 6;

	for (int i = 1; i < 4; i++) {
	    game.playMove(playerMove++);
	}

	Assert.assertTrue(game.giveName(DUMMY_NAME));

	Assert.assertTrue(game.endGame());

	Assert.assertTrue(isStateCorrect(game, States.STAND_BY));

	Assert.assertTrue(isOutcomeCorrect(game, GameOutcome.PLAYER_WINS));

	Assert.assertTrue(isPlayerAdded());

	Assert.assertTrue(isGameAdded());

	Assert.assertTrue(isOutcomeCorrect(game, getDBOutcome()));
    }

    @Test
    public void playMoveShouldHaveGameEndIfTheGameIsDrawAndNotAcceptName() {
	dbHelper.dropCreateEmptyTables();
	final Board board = new TicTacToeBoard();
	board.addMove(2, "O");
	board.addMove(3, "O");
	board.addMove(4, "O");
	board.addMove(8, "O");

	for (int i = 0; i < 7; i++) {
	    if (board.getField(i) == " ") {
		board.addMove(i, "X");
	    }
	}

	final BoardArcadeGame game = createNewGameWithBoard(board);
	game.putCoins(TARGET_MONEY);
	game.playMove(7);

	Assert.assertFalse(game.giveName(DUMMY_NAME));

	Assert.assertTrue(isStateCorrect(game, States.END_GAME));

	Assert.assertTrue(game.endGame());

	Assert.assertTrue(isStateCorrect(game, States.STAND_BY));

	Assert.assertTrue(isOutcomeCorrect(game, GameOutcome.DRAW));

	Assert.assertTrue(isGameAdded());

	Assert.assertTrue(isOutcomeCorrect(game, getDBOutcome()));
    }

    @Test
    public void playMoveWithAIWinningShouldEndGameAndAddInDB() {
	dbHelper.dropCreateEmptyTables();
	final AiStrategy strategy = new AiStrategy() {
	    public int count = 0;

	    @Override
	    public Integer playAIMove(final Board board) {
		return count++;
	    }
	};
	final BoardArcadeGame game = createNewTicTacToeGameWithStrategy(strategy);
	game.putCoins(TARGET_MONEY);

	int playerMove = 4;

	for (int i = 0; i < 3; i++) {
	    game.playMove(playerMove++);
	}

	Assert.assertFalse(game.giveName(DUMMY_NAME));

	Assert.assertTrue(isStateCorrect(game, States.END_GAME));

	Assert.assertTrue(game.endGame());

	Assert.assertTrue(isStateCorrect(game, States.STAND_BY));

	Assert.assertTrue(isOutcomeCorrect(game, GameOutcome.AI_WINS));

	Assert.assertTrue(isGameAdded());

	Assert.assertTrue(isOutcomeCorrect(game, getDBOutcome()));

    }

    @Test
    public void playMoveWithAnInvalidIndexShouldReturnFalse() {
	final BoardArcadeGame game = createNewPaidTicTacToeGame();
	Assert.assertFalse(game.playMove(RandomNumberGenerator.generate(-30, -1)));
	Assert.assertFalse(game.playMove(RandomNumberGenerator.generate(9, 30)));

    }

    @Test
    public void playMoveTwiceAtTheIndexShouldReturnFalse() {
	final BoardArcadeGame game = createNewPaidTicTacToeGame();
	final int index = RandomNumberGenerator.generate(0, 9);
	Assert.assertTrue(game.playMove(index));
	Assert.assertFalse(game.playMove(index));
    }

    @Test
    public void playMoveOnAIndexTakenByTheAiShouldReturnFalse() {
	final AiStrategy strategy = new AiStrategy() {
	    public int count = 1;

	    @Override
	    public Integer playAIMove(final Board board) {
		return count++;
	    }
	};
	final BoardArcadeGame game = createNewTicTacToeGameWithStrategy(strategy);
	game.putCoins(TARGET_MONEY);
	game.playMove(0);

	Assert.assertFalse(game.playMove(1));

    }

    @Test
    public void playMoveOnStandByShouldReturnFalse() {
	final BoardArcadeGame game = createNewTicTacToeGame();
	Assert.assertFalse(game.playMove(RandomNumberGenerator.generate(0, 9)));
    }

    private GameOutcome getDBOutcome() {
	return dbHelper.getGameOutcome();
    }

    private boolean isGameAdded() {
	return dbHelper.getGamesCount() == 1;
    }

    private boolean isPlayerAdded() {
	return dbHelper.getPlayersCount() == 1;
    }
}
