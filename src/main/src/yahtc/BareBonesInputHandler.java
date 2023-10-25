package yahtc;

import java.util.ArrayList;
import java.util.Scanner;

public class BareBonesInputHandler implements InputHandler {
    Scanner scanner = new Scanner(System.in);



    public boolean yesNoChooser() {
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
                default -> System.out.println("try again.");
            }
        }
    }

    public Die[] dicePicker(Die[] dice) {
        System.out.println("Choose the dice by typing in their index divided by semicolons.");
        Die[] fixedDice = new Die[5];
        boolean acceptable = false;
        while (!acceptable) {
            String input = scanner.next();
            String[] inputAsArray = input.split(",");
            for (String s : inputAsArray) {
                s = s.trim();
                if (!(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5"))) {
                    System.out.println("try again.");
                    break;
                } else {
                    int index = (Integer.parseInt(s)) - 1;
                    fixedDice[index] = new Die(dice[index]);
                }
            }
            acceptable = true;
        }
        return fixedDice;
    }

    @Override
    public Result resultChooser(ArrayList<Result> results) {
        System.out.println("choose a result:");
        System.out.println(results.toString());
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
}
