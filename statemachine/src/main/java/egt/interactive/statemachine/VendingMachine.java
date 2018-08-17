package egt.interactive.statemachine;

import egt.interactive.statemachine.inventory.Inventory;
import egt.interactive.statemachine.product.Product;
import egt.interactive.statemachine.product.ProductQuantity;
import egt.interactive.writer.Writer;

public final class VendingMachine {
    private final static String NOT_ENOUGH_MONEY_MESSAGE = "You do not have enough money for that item! Please add more coins or choose a different item!";
    private final static String TAKE_ITEM_MESSAGE = "Please take your item!";
    private final static String SUCCESSFULLY_ADDED_PRODUCT_MESSAGE = "Product added succesfully!";
    private final static String INVENTORY_FULL_MESSAGE = "Inventory is full! Please make space and then add new item";
    private final Inventory inventory;
    private final Writer writer;
    private States state;
    private long totalMoneyCollected;
    private long coins;

    public VendingMachine(final int size, final Writer writer) {
	this.state = States.STAND_BY;
	this.totalMoneyCollected = 0L;
	this.inventory = new Inventory(size);
	this.coins = 0L;
	this.writer = writer;
    }

    // Machine function methods
    public long putCoins(final long coin) {
	if (!isMoneyValid(coin)) {
	    return coins;
	}
	this.state.putCoins(this, coin);
	return this.coins;
    }

    public Product selectItem(final String name) {
	return this.state.selectItem(this, name) ? this.inventory.getProduct(name).getProduct() : null;
    }

    public boolean takeItem() {
	return state.takeItem(this);

    }

    public Long service() {
	return state.service(this) ? returnMoneyToCustomer() : null;

    }

    public Long returnMoney() {
	return state.returnMoney(this) ? this.returnMoneyToCustomer() : null;
    }

    public boolean fixMachine() {
	return this.state.fixMachine(this);
    }

    public int getItemsCount() {
	return inventory.getItemsCount();
    }

    public Long getItemPrice(final String name) {
	ProductQuantity prQ = inventory.getProduct(name);
	return prQ != null ? prQ.getProduct().getPrice() : null;
    }

    public long getTotalMoney() {
	return this.totalMoneyCollected;
    }

    public long getCoins() {
	return this.coins;
    }

    public boolean isMoneyValid(final long coin) {
	return coin > 0;

    }

    public int getInventorySize() {
	return this.inventory.getSize();
    }

    public Integer getProductQuantity(final String name) {
	return inventory.getProductQuantity(name);
    }

    public boolean addProduct(final String name, final long price, final int quantity) {
	return (this.state.addProduct(this, name, price, quantity));

    }

    void takeCustomersMoney(final Product product) {
	this.coins -= product.getPrice();
	this.totalMoneyCollected += product.getPrice();
    }

    void makeItem(final Product product) {
	inventory.buyOne(product.getName());
	writer.write(TAKE_ITEM_MESSAGE);
    }

    void setState(final States state) {
	this.state = state;

    }

    long returnMoneyToCustomer() {
	final long moneyForReturn = this.coins;
	this.coins = 0L;
	return moneyForReturn;
    }

    boolean checkIfEnoughQuantityAndMoney(final Product product) {
	// Show messages depending on what's wrong and return to selecting state
	if (product.getPrice() > this.coins) {
	    writer.write(NOT_ENOUGH_MONEY_MESSAGE);
	    return false;
	} else if (inventory.getProductQuantity(product.getName()) <= 0) {
	    writer.write(String.format("Machine is all out of %s. Please choose a different item!", product.getName()));
	    return false;
	}
	return true;
    }

    boolean hasMoneyLeft() {
	return coins > 0;
    }

    void addCoinsToMachine(final long money) {
	coins += money;
    }

    ProductQuantity getProduct(final String name) {
	return inventory.getProduct(name);
    }

    void printItemAdded() {
	writer.write(SUCCESSFULLY_ADDED_PRODUCT_MESSAGE);
    }

    void printItemNotAdded() {
	writer.write(INVENTORY_FULL_MESSAGE);

    }

    Writer getWriter() {
	return this.writer;
    }

    Inventory getInventory() {
	return this.inventory;
    }

    public String getInfo() {
	return new StringBuilder().append(String.format("Coins put: %.2f", Double.valueOf(this.coins) / 100))
		.append(System.lineSeparator()).append(this.inventory.getInfo()).toString();
    }

}
