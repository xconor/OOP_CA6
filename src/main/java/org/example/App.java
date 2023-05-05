package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) throws DaoException {
        GameDAO gameDAO = new GameDAOImpl();

        System.out.println("\nCall getAllGames()");
        List<Game> games = gameDAO.getAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games");
        } else {
            for (Game game : games) {
                System.out.println("Game: " + game);
            }
        }

        System.out.println("\nCall getGameById(1)");
        Game game = gameDAO.getGameById(1);

        if (game == null) {
            System.out.println("Game not found");
        } else {
            System.out.println("Game: " + game);
        }
    }

}


