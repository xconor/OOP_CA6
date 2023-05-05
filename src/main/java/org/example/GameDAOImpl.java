package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements GameDAO {
    private MySqlDao dao = new MySqlDao();

    @Override
    public List<Game> getAllGames() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Game> games = new ArrayList<>();

        try {
            connection = dao.getConnection();
            String query = "SELECT * FROM games";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                float rating = resultSet.getFloat("rating");
                int releaseYear = resultSet.getInt("release_year");
                String developer = resultSet.getString("developer");
                String platform = resultSet.getString("platform");
                Game game = new Game(id, title, rating, releaseYear, developer, platform);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve all games: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dao.freeConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("Failed to close resources: " + e.getMessage());
            }
        }

        return games;
    }

    @Override
    public Game getGameById(int id) throws DaoException {
        // Implementation of getGameById() method goes here
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
        // Implementation of deleteGame() method goes here
    }
}