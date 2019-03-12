/**
 * Class to represent a transition from one state to another, and hold the input/output characters to read/write from
 * the tape, as well as the move direction.
 *
 * @author 160009591
 */
public class Transition {

    private State currentState;
    private Character tapeInput;
    private State outputState;
    private Character tapeOutput;
    private Character move;


    public Transition(State currentState, Character tapeInput, State outputState, Character tapeOutput, Character move) {

        this.currentState = currentState;
        this.tapeInput = tapeInput;
        this.outputState = outputState;
        this.tapeOutput = tapeOutput;
        this.move = move;
    }

    @Override
    public String toString() {

        return this.currentState.getName() + " "
                + this.tapeInput + " " + this.outputState.getName() + " "
                + this.tapeOutput + " " + this.move;
    }

    /*
     * Getters
     */
    public State getCurrentState() {

        return currentState;
    }

    public Character getTapeInput() {

        return tapeInput;
    }


    public State getOutputState() {

        return outputState;
    }


    public Character getTapeOutput() {

        return tapeOutput;
    }

    public Character getMove() {

        return move;
    }
}
