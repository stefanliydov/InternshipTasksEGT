package statemachine;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.statemachine.VendingMachine;

public class AddProductTests {

    private static final String NAME = UUID.randomUUID().toString();

    @DataProvider(name = "getFullMachineInService")
    public Object[][] getData() {
	return resources.MyDataProvider.getFullMachineInServiceDataProvider();
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addItemActuallyAddsItem(final VendingMachine machine) {
	final int oldSize = machine.getItemsCount();
	machine.addProduct(NAME, ThreadLocalRandom.current().nextLong(50, 400),
		ThreadLocalRandom.current().nextInt(2, 10));
	final int result = machine.getItemsCount();
	Assert.assertEquals(result, oldSize + 1);
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addMaxItemsShouldStopAddingItems(final VendingMachine machine) {
	while (true) {
	    if (!machine.addProduct(String.valueOf(ThreadLocalRandom.current().nextInt()),
		    ThreadLocalRandom.current().nextLong(50, 400), ThreadLocalRandom.current().nextInt(2, 10))) {
		break;
	    }
	}
	Assert.assertEquals(machine.getItemsCount(), machine.getInventorySize());
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addingProductWithNegativePriceShouldReturnFalse(final VendingMachine machine) {
	Assert.assertFalse(machine.addProduct(NAME, ThreadLocalRandom.current().nextLong(-200, 0), 6));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addingProductWithNegativeQuantityShouldReturnFalse(final VendingMachine machine) {
	Assert.assertFalse(machine.addProduct(NAME, 2L, ThreadLocalRandom.current().nextInt(-200, -1)));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addingProductWithZeroQuantityShouldReturnFalse(final VendingMachine machine) {
	Assert.assertFalse(machine.addProduct(NAME, 2L, 0));
    }

    @Test(dataProvider = "getFullMachineInService")
    public void addSameProductManyTimesShouldIncreaseSize(final VendingMachine machine) {
	int expectedQuantity = 0;
	final int count = ThreadLocalRandom.current().nextInt(50, 100);
	for (int i = 0; i < count; i++) {
	    final int amount = ThreadLocalRandom.current().nextInt(2, 10);
	    machine.addProduct(NAME, 10l, amount);
	    expectedQuantity += amount;
	}
	final int actualQuantity = machine.getProductQuantity(NAME);

	Assert.assertEquals(actualQuantity, expectedQuantity);
    }

}
