package egt.interactive.tic_tac_toe.resources;

public class MyDataProvider {

    public static Object[][] getNewTicTacToeGame() {
	return new Object[][] { { TicTacToeFactory.createNewTicTacToeGame() } };
    }

    public static Object[][] getNewPaidTicTacToeGameProvider() {
	return new Object[][] { { TicTacToeFactory.createNewPaidTicTacToeGame() } };
    }

    public static Object[][] getNewTicTacToeGameAtEndState() {
	return new Object[][] { { TicTacToeFactory.createNewEndStateTicTacToegame() } };
    }

}
