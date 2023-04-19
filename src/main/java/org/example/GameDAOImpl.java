package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl extends MySqlDao implements GameDAO {
    private Connection conn;

    public GameDAOImpl() {
        this.conn = conn;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                float rating = rs.getFloat("rating");
                int releaseYear = rs.getInt("release_year");
                String developer = rs.getString("developer");
                String platform = rs.getString("platform");
                games.add(new Game(id, title, rating, releaseYear, developer, platform));
            }
        } catch (SQLException ex) {
            // handle exception
        }
        return games;
    }

    @Override
    public Game getGameById(int id) {
        Game game = null;
        String sql = "SELECT * FROM games WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    float rating = rs.getFloat("rating");
                    int releaseYear = rs.getInt("release_year");
                    String developer = rs.getString("developer");
                    String platform = rs.getString("platform");
                    game = new Game(id, title, rating, releaseYear, developer, platform);
                }
            }
        } catch (SQLException ex) {
            // handle exception
        }
        return game;
    }

    @Override
    public void addGame(Game game) {
        String sql = "INSERT INTO games (title, rating, release_year, developer, platform) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getTitle());
            stmt.setFloat(2, game.getRating());
            stmt.setInt(3, game.getReleaseYear());
            stmt.setString(4, game.getDeveloper());
            stmt.setString(5, game.getPlatform());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    @Override
    public void updateGame(Game game) {
        String sql = "UPDATE games SET title = ?, rating = ?, release_year = ?, developer = ?, platform = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getTitle());
            stmt.setFloat(2, game.getRating());
            stmt.setInt(3, game.getReleaseYear());
            stmt.setString(4, game.getDeveloper());
            stmt.setString(5, game.getPlatform());
            stmt.setInt(6, game.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    @Override
    public void deleteGame(int id) {
        String sql = "DELETE FROM games WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }
}
