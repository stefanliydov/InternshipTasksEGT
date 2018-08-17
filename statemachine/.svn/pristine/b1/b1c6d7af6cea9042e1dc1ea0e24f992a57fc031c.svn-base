package statemachine;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.statemachine.States;
import egt.interactive.statemachine.VendingMachine;
import resources.MyReflection;
import resources.ProductNames;

public class StandByStateTests {
    private static final String NAME = UUID.randomUUID().toString();

    @DataProvider(name = "getEmptyMachine")
    public Object[][] getData() {
	return resources.MyDataProvider.getEmptyMachineDataProvider();
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldNotReturnMoney(final VendingMachine machine) {
	Assert.assertNull(machine.returnMoney());
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldEnterServiceIfNeedAndReturnZeroCoins(final VendingMachine machine) {
	Assert.assertEquals(machine.service(), Long.valueOf(0));
	Assert.assertTrue(MyReflection.isStateCorrect(machine, States.SERVICE));
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldNotSelectItem(final VendingMachine machine) {
	final String productName = ProductNames.values()[ThreadLocalRandom.current()
		.nextInt(ProductNames.values().length)].getName();
	Assert.assertNull(machine.selectItem(productName));
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldNotGiveItem(final VendingMachine machine) {
	Assert.assertFalse(machine.takeItem());
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldTakeCoins(final VendingMachine machine) {
	final long money = ThreadLocalRandom.current().nextLong(500, 1000);
	Assert.assertEquals(machine.putCoins(money), money);
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineShouldNotAddNewItem(final VendingMachine machine) {

	Assert.assertFalse(machine.addProduct(NAME, ThreadLocalRandom.current().nextLong(100, 150),
		ThreadLocalRandom.current().nextInt(100, 150)));
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineInStandByShouldChangeStateAfterPutCoins(final VendingMachine machine) {

	machine.putCoins(ThreadLocalRandom.current().nextLong(500, 1000));

	Assert.assertTrue(MyReflection.isStateCorrect(machine, States.SELECTING_ITEM));
    }

    @Test(dataProvider = "getEmptyMachine")
    public void machineShouldNotNeedFixing(final VendingMachine machine) {
	Assert.assertFalse(machine.fixMachine());
    }
}
