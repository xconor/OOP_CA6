package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {

    private static GameDAO gameDao = new GameDAOImpl();

    public static void main(String[] args) throws DaoException {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        int menuItem;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Get all games");
            System.out.println("2. Get game by ID");
            System.out.println("3. Delete game by ID");
            System.out.println("4. Add new game");
            System.out.println("5. List games by rating");
            System.out.println("10. Quit");

            System.out.print("Enter menu item: ");
            menuItem = scanner.nextInt();

            switch (menuItem) {
                case 1:
                    System.out.println("\nAll games:");
                    List<Game> games = gameDao.getAllGames();

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
                        Game game = gameDao.getGameById(id);

                        if (game == null)
                            System.out.println("Game with ID " + id + " not found");
                        else
                            System.out.println("\nGame with ID " + id + ": " + game.toString());
                    } catch (DaoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    System.out.print("Enter the id of the game to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        gameDao.deleteGame(deleteId);
                        System.out.println("Game with id " + deleteId + " deleted successfully.");
                    } catch (DaoException e) {
                        System.out.println("Error deleting game: " + e.getMessage());
                    }
                    break;

                case 4:
                    Game newGame = new Game();
                    System.out.print("Enter the title of the game: ");
                    String title = scanner.next();
                    newGame.setTitle(title);

                    System.out.print("Enter the rating of the game: ");
                    float rating = scanner.nextFloat();
                    newGame.setRating(rating);

                    try {
                        gameDao.addGame(newGame);
                        System.out.println("New game added with ID " + newGame.getId());
                    } catch (DaoException e) {
                        System.out.println("Error adding game: " + e.getMessage());
                    }
                    break;


                case 5:
                    System.out.println("\nGames by rating:");
                    List<Game> gamesByRating = gameDao.findGamesUsingFilter(new GameRatingComparator());
                    if (gamesByRating.isEmpty())
                        System.out.println("There are no games");
                    else {
                        for (Game game : gamesByRating)
                            System.out.println("Game: " + game.toString());
                    }
                    break;

                case 10:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid menu item. Please try again.");
            }
        } while (!quit);

        scanner.close();
    }

    static class GameRatingComparator implements Comparator<Game> {
        @Override
        public int compare(Game game1, Game game2) {
            return Float.compare(game2.getRating(), game1.getRating());
        }
    }
}
