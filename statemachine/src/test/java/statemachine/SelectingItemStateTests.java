package statemachine;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.statemachine.States;
import egt.interactive.statemachine.VendingMachine;
import egt.interactive.statemachine.product.Product;
import resources.MyReflection;
import resources.ProductNames;

public class SelectingItemStateTests {
    private static final long MONEY_TO_ADD = ThreadLocalRandom.current().nextLong(1000, 2000);
    private static final String NAME = UUID.randomUUID().toString();

    @DataProvider(name = "getFullMachine")
    public Object[][] getData() {
	return resources.MyDataProvider.getFullMachineDataProvider();
    }
 
    @Test(dataProvider = "getFullMachine")
    public void machineShouldNotGiveItem(final VendingMachine machine) {
	Assert.assertFalse(machine.takeItem());
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldGoToServiceAndReturnMoney(final VendingMachine machine) {
	machine.putCoins(MONEY_TO_ADD);
	Assert.assertEquals(machine.getCoins(), MONEY_TO_ADD);
	Assert.assertEquals(machine.service(), Long.valueOf(MONEY_TO_ADD));
	Assert.assertEquals(machine.getCoins(), 0L);
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldAcceptCoins(final VendingMachine machine) {
	final long expectedAmount = machine.putCoins(ThreadLocalRandom.current().nextLong(1000, 2000));
	final long coins = machine.getCoins();
	Assert.assertEquals(coins, expectedAmount);
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldAcceptCoinsAndNotChangeState(final VendingMachine machine) {
	machine.putCoins(MONEY_TO_ADD);
	machine.putCoins(ThreadLocalRandom.current().nextLong(1000, 2000));
	Assert.assertTrue(MyReflection.isStateCorrect(machine, States.SELECTING_ITEM));
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldLetYouSelectItemIfMoneyIsEnough(final VendingMachine machine) {
	machine.putCoins(MONEY_TO_ADD);
	Assert.assertNotNull(machine.selectItem(
		ProductNames.values()[ThreadLocalRandom.current().nextInt(ProductNames.values().length)].getName()));
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldNotLetYouSelectItemIfMoneyIsNotEnough(final VendingMachine machine) {
	machine.returnMoney();
	machine.putCoins(ThreadLocalRandom.current().nextLong(1, 5));
	Assert.assertNull(machine.selectItem(
		ProductNames.values()[ThreadLocalRandom.current().nextInt(ProductNames.values().length)].getName()));
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldNotLetYouSelectItemIfThereIsntEnoughQuantity(final VendingMachine machine) {
	machine.returnMoney();
	machine.service();
	machine.addProduct(NAME, ThreadLocalRandom.current().nextLong(20, 400), 1);
	Assert.assertTrue(machine.fixMachine());
	final long amount = ThreadLocalRandom.current().nextLong(500, 1000);
	Assert.assertEquals(machine.putCoins(amount), amount);
	Assert.assertNotNull(machine.selectItem(NAME));
	Assert.assertNotNull(machine.takeItem());
	Assert.assertNull(machine.selectItem(NAME));
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldNotLetYouAddProductsWhileItsWorking(final VendingMachine machine) {
	Assert.assertFalse(machine.addProduct(NAME, 10L, 1));
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldSelectItemAndThenTakeTheMoneyForIt(final VendingMachine machine) {
	machine.putCoins(MONEY_TO_ADD);
	long moneyLeft = machine.getCoins();
	final String productName = ProductNames.values()[ThreadLocalRandom.current()
		.nextInt(ProductNames.values().length)].getName();
	machine.selectItem(productName);
	moneyLeft -= machine.getItemPrice(productName);
	final long coins = machine.getCoins();

	Assert.assertEquals(coins, moneyLeft);
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldSelectItemSuccesfullyAndThenReduceItemQuantity(final VendingMachine machine) {
	machine.putCoins(MONEY_TO_ADD);
	final String productName = ProductNames.values()[ThreadLocalRandom.current()
		.nextInt(ProductNames.values().length)].getName();

	final int expectedQuantity = machine.getProductQuantity(productName) - 1;

	final Product product = machine.selectItem(productName);
	Assert.assertNotNull(product);
	final int actualQuantity = machine.getProductQuantity(productName);

	Assert.assertEquals(actualQuantity, expectedQuantity);
    }

    @Test(dataProvider = "getFullMachine")
    public void machineShouldNotNeedFixing(final VendingMachine machine) {
	Assert.assertFalse(machine.fixMachine());
    }
}
