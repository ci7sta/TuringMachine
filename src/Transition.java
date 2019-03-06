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
     * Getters and setters
     */

    public State getCurrentState() {

        return currentState;
    }

    public void setCurrentState(State currentState) {

        this.currentState = currentState;
    }

    public Character getTapeInput() {

        return tapeInput;
    }

    public void setTapeInput(Character tapeInput) {

        this.tapeInput = tapeInput;
    }

    public State getOutputState() {

        return outputState;
    }

    public void setOutputState(State outputState) {

        this.outputState = outputState;
    }

    public Character getTapeOutput() {

        return tapeOutput;
    }

    public void setTapeOutput(Character tapeOutput) {

        this.tapeOutput = tapeOutput;
    }

    public Character getMove() {

        return move;
    }

    public void setMove(Character move) {

        this.move = move;
    }
}
