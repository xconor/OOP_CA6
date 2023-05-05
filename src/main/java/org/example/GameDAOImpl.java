package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements GameDAO {

    private MySqlDao mySqlDao; // declare the variable
    private MySqlDao dao;

    public GameDAOImpl() {
        this.mySqlDao = new MySqlDao(); // initialize the variable
        dao = new MySqlDao();
    }

    @Override
    public List<Game> getAllGames() throws DaoException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (Connection connection = dao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Game game = new Game(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getFloat("rating"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("developer"),
                        resultSet.getString("platform")
                );
                games.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("Error retrieving games: " + e.getMessage());
        }

        return games;
    }

    @Override
    public Game getGameById(int id) throws DaoException {
        String sql = "SELECT * FROM games WHERE id=?";
        try (Connection connection = dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Game game = new Game(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getFloat("rating"),
                            resultSet.getInt("release_year"),
                            resultSet.getString("developer"),
                            resultSet.getString("platform")
                    );
                    return game;
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Error retrieving game by id: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void addGame(Game game) throws DaoException {
        // Implementation of addGame() method goes here
    }

    @Override
    public void updateGame(Game game) throws DaoException {
        // Implementation of updateGame() method goes here
    }

    @Override
    public void deleteGame(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = mySqlDao.getConnection();
            String query = "DELETE FROM games WHERE id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error deleting game: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    mySqlDao.freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Error closing statement or connection: " + e.getMessage());
            }
        }
    }
}