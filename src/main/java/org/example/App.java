package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        GameDAO IGameDao = new GameDAOImpl();

        try {
            System.out.println("\nCall getAllGames()");
            List<Game> games = IGameDao.getAllGames();     // call a method in the DAO

            if (games.isEmpty())
                System.out.println("There are no Games");
            else {
                for (Game game : games)
                    System.out.println("Game: " + game.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}