package egt.interactive.tic_tac_toe.game_messages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameMessages {

    private static final String MESSAGES_PATH = "src/main/resources/messages.properties";

    private final String PLAYER_SIGN;
    private final String AI_SIGN;
    private final String INVALID_MOVE;
    private final String PLAYER_VICTORY_MESSAGE;
    private final String AI_VICTORY_MESSAGE;
    private final String DRAW_MESSAGE;
    private final String WRONG_NAME_MESSAGE;

    public GameMessages() {
	Properties loadMessages = loadMessages();
	this.PLAYER_SIGN = loadMessages.getProperty("playersign");
	this.AI_SIGN = loadMessages.getProperty("aisign");
	this.INVALID_MOVE = loadMessages.getProperty("invalidmove");
	this.PLAYER_VICTORY_MESSAGE = loadMessages.getProperty("playervictory");
	this.AI_VICTORY_MESSAGE = loadMessages.getProperty("aivictory");
	this.DRAW_MESSAGE = loadMessages.getProperty("draw");
	this.WRONG_NAME_MESSAGE = loadMessages.getProperty("wrong_name");
    }

    private Properties loadMessages() {
	final Properties prop = new Properties();

	try (InputStream input = new FileInputStream(MESSAGES_PATH);) {
	    prop.load(input);
	    return prop;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

    }

    public String getPlayerSign() {
	return this.PLAYER_SIGN;
    }

    public String getAiSign() {
	return this.AI_SIGN;
    }

    public String getInvalidMoveMessage() {
	return this.INVALID_MOVE;
    }

    public String getDrawMessage() {
	return this.DRAW_MESSAGE;

    }

    public String getAiVictoryMessage() {
	return this.AI_VICTORY_MESSAGE;

    }

    public String getPlayerVictoryMessage() {
	return this.PLAYER_VICTORY_MESSAGE;

    }

    public String getWrongNameMessage() {
	return this.WRONG_NAME_MESSAGE;
    }
}
