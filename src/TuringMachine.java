import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class representing the Turing Machine itself. Once supplied with the necessary parameters, the run() method can be
 * invoked to process a tape based on a description and "accept" or "reject".
 *
 * @author 160009591
 */
public class TuringMachine {

    private int transitions;
    private int numberOfStates;
    private State acceptState, rejectState;
    private HashMap<String, State> stateTable;
    private HashMap<String, Transition> transitionTable;
    private ArrayList<Character> alphabet;
    private Tape tape;
    private State currentState;
    private State startState;
    private boolean EXPERIMENT_MODE;
    private boolean DATA_PRINTED;
    private int TAPE_LENGTH = 0;

    public TuringMachine() {

    }

    /**
     * Run method of the turing machine.
     * <p>
     * Repeatedly:
     * - Get an input character from the tape
     * - Locate the transition associated with the current state and the input character
     * - Perform the transition
     * - Invoke tape methods to move L or R according to transition table
     * - Accept or reject when one of these states reached (or reject when we have the special "virtual transition")
     */
    public void run(boolean experimentModeToggled) {

        DATA_PRINTED = false;
        EXPERIMENT_MODE = experimentModeToggled;
        TAPE_LENGTH = tape.getTape().size();

        if (!validateTape()) {
            System.out.println("input error");
            System.exit(2);
        }

        do {
            Character input = this.tape.getCurrent();

            if (!this.alphabet.contains(input) && input != '_') {
                System.out.println("error in tape file");
                System.exit(3);
            }

            if (!handleTransition(input)) transitions++;
            //tape.printState();
        } while (!checkCurrentState() && !DATA_PRINTED);
    }

    private boolean validateTape() {
        for (Character c : this.tape.getTape()) {
            if (!this.alphabet.contains(c) && c != '_') return false;
        }

        return true;
    }

    private boolean handleTransition(Character input) {

        try {
            Transition transition = this.transitionTable.get(this.currentState.getName() + input);


            if (transition == null) {
                handleTransitionNotFound(input);
                checkCurrentState();
                return true;
            } else {
                //System.out.println(transition.toString());
                performStateTransition(transition);
                checkCurrentState();
                return false;
            }

        } catch (NullPointerException ex) {
            if (EXPERIMENT_MODE) {
                printData();
                return true;
            }

            System.out.println("not accepted");
            System.out.println(this.transitions + " ");
            this.tape.printState();
            System.exit(1);
        }

        return false;
    }

    private void printData() {
        try {
            if (!DATA_PRINTED) {
                FileWriter pw = new FileWriter("data.csv", true);
                pw.append(TAPE_LENGTH + "");
                pw.append(",");
                pw.append(transitions + "");
                pw.append("\n");

                pw.flush();
                DATA_PRINTED = true;
                tape.printState();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean checkCurrentState() {

        if (this.currentState.isAccept()) {

            if (EXPERIMENT_MODE) {
                printData();
                return true;
            }

            System.out.println("accepted");
            System.out.println(this.transitions + " ");
            this.tape.printState();
            System.exit(0);

        } else if (this.currentState.isReject()) {

            if (EXPERIMENT_MODE) {
                printData();
                return true;
            }

            System.out.println("not accepted");
            System.out.println(this.transitions + " ");
            this.tape.printState();
            System.exit(1);

        }

        return false;
    }

    private void handleTransitionNotFound(Character input) {

        if (!this.alphabet.contains(input) && input != '_') {
            System.exit(2);
        } else {

            // Perform "virtual" transition, since no transition exists for given input and current state
            this.currentState = this.rejectState;
            this.tape.moveL(this.tape.getCurrent());
        }
    }

    private void performStateTransition(Transition transition) {

        this.currentState = transition.getOutputState();

        switch (transition.getMove()) {
            case 'L':
                this.tape.moveL(transition.getTapeOutput());
                break;

            case 'R':
                this.tape.moveR(transition.getTapeOutput());
                break;
        }
    }

    /*
     * Getters and setters
     */

    public int getTransitions() {

        return transitions;
    }

    public void setTransitions(int transitions) {

        this.transitions = transitions;
    }

    public int getNumberOfStates() {

        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {

        this.numberOfStates = numberOfStates;
    }

    public State getAcceptState() {

        return acceptState;
    }

    public void setAcceptState(State acceptState) {

        this.acceptState = acceptState;
    }

    public State getRejectState() {

        return rejectState;
    }

    public void setRejectState(State rejectState) {

        this.rejectState = rejectState;
    }

    public HashMap<String, State> getStateTable() {

        return stateTable;
    }

    public void setStateTable(HashMap<String, State> stateTable) {

        this.stateTable = stateTable;
    }

    public HashMap<String, Transition> getTransitionTable() {
        return transitionTable;
    }

    public void setTransitionTable(HashMap<String, Transition> transitionTable) {
        this.transitionTable = transitionTable;
    }

    public ArrayList<Character> getAlphabet() {

        return alphabet;
    }

    public void setAlphabet(ArrayList<Character> alphabet) {

        this.alphabet = alphabet;
    }

    public Tape getTape() {

        return tape;
    }

    public void setTape(Tape tape) {

        this.tape = tape;
    }

    public State getCurrentState() {

        return currentState;
    }

    public void setCurrentState(State currentState) {

        this.currentState = currentState;
    }

    public State getStartState() {

        return startState;
    }

    public void setStartState(State startState) {

        this.startState = startState;
    }
}
