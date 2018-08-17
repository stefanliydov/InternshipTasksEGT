package egt.interactive.tic_tac_toe.board;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard implements Board {

    private static final int SIZE = 3;
    private static final int SQUARE_SIZE = 9;
    private final String[][] fields;
    private final List<Integer> freeFields;

    public TicTacToeBoard() {
	this.fields = new String[SIZE][SIZE];
	this.freeFields = new ArrayList<>();
	this.loadFields();
    }

    private void loadFields() {
	for (int i = 0; i < SQUARE_SIZE; i++) {
	    this.freeFields.add(i);
	}
	for (int i = 0; i < fields.length; i++) {
	    for (int j = 0; j < fields.length; j++) {
		fields[i][j] = " ";
	    }
	}
    }

    @Override
    public String getField(final int x) {
	return this.fields[x / SIZE][x % SIZE];
    }

    @Override
    public String[][] getAllFields() {
	return this.fields;
    }

    @Override
    public boolean addMove(final int num, final String sign) {
	final int x = num / SIZE;
	final int y = num % SIZE;

	this.fields[x][y] = sign;
	this.freeFields.remove(Integer.valueOf(num));
	return true;

    }

    @Override
    public List<Integer> getFreeFields() {
	return this.freeFields;
    }

    @Override
    public boolean isFull() {
	return this.freeFields.size() == 0;
    }

    @Override
    public boolean isFieldFree(final int move) {
	return this.getField(move).equals(" ");
    }

    @Override
    public boolean isMoveValid(final int move) {
	return move >= 0 && move < SQUARE_SIZE;
    }

    @Override
    public int getSize() {
	return SIZE;
    }

}
