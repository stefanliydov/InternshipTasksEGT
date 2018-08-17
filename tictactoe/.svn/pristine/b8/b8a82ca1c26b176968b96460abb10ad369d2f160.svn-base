package egt.interactive.tic_tac_toe;

import static egt.interactive.tic_tac_toe.resources.MyReflection.isStateCorrect;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.resources.MyDataProvider;
import egt.interactive.tic_tac_toe.resources.RandomNumberGenerator;

public class EndGameTests {

    @DataProvider(name = "getGame")
    public Object[][] getData() {
	return MyDataProvider.getNewTicTacToeGameAtEndState();
    }

    @Test(dataProvider = "getGame")
    public void gameShouldNotReturnMoneyAtEndGame(final BoardArcadeGame game) {
	Assert.assertEquals(game.returnMoney(), 0);
    }

    @Test(dataProvider = "getGame")
    public void gameShouldNotPlayMove(final BoardArcadeGame game) {
	Assert.assertFalse(game.playMove(RandomNumberGenerator.generate(0, 9)));
    }

    @Test(dataProvider = "getGame")
    public void gameShouldNotAcceptCoinsAtEndGame(final BoardArcadeGame game) {
	final int amount = RandomNumberGenerator.generate(0, 9);
	Assert.assertEquals(game.putCoins(amount), amount);
    }

    @Test(dataProvider = "getGame")
    public void gameShouldGoBackToStandByAfterEndIsCalled(final BoardArcadeGame game) {
	Assert.assertTrue(isStateCorrect(game, States.END_GAME));
	Assert.assertTrue(game.endGame());
	Assert.assertTrue(isStateCorrect(game, States.STAND_BY));
    }

}
