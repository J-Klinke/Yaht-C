package yahtc;

import java.util.ArrayList;

public interface InputHandler {

    /**
     * handles a player's boolean decision.
     * @return the decision
     */
    boolean yesNoChooser();

    /**
     * allows a player to pick dice.
     * @param dice the Die[] to pick from
     * @return a Die[] of the picked dice
     */
    Die[] dicePicker(Die[] dice);

    /**
     * lets the player choose a result.
     * @param results the ArrayList to choose from
     * @return the chosen result
     */
    Result resultChooser(ArrayList<Result> results);
}
