package resources;

import machinefactory.VendingMachineFactory;

public class MyDataProvider {

    public static Object[][] getFullMachineDataProvider() {
	return new Object[][] { { VendingMachineFactory.createFullVendingMachine() } };
    }

    public static Object[][] getFullMachineInServiceDataProvider() {
	return new Object[][] { { VendingMachineFactory.createFullVendingMachineInService() } };
    }

    public static Object[][] getEmptyMachineDataProvider() {
	return new Object[][] { { VendingMachineFactory.createEmptyVendingMachine() } };
    }

    public static Object[][] getFullMachineWithMoneyAndSelectedProductDataProvider() {
	return new Object[][] { { VendingMachineFactory.createFullVendingMachineWithMoneyAndSelectedProduct() } };
    }

}
