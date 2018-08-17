package egt.interactive.tic_tac_toe;

import static egt.interactive.tic_tac_toe.resources.MyReflection.isStateCorrect;
import static egt.interactive.tic_tac_toe.resources.RandomNumberGenerator.generate;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.resources.MyDataProvider;

public class ReturnMoneyTests {

    private static final int TARGET_MONEY = 10;

    @DataProvider(name = "getGame")
    public Object[][] getData() {
	return MyDataProvider.getNewTicTacToeGame();
    }

    @Test(dataProvider = "getGame")
    public void machineShouldReturnMoneyIfCustomersBacksOff(final BoardArcadeGame game) {
	final int amount = generate(1, 9);

	game.putCoins(amount);
	final int actualAmount = game.returnMoney();
	assertEquals(actualAmount, amount);
	Assert.assertTrue(isStateCorrect(game, States.STAND_BY));

    }

    @Test(dataProvider = "getGame")
    public void machineShouldNotReturnMoneyIfGameHasStarted(final BoardArcadeGame game) {
	game.putCoins(TARGET_MONEY);
	Assert.assertEquals(game.returnMoney(), 0);
    }

    @Test(dataProvider = "getGame")
    public void machineShouldNotReturnMoneyAfterGameHasStartedEvenIfExtraMoneyIsPassed(final BoardArcadeGame game) {
	final int amount = generate(20, 100);
	final int extraMoneyReturned = game.putCoins(amount);
	Assert.assertEquals(extraMoneyReturned, (amount - TARGET_MONEY));
	Assert.assertEquals(game.returnMoney(), 0);
    }

}
