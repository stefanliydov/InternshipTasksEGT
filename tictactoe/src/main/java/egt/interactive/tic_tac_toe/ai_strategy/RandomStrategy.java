package egt.interactive.tic_tac_toe.ai_strategy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import egt.interactive.tic_tac_toe.board.Board;

public class RandomStrategy implements AiStrategy {

    @Override
    public Integer playAIMove(final Board board) {
	final List<Integer> freeFields = board.getFreeFields();
	final int size = freeFields.size();
	if (size == 0) {
	    return null;
	}
	return freeFields.get(ThreadLocalRandom.current().nextInt(0, size));
    }

}
