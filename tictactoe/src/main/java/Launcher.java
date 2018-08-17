
import java.text.NumberFormat;
import java.text.ParsePosition;

import egt.interactive.tic_tac_toe.ai_strategy.RandomStrategy;
import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.board.TicTacToeBoard;
import egt.interactive.tic_tac_toe.game.GameOutcome;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.game.TicTacToe;
import egt.interactive.tic_tac_toe.game_utils.BoardGameUtils;
import egt.interactive.tic_tac_toe.io.ConsoleIO;
import egt.interactive.tic_tac_toe.io.IO;

public class Launcher {

    public static void main(String[] args) {
	final IO io = new ConsoleIO();
	final BoardGameUtils gu = new BoardGameUtils(new RandomStrategy(), new TicTacToeBoard());
	final BoardArcadeGame game = new TicTacToe.Builder().io(io).gameUtils(gu).build();
	io.write("Please insert 10 coins");
	while (gu.getState() == States.STAND_BY) {
	    final String coins = io.read();
	    if (isNumeric(coins)) {
		game.putCoins(Integer.valueOf(coins));
	    }
	}

	while (gu.getOutcome() == null) {
	    game.playMove(Integer.valueOf(io.read()));
	}
	if (gu.getOutcome() == GameOutcome.PLAYER_WINS) {
	    while (game.giveName(io.read()) == false) {
	    }
	}
	game.endGame();
    }

    public static boolean isNumeric(final String str) {
	if (str.equals("")) {
	    return false;
	}
	final NumberFormat formatter = NumberFormat.getInstance();
	final ParsePosition pos = new ParsePosition(0);
	formatter.parse(str, pos);
	return str.length() == pos.getIndex();
    }
}
