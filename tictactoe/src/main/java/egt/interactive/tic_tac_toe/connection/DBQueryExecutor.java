package egt.interactive.tic_tac_toe.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import egt.interactive.tic_tac_toe.game.GameOutcome;
import egt.interactive.tic_tac_toe.player_points.PlayerPoints;

public class DBQueryExecutor {

    private final DBCredentials credentials;

    public DBQueryExecutor() {
	this.credentials = new DBCredentials();
    }

    public boolean doesPlayerExist(final String name) {
	return this.getPlayerId(name) != null;
    }

    public void addPlayer(final String name) {
	final String selectStatement = "INSERT INTO players(name) VALUES (?)";

	try (final Connection connection = DBConnectionFactory.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(selectStatement);) {
	    stmt.setString(1, name);
	    stmt.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);

	}

    }

    public void addGameWithNoPlayer(final GameOutcome outcome) {
	final String timeStamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME
		.format(Instant.now().atZone(ZoneId.systemDefault()));
	final String insertStatement = "INSERT INTO games(date_time, outcome) VALUES (?,?)";

	try (final Connection connection = DBConnectionFactory.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(insertStatement);) {
	    stmt.setString(1, timeStamp);
	    stmt.setString(2, outcome.name());
	    stmt.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }

    public void addGameWithPlayer(final String playerName, final GameOutcome outcome) {

	final String timeStamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME
		.format(Instant.now().atZone(ZoneId.systemDefault()));
	final String playerId = this.getPlayerId(playerName);
	final String insertStatement = "INSERT INTO games(player_id, date_time, outcome) VALUES (?,?,?)";

	try (final Connection connection = DBConnectionFactory.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(insertStatement);) {
	    stmt.setString(1, playerId);
	    stmt.setString(2, timeStamp);
	    stmt.setString(3, outcome.name());
	    stmt.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }

    public List<PlayerPoints> getTopThreePlayers() {
	final List<PlayerPoints> players = new LinkedList<>();
	final String selectStatement = "SELECT count(*) as total_wins, p.name  FROM players as p "
		+ "JOIN games as g  ON p.id = g.player_id " + "WHERE g.outcome = 'PLAYER_WINS' GROUP BY g.player_id "
		+ "ORDER BY total_wins desc, p.name ASC limit 3";
	try (final Connection connection = DBConnectionFactory.getConnection(credentials);
		final Statement stmt = connection.createStatement();
		final ResultSet rs = stmt.executeQuery(selectStatement)) {

	    if (rs != null) {
		while (rs.next()) {
		    players.add(new PlayerPoints(rs.getString("name"), rs.getString("total_wins")));
		}
	    }
	    return players;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

    }

    private String getPlayerId(final String playerName) {
	final String selectStatement = "SELECT * FROM players WHERE name = ?;";
	try (final Connection connection = DBConnectionFactory.getConnection(credentials);
		final PreparedStatement stmt = connection.prepareStatement(selectStatement);) {
	    stmt.setString(1, playerName);
	    try (final ResultSet rs = stmt.executeQuery()) {
		if (rs.next()) {
		    return rs.getString("id");
		}
	    }
	    return null;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}