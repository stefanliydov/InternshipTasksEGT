package egt.interactive.tic_tac_toe.player_points;

public class PlayerPoints {

    private final String name;
    private final String points;

    public PlayerPoints(final String name, final String points) {
	this.name = name;
	this.points = points;
    }

    public String getName() {
	return this.name;
    }

    public String getPoints() {
	return this.points;
    }

}
