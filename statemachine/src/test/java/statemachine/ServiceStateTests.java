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

public class ServiceStateTests {
    private static final String NAME = UUID.randomUUID().toString();

    @DataProvider(name = "getFullMachineInService")
    public Object[][] getData() {
	return resources.MyDataProvider.getFullMachineInServiceDataProvider();
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldNotAcceptCoins(final VendingMachine machine) {
	final long money = ThreadLocalRandom.current().nextLong(500, 1000);
	Assert.assertEquals(machine.putCoins(money), 0L);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldNotSelectItem(final VendingMachine machine) {
	final String productName = ProductNames.values()[ThreadLocalRandom.current()
		.nextInt(ProductNames.values().length)].getName();
	Assert.assertNull(machine.selectItem(productName));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldNotGiveItem(final VendingMachine machine) {
	Assert.assertFalse(machine.takeItem());
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldNotReturnMoney(final VendingMachine machine) {
	Assert.assertNull(null);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldLetYouAddProductsWhileItsWorking(final VendingMachine machine) {
	Assert.assertTrue(machine.addProduct(NAME, 10L, 1));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldAcceptServiceAndReturnZeroCoins(final VendingMachine machine) {
	Assert.assertEquals(machine.service(), null);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldGoBackToStandByAfterGettingFixed(final VendingMachine machine) {
	machine.fixMachine();

	Assert.assertTrue(MyReflection.isStateCorrect(machine, States.STAND_BY));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void machineShouldNotGoToServiceIfAlreadyInService(final VendingMachine machine) {
	Assert.assertEquals(machine.service(), null);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void getItemPriceTest(final VendingMachine machine) throws Exception {
	final Long price = machine.getItemPrice(UUID.randomUUID().toString());
	Assert.assertNull(price);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void getProductQuantityTest(final VendingMachine machine) throws Exception {
	final Integer quantity = machine.getProductQuantity(UUID.randomUUID().toString());
	Assert.assertNull(quantity);
    }
}
