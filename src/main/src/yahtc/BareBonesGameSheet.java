package yahtc;

public class BareBonesGameSheet extends GameSheet{

    public BareBonesGameSheet(Player player) {
        super(player);
    }


    private String getResultScore(Integer result) {
        String score;
        if (result == null) {
            score = "  ";
        } else {
            score = String.valueOf(result); //append whitespaces?
        }
        return score;
    }

    private String getPlayerLine() {
        StringBuilder playerLine = new StringBuilder();
        int length = 45 - player.getName().length();
        playerLine.append("|");
        String repeat = "-".repeat(Math.max(0, length / 2 - 2));
        playerLine.append(repeat);
        playerLine.append(player.getName());
        playerLine.append(repeat);
        playerLine.append("\n");
        return playerLine.toString();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        String tableLines = "|–––––––––––––|–––––|–––––––––––––––––|–––––|\n";
        String firstLine = "| Ones        | " + getResultScore(scores[0]) +
                "  | Three of a Kind |  " + getResultScore(scores[7]) + " |\n";
        String secondLine = "| Twos        | " + getResultScore(scores[1]) +
                "  | Four of a Kind  | " + getResultScore(scores[8]) + " |\n";
        String thirdLine = "| Threes      | " + getResultScore(scores[2]) +
                "  | Full House      | " + getResultScore(scores[9]) + "  |\n";
        String fourthLine = "| Fours       | " + getResultScore(scores[3]) +
                "  | Small Straight  | " + getResultScore(scores[10]) + "  |\n";
        String fifthLine = "| Fives       | " + getResultScore(scores[4]) +
                "  | Large Straight  | " + getResultScore(scores[11]) + "  |\n";
        String sixthLine = "| Sixes       | " + getResultScore(scores[5]) +
                "  | Yaht-C          | " + getResultScore(scores[12]) + "  |\n";
        String seventhLine = "| Upper Total | " + calculateUpper() +
                " | Chance          | " + getResultScore(scores[13]) + "  |\n";
        String eighthLine = "| Bonus       | " + getResultScore(scores[6]) +
                "  | Lower Total     | " + calculateLower() + " |\n";
        String ninthLine = "| Upper Total | " + (calculateLower() + getResultScore(scores[6])) +
                " | Total           | " + calculateTotal() + " |\n";

        stringBuilder.append(getPlayerLine());
        stringBuilder.append(tableLines);
        stringBuilder.append(firstLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(secondLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(thirdLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(fourthLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(fifthLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(sixthLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(seventhLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(eighthLine);
        stringBuilder.append(tableLines);
        stringBuilder.append(ninthLine);
        stringBuilder.append(tableLines);


        return stringBuilder.toString();
    }
}
