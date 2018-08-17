package egt.interactive.tic_tac_toe;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.resources.MyDataProvider;

public class EndGameMethodTests {

    @DataProvider(name = "getGame")
    public Object[][] getData() {
	return MyDataProvider.getNewTicTacToeGame();
    }

    @Test(dataProvider = "getGame")
    public void endGameShouldNotBeValidAtStandBy(final BoardArcadeGame game) {
	Assert.assertFalse(game.endGame());
    }

    @Test(dataProvider = "getGame")
    public void endGameShouldNotBeValidWhileGameIsGoing(final BoardArcadeGame game) {
	Assert.assertFalse(game.endGame());
    }

}
