package egt.interactive.statemachine.product;

public class ProductQuantity {

    private final Product product;
    private int quantity;

    public ProductQuantity(final Product product, final int quantity) {
	this.product = product;
	this.quantity = quantity;
    }

    public int getQuantity() {
	return quantity;
    }

    public Product getProduct() {
	return product;
    }

    public void buyOne() {
	this.quantity--;
    }

    public ProductQuantity addQuantity(final int quantity) {
	this.quantity += quantity;
	return this;
    }

    public String getInfo() {
	return new StringBuilder().append(". Name: ").append(product.getName()).append(", price: ")
		.append(String.format("%.2f", Double.valueOf(product.getPrice() / 100))).append("$, quantity left: ")
		.append(quantity).toString();
    }
}
