package resources;

public enum ProductNames {
    CROISSANT("Croissant"), COCACOLA("Coca-Cola"), KITKAT("Kit-Kat"), SPRITE("Sprite"), SNICKERS("Snickers"), CHIPS(
	    "Chips"), GUMMYBEARS("Gummy Bears");

    private final String name;

    ProductNames(final String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
    }
}
