package egt.interactive.statemachine.product;

public class Product {

    private final String name;
    private final long price;

    public Product(final String name, final long price) {
	this.name = name;
	this.price = price;

    }

    public String getName() {
	return this.name;
    }

    public Long getPrice() {
	return this.price;
    }
}
