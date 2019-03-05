import java.util.ArrayList;

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
            tape = new ArrayList<>();
            for (int i = 0; i < initial.length(); i++) {
                tape.add(initial.charAt(i));
            }
            current = initial.charAt(0);
        }

        currentPos = 0;
    }

    public void moveL(Character write) {

        if (this.currentPos != 0) {
            this.tape.set(currentPos, write);
            this.currentPos--;
            this.current = tape.get(currentPos);
        } else {
            this.tape.set(currentPos, write);
        }
    }

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
