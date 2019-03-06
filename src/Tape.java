import java.util.ArrayList;

/**
 * Class representing the tape (as an ArrayList of Characters). Supplies methods to move left or right and write to
 * the tape.
 *
 * @author 160009591
 */
public class Tape {

    private ArrayList<Character> tape;
    private Character current;
    private int currentPos;

    public Tape(String initial) {

        if (initial == null) {
            tape = new ArrayList<>();
            tape.add('_');
            current = ('_');
        } else {
            initial = initial.replace("\n", "");
            tape = new ArrayList<>();
            for (int i = 0; i < initial.length(); i++) {
                tape.add(initial.charAt(i));
            }
            current = initial.charAt(0);
        }

        currentPos = 0;
    }


    /**
     * Move left on the tape (stay in current position if we're at the left end).
     *
     * @param write - the output to write to the current tape position
     */
    public void moveL(Character write) {

        if (this.currentPos != 0) {
            this.tape.set(currentPos, write);
            this.currentPos--;
            this.current = tape.get(currentPos);
        } else {
            this.tape.set(currentPos, write);
        }
    }

    /**
     * Move right on the tape (add a blank if we've reached the "end").
     *
     * @param write - the output to write to the current tape position
     */
    public void moveR(Character write) {

        this.tape.set(currentPos, write);
        currentPos++;

        try {
            this.current = this.tape.get(currentPos);
        } catch (IndexOutOfBoundsException ex) {
            this.tape.add('_');
            this.current = '_';
        }
    }


    /**
     * Print the final resulting tape after the TM has halted.
     */
    public void printState() {
        StringBuilder sb = new StringBuilder();

        // Assemble string by iterating across the tape
        for (Character tapeCharacter : this.tape) {
            sb.append(tapeCharacter);
        }


        // Strip trailing blanks
        String tapeOutput = sb.toString().replaceAll("_+$", "");

        // If the tape is now empty, print a single blank by convention
        if (tapeOutput.isEmpty()) tapeOutput = "_";

        System.out.println(tapeOutput);
    }

    /*
     * Getters and setters
     */

    public ArrayList<Character> getTape() {

        return tape;
    }

    public void setTape(ArrayList<Character> tape) {

        this.tape = tape;
    }

    public Character getCurrent() {

        return current;
    }

    public void setCurrent(Character current) {

        this.current = current;
    }
}
