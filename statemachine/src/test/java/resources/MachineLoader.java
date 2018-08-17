package resources;

import java.util.concurrent.ThreadLocalRandom;

import egt.interactive.statemachine.VendingMachine;

public class MachineLoader {
    public static void loadMachine(final VendingMachine machine) {
	machine.service();
	final ProductNames[] names = ProductNames.values();

	for (int i = 0; i < ProductNames.values().length; i++) {
	    machine.addProduct(names[i].getName(), ThreadLocalRandom.current().nextLong(50, 400),
		    ThreadLocalRandom.current().nextInt(10, 20));
	}
	machine.fixMachine();
    }
}
