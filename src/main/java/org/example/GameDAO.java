package org.example;


import java.sql.SQLException;
import java.util.List;

public interface GameDAO {
    public List<Game> getAllGames() throws DaoException;
    public Game getGameById(int id) throws DaoException;
    public void addGame(Game game) throws DaoException;
    public void updateGame(Game game) throws DaoException;
    public void deleteGame(int id) throws DaoException;
}



