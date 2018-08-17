package egt.interactive.tic_tac_toe.resources;

import java.lang.reflect.Field;

import egt.interactive.tic_tac_toe.arcade.BoardArcadeGame;
import egt.interactive.tic_tac_toe.game.GameOutcome;
import egt.interactive.tic_tac_toe.game.States;
import egt.interactive.tic_tac_toe.game_utils.BoardGameUtils;

public class MyReflection {

    public static boolean isStateCorrect(final BoardArcadeGame game, final States targetState) {
	try {
	    final Field field = game.getClass().getDeclaredField("gameUtils");
	    field.setAccessible(true);
	    final BoardGameUtils gameUtils = (BoardGameUtils) field.get(game);
	    return gameUtils.getState().equals(targetState);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }

    public static boolean isOutcomeCorrect(final BoardArcadeGame game, final GameOutcome targetOutcome) {
	try {
	    final Field field = game.getClass().getDeclaredField("gameUtils");
	    field.setAccessible(true);
	    final BoardGameUtils gameUtils = (BoardGameUtils) field.get(game);
	    return gameUtils.getOutcome().equals(targetOutcome);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }
}
