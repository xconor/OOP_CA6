package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameDAOImpl implements GameDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/games";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";

    @Override
    public List<Game> getAllGames() throws DaoException {
        List<Game> games = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM games")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                float rating = rs.getFloat("rating");

                Game game = new Game(id, title, rating);
                games.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("Error retrieving all games: " + e.getMessage());
        }

        return games;
    }

    @Override
    public Game getGameById(int id) throws DaoException {
        Game game = null;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM games WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    float rating = rs.getFloat("rating");

                    game = new Game(id, title, rating);
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Error retrieving game with ID " + id + ": " + e.getMessage());
        }

        return game;
    }

    @Override
    public void deleteGame(int id) throws DaoException {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM games WHERE id = ?")) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("Game with ID " + id + " not found");
            }

        } catch (SQLException e) {
            throw new DaoException("Error deleting game with ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public Game addGame(Game game) throws DaoException {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO games (title, rating) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, game.getTitle());
            stmt.setFloat(2, game.getRating());
            stmt.setInt(3, game.getYear());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("Error adding game: no rows affected");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    game.setId(id);
                } else {
                    throw new DaoException("Error adding game: no ID obtained");
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Error adding game: " + e.getMessage());
        }
        return game;
    }

    @Override
    public List<Game> findGamesUsingFilter(App.GameRatingComparator gameRatingComparator) throws DaoException {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM games");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                float rating = rs.getFloat("rating");

                Game game = new Game(id, title, rating);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving games: " + e.getMessage());
        }

        games.sort(gameRatingComparator);
        return games;
    }

}