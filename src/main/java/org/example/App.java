package org.example;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DaoException {
        GameDAO IGameDao = new GameDAOImpl();
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        int menuItem;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Get all games");
            System.out.println("2. Get game by ID");
            System.out.println("3. Quit");

            System.out.print("Enter menu item: ");
            menuItem = scanner.nextInt();

            switch (menuItem) {
                case 1:
                    System.out.println("\nAll games:");
                    List<Game> games = IGameDao.getAllGames();

                    if (games.isEmpty())
                        System.out.println("There are no games");
                    else {
                        for (Game game : games)
                            System.out.println("Game: " + game.toString());
                    }

                    break;

                case 2:
                    System.out.print("\nEnter game ID: ");
                    int id = scanner.nextInt();

                    try {
                        Game game = IGameDao.getGameById(id);

                        if (game == null)
                            System.out.println("Game with ID " + id + " not found");
                        else
                            System.out.println("\nGame with ID " + id + ": " + game.toString());
                    } catch (DaoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid menu item. Please try again.");
            }
        } while (!quit);

        scanner.close();
    }
}


