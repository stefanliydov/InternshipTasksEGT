package egt.interactive.tic_tac_toe.resources;

import static egt.interactive.tic_tac_toe.resources.MyReflection.isStateCorrect;

import egt.interactive.tic_tac_toe.ai_strategy.AiStrategy;
import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.board.Board;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.game.TicTacToe;

public class TicTacToeFactory {

    public static BoardArcadeGame createNewTicTacToeGame() {
	return new TicTacToe.Builder().build();
    }

    public static BoardArcadeGame createNewPaidTicTacToeGame() {
	BoardArcadeGame game = createNewTicTacToeGame();
	game.putCoins(10);
	return game;
    }

    public static BoardArcadeGame createNewTicTacToeGameWithStrategy(final AiStrategy strategy) {
	return new TicTacToe.Builder().aiStrategy(strategy).build();
    }

    public static BoardArcadeGame createNewEndStateTicTacToegame() {
	final BoardArcadeGame game = createNewPaidTicTacToeGame();
	while (!isStateCorrect(game, States.END_GAME)) {
	    game.playMove(RandomNumberGenerator.generate(0, 9));
	}
	return game;
    }

    public static BoardArcadeGame createNewGameWithBoard(final Board board) {
	return new TicTacToe.Builder().board(board).build();
    }
}
