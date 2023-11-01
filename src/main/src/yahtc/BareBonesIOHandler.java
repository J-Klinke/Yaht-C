package yahtc;

import java.util.ArrayList;
import java.util.Scanner;

public class BareBonesIOHandler implements IOHandler {
    private Scanner scanner = new Scanner(System.in);


    /**
     * handles a player's boolean decision.
     * @return the decision
     */
    @Override
    public boolean rollAgain() {
        System.out.println("Do you want to roll the dice again?");
        System.out.println("Type Yes or No.");
        while (true) {
            String input = scanner.next();
            switch (input.toLowerCase()) {
                case "yes", "y" -> {
                    return true;
                }
                case "no", "n" -> {
                    return false;
                }
                default -> System.out.println("Try again.");
            }
        }
    }

    /**
     * allows a player to pick dice.
     * @param dice the Die[] to pick from
     * @return a Die[] of the picked dice
     */
    @Override
    public Die[] dicePicker(Die[] dice) {
        System.out.println("Choose the dice by typing in their index divided by semicolons.");
        Die[] fixedDice = new Die[5];
        boolean acceptable = false;
        WHILE :
        while (!acceptable) {
            String input = scanner.next();
            String[] inputAsArray = input.split(",");
            for (String s : inputAsArray) {
                s = s.trim();
                if (!(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5"))) {
                    System.out.println("try again.");
                    continue WHILE;
                } else {
                    int index = (Integer.parseInt(s)) - 1;
                    fixedDice[index] = new Die(dice[index]);
                }
            }
            acceptable = true;
        }
        return fixedDice;
    }

    /**
     * lets the player choose a result.
     * @param results the ArrayList to choose from
     * @return the chosen result
     */
    @Override
    public Result resultChooser(ArrayList<Result> results) {
        System.out.println("Choose a result:");
        printResults(results);
        ArrayList<String> resultsAsStrings = new ArrayList<>();
        for (Result result : results) {
            resultsAsStrings.add(result.getName().toLowerCase());
        }
        while (true) {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (resultsAsStrings.contains(input.trim().toLowerCase())) {
                return results.get(resultsAsStrings.indexOf(input.trim().toLowerCase()));
            }
            System.out.println("Try again.");
        }
    }

    @Override
    public void printGameSheet(Player player) {
        /*String gameSheet = """
                | player name\s
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Ones        |     | Three of a Kind |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Twos        |     | Four of a Kind  |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Threes      |     | Full House      |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Fours       |     | Small Straight  |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Fives       |     | Large Straight  |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Sixes       |     | Yaht-C          |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Upper Total |     | Chance          |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Bonus       |     | Lower Total     |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                | Upper Total |     | Total           |     |
                |–––––––––––––|–––––|–––––––––––––––––|–––––|
                """;

         */
        System.out.println(player.getName());
        System.out.println("–".repeat(player.getName().length()));
        System.out.print("Filled ");
        printResults(player.getResultsFilled());
        System.out.println("Current Score: " + player.calculateCurrentTotalScore() + "\n");
    }

    @Override
    public void printRound(int round) {
        System.out.println("Round " + round);
    }

    @Override
    public void printWinner(Player winner) {
        System.out.println("The winner is: " + winner.getName());
    }

    @Override
    public void printDice(ArrayList<Integer> dieValuesAsInt) {
        System.out.println("Dice: " + dieValuesAsInt.toString());
    }

    @Override
    public void printResults(ArrayList<Result> results) {
        System.out.print("Results: ");
        if (results.isEmpty()) {
            System.out.print("[]");
        } else {
            for (int i = 0; i < results.size(); i++) {
                if (i % 6 == 0 && i != 0) {
                    System.out.print("\n");
                }
                System.out.print(results.get(i));
            }
        }

        System.out.println();
    }

    @Override
    public void printEliminationMessage() {
        System.out.println("You can't achieve a new result. Choose a result to eliminate.");
    }

    @Override
    public void rollDice() {
        System.out.println("Enter 'roll' to roll the dice.");
        scanner.next();
    }
}
