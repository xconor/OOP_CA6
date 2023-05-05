package org.example;

import java.util.Comparator;
import java.util.List;

public interface GameDAO {

    List<Game> getAllGames() throws DaoException;

    Game getGameById(int id) throws DaoException;

    void deleteGame(int id) throws DaoException;

    Game addGame(Game game) throws DaoException;

    List<Game> findGamesUsingFilter(App.GameRatingComparator gameRatingComparator) throws DaoException;
}
