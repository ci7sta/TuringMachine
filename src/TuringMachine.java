import java.util.ArrayList;
import java.util.HashMap;

public class TuringMachine {

    private int transitions;
    private int numberOfStates;
    private State acceptState, rejectState;
    private HashMap<String, State> stateTable;
    private HashMap<State, HashMap<Character, Transition>> transitionTable;
    private ArrayList<Character> alphabet;
    private Tape tape;
    private State currentState;
    private State startState;

    public TuringMachine() {

    }

    public void run() {

        do {

            if (this.currentState.isAccept()) {
                System.out.println("accepted");
                System.out.println(this.transitions - 1);
                this.tape.printState();
                System.exit(0);
            } else if (this.currentState.isReject()) {
                System.out.println("not accepted");
                System.out.println(this.transitions - 1);
                this.tape.printState();
                System.exit(1);
            }

            Character input = this.tape.getCurrent();

            if (!this.alphabet.contains(input) && input != '_') {
                System.out.println("error in tape file");
                System.exit(3);
            }
            Transition transition = this.transitionTable.get(this.currentState).get(input);

            if (transition == null) {

                if (!this.alphabet.contains(input) && input != '_') {
                    System.out.println("error in tape file");
                    System.exit(3);
                } else {
                    this.currentState = this.rejectState;
                    this.tape.moveL(this.tape.getCurrent());
                }

            } else {
                this.currentState = transition.getOutputState();
              /*  System.out.println("should write " + transition.getTapeOutput());
                System.out.println("should go to state " + transition.getOutputState().getName() + " rejecting? " + transition.getOutputState().isReject());
                System.out.println("should move " + transition.getMove());*/

                switch (transition.getMove()) {
                    case 'L':
                        this.tape.moveL(transition.getTapeOutput());
                        this.transitions++;
                        break;

                    case 'R':
                        this.tape.moveR(transition.getTapeOutput());
                        this.transitions++;
                        break;
                }
            }
        } while (true);
    }

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

    public HashMap<State, HashMap<Character, Transition>> getTransitionTable() {

        return transitionTable;
    }

    public void setTransitionTable(HashMap<State, HashMap<Character, Transition>> transitionTable) {

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
