package org.example;


import java.util.List;

public interface GameDAO {
    public List<Game> getAllGames();
    public Game getGameById(int id);
    public void addGame(Game game);
    public void updateGame(Game game);
    public void deleteGame(int id);
}

