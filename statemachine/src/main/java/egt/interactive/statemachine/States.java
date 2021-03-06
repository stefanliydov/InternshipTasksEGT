package egt.interactive.statemachine;

import java.util.Objects;

import egt.interactive.statemachine.product.Product;
import egt.interactive.statemachine.product.ProductQuantity;

public enum States {
    STAND_BY {
	/*
	 * The machine is ready for customers!
	 */
	@Override
	boolean putCoins(final VendingMachine machine, final long coin) {
	    machine.addCoinsToMachine(coin);
	    machine.getWriter().write(machine.getInfo());
	    machine.setState(States.SELECTING_ITEM);
	    return true;
	}

	@Override
	boolean selectItem(final VendingMachine machine, final String name) {
	    return false;
	}

	@Override
	boolean takeItem(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean service(final VendingMachine machine) {
	    machine.setState(States.SERVICE);
	    return true;
	}

	@Override
	boolean returnMoney(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean addProduct(final VendingMachine machine, final String name, final long price, final int quantity) {
	    return false;
	}

	@Override
	boolean fixMachine(final VendingMachine machine) {
	    return false;
	}

    },

    SELECTING_ITEM {
	/*
	 * Coins have been put in machine and is now ready to select and prepare
	 * item for customer
	 */

	@Override
	boolean putCoins(final VendingMachine machine, final long coin) {
	    machine.addCoinsToMachine(coin);
	    machine.getWriter().write(machine.getInfo());
	    return true;
	}

	@Override
	boolean selectItem(final VendingMachine machine, final String name) {
	    ProductQuantity productQ = machine.getInventory().getProduct(name);
	    if (Objects.isNull(productQ)) {
		machine.getWriter()
			.write(String.format("Item with name: %s is invalid, please pick a valid name!", name));
		return false;
	    }
	    Product product = productQ.getProduct();
	    if (machine.checkIfEnoughQuantityAndMoney(product)) {
		machine.takeCustomersMoney(product);
		machine.makeItem(product);
		machine.setState(States.TAKE_ITEM);
		return true;
	    }
	    machine.setState(States.SELECTING_ITEM);
	    return false;
	}

	@Override
	boolean takeItem(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean service(final VendingMachine machine) {
	    machine.setState(States.SERVICE);
	    return true;

	}

	@Override
	boolean returnMoney(final VendingMachine machine) {
	    // Returns all customer coins in machine and returns back to
	    // STAND_BY
	    machine.setState(States.STAND_BY);
	    machine.getInventory().reArrangeItems();
	    return true;
	}

	@Override
	boolean addProduct(final VendingMachine machine, final String name, final long price, final int quantity) {
	    return false;
	}

	@Override
	boolean fixMachine(final VendingMachine machine) {
	    return false;
	}

    },

    TAKE_ITEM {
	/*
	 * Machine has prepared item for customer successfully and is now
	 * waiting for item to be picked by customer
	 */
	@Override
	boolean putCoins(final VendingMachine machine, final long coins) {
	    return false;
	}

	@Override
	boolean selectItem(final VendingMachine machine, final String name) {
	    return false;
	}

	@Override
	boolean takeItem(final VendingMachine machine) {
	    /*
	     * Checks if there are coins left in machine, if yes return to
	     * select menu and give option to continue buying, else go back to
	     * STAND_BY
	     */
	    if (machine.hasMoneyLeft()) {
		machine.setState(States.SELECTING_ITEM);
		machine.getWriter().write(machine.getInfo());
	    } else {
		machine.setState(States.STAND_BY);
	    }
	    machine.getInventory().reArrangeItems();
	    return true;
	}

	@Override
	boolean service(final VendingMachine machine) {
	    machine.setState(States.SERVICE);
	    return true;
	}

	@Override
	boolean returnMoney(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean addProduct(final VendingMachine machine, final String name, final long price, final int quantity) {
	    return false;
	}

	@Override
	boolean fixMachine(final VendingMachine machine) {
	    return false;
	}

    },

    SERVICE {
	/*
	 * A fatal error has occurred while the machine was working... Machine
	 * will now be out of service until fixed! When fixed it should return
	 * back to STAND_BY state. Machine should return any money put by a
	 * customer when error occurred!
	 */

	@Override
	boolean putCoins(final VendingMachine machine, final long coins) {
	    return false;
	}

	@Override
	boolean selectItem(final VendingMachine machine, final String name) {
	    return false;
	}

	@Override
	boolean takeItem(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean service(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean returnMoney(final VendingMachine machine) {
	    return false;
	}

	@Override
	boolean addProduct(final VendingMachine machine, final String name, final long price, final int quantity) {
	    Product product = new Product(name, price);
	    if (machine.getInventory().addProduct(new ProductQuantity(product, quantity))) {
		machine.printItemAdded();
		return true;
	    }
	    machine.printItemNotAdded();
	    return false;
	}

	@Override
	boolean fixMachine(final VendingMachine machine) {
	    machine.setState(STAND_BY);
	    return true;
	}

    };

    abstract boolean putCoins(final VendingMachine machine, final long coins);

    abstract boolean selectItem(final VendingMachine machine, final String name);

    abstract boolean takeItem(final VendingMachine machine);

    abstract boolean service(final VendingMachine machine);

    abstract boolean returnMoney(final VendingMachine machine);

    abstract boolean addProduct(final VendingMachine machine, final String name, final long price, final int quantity);

    abstract boolean fixMachine(final VendingMachine machine);
}
