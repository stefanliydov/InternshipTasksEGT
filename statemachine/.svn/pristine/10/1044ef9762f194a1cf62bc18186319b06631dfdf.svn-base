package egt.interactive.statemachine.inventory;

import java.util.HashMap;
import java.util.Map;

import egt.interactive.statemachine.product.Product;
import egt.interactive.statemachine.product.ProductQuantity;

public final class Inventory {
    private final int INVENTORY_SIZE;
    private final Map<String, ProductQuantity> products;

    public Inventory(int size) {
	this.products = new HashMap<>();
	this.INVENTORY_SIZE = size;
    }

    public boolean addProduct(ProductQuantity productQuantity) {
	Product product = productQuantity.getProduct();
	if (product.getPrice() <= 0 || productQuantity.getQuantity() <= 0) {
	    return false;
	}

	if (this.products.size() == INVENTORY_SIZE) {
	    return false;
	}

	final String productName = product.getName();
	products.compute(productName,
		(k, v) -> v == null ? productQuantity : v.addQuantity(productQuantity.getQuantity()));
	return true;

    }

    public ProductQuantity getProduct(final String name) {
	return this.products.get(name);
    }

    public void reArrangeItems() {
	this.products.entrySet().removeIf(e -> e.getValue().getQuantity() <= 0);
    }

    public int getItemsCount() {
	return this.products.size();
    }

    public void buyOne(String name) {
	products.get(name).buyOne();

    }

    public Integer getProductQuantity(String name) {
	ProductQuantity prQ = this.products.get(name);
	return prQ != null ? prQ.getQuantity() : null;
    }

    public String getInfo() {
	final StringBuilder sb = new StringBuilder();
	sb.append("Items available: ").append(System.lineSeparator());
	int count = 1;
	for (ProductQuantity entry : products.values()) {
	    sb.append(count++).append(entry.getInfo()).append(System.lineSeparator());
	}
	sb.append("Select the item you want by pressing the name of the product!").append(System.lineSeparator());
	return sb.toString();
    }

    public int getSize() {
	return this.INVENTORY_SIZE;
    }

}
