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
     * Validate the tape, get the input character and call method to handle the transition.
     */
    public void run(boolean experimentModeToggled) {

        // Set up experiment variables
        DATA_PRINTED = false;
        EXPERIMENT_MODE = experimentModeToggled;
        TAPE_LENGTH = tape.getTape().size();

        if (!validateTape()) {
            System.out.println("input error");
            System.exit(2);
        }


        // Main TM loop, get current char and handle the transition
        do {
            Character input = tape.getCurrent();
            if (!handleTransition(input)) transitions++;
        } while (!checkCurrentState() && !DATA_PRINTED);
    }


    /**
     * Pre-validate the tape to check all characters are in the alphabet.
     *
     * @return if the tape is valid or not
     */
    private boolean validateTape() {
        for (Character c : tape.getTape()) {
            if (!alphabet.contains(c) && c != '_') return false;
        }
        return true;
    }

    /**
     * Handle the transition based on the input by locating it in the transition table and doing it, or acting
     * appropriately if it's not found (by doing the "virtual" transition)
     *
     * @param input - the current read character
     * @return true if we do the virtual transition, so we don't count it
     */
    private boolean handleTransition(Character input) {

        try {
            Transition transition = transitionTable.get(currentState.getName() + input);


            if (transition == null) {
                handleTransitionNotFound(input);
                checkCurrentState();
                return true;
            } else {
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
            System.out.println(transitions + " ");
            this.tape.printState();
            System.exit(1);
        }

        return false;
    }

    /**
     * In experiment mode, print info to a csv file.
     */
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

    /**
     * Check the current state we're in, and accept/reject if we need to.
     *
     * @return true if we write to the file to stop the program exiting
     */
    private boolean checkCurrentState() {

        if (currentState.isAccept()) {

            if (EXPERIMENT_MODE) {
                printData();
                return true;
            }

            System.out.println("accepted");
            System.out.println(transitions + " ");
            tape.printState();
            System.exit(0);

        } else if (currentState.isReject()) {

            if (EXPERIMENT_MODE) {
                printData();
                return true;
            }

            System.out.println("not accepted");
            System.out.println(transitions + " ");
            tape.printState();
            System.exit(1);

        }

        return false;
    }

    /**
     * If we didn't get a transition, exit with error or do the virtual transition and reject.
     *
     * @param input - the input character
     */
    private void handleTransitionNotFound(Character input) {

        if (!alphabet.contains(input) && input != '_') {
            System.exit(2);
        } else {

            // Perform "virtual" transition, since no transition exists for given input and current state
            currentState = rejectState;
            tape.moveL(tape.getCurrent());
        }
    }

    /**
     * Perform a normal transition, and move L or R on the tape based on what the transition entry tells us to do.
     *
     * @param transition - the transition instruction to perform
     */
    private void performStateTransition(Transition transition) {

        currentState = transition.getOutputState();

        switch (transition.getMove()) {
            case 'L':
                tape.moveL(transition.getTapeOutput());
                break;

            case 'R':
                tape.moveR(transition.getTapeOutput());
                break;
        }
    }

    /*
     * Getters and setters
     */

    public void setTransitions(int transitions) {

        this.transitions = transitions;
    }

    public void setNumberOfStates(int numberOfStates) {

        this.numberOfStates = numberOfStates;
    }

    public void setAcceptState(State acceptState) {

        this.acceptState = acceptState;
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
