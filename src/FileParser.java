import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for parsing tape and Turing machine description files.
 *
 * @author 160009591
 */
public class FileParser {

    private BufferedReader reader;

    /**
     * Initialise reader to null if the supplied file doesn't exist so we know just to make a new tape later.
     *
     * @param filename    - the file name to read in
     * @param readingTape - if we are reading a tape file or not
     */
    public FileParser(String filename, boolean readingTape) {

        try {
            //this.reader = new BufferedReader(new FileReader("tmfiles/" + filename));
            this.reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            if (readingTape) {
                this.reader = null;
            } else {
                System.exit(3);
            }
        }
    }

    /**
     * Given an optional file, initialise a tape object with the contents of that file (ignoring whitespace)
     *
     * @return a new initialised Tape object
     * @throws IOException if there are errors in file IO
     */
    public Tape initialiseTape() throws IOException {

        if (this.reader == null) {
            return new Tape(null);
        } else {
            String tapeWord = "";
            StringBuilder sb = new StringBuilder(tapeWord);

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return new Tape(sb.toString().replace(" ", ""));
        }
    }

    /**
     * Parse the "states x" line of the TM desc file.
     *
     * @param line - the line of the file containing the number of states
     * @return the number of states for the TM
     */
    private int getNumberOfStates(String line) {

        String[] tokens = line.split("\\s+");
        int numberOfStates = 0;

        try {
            numberOfStates = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            showInputError();
        }

        if (numberOfStates < 2) {
            // Must have at least an accept and reject state.
            showInputError();
        }

        return numberOfStates;
    }


    /**
     * Gather the alphabet characters from the given line.
     *
     * @param line - the line containing the alphabet characters
     * @return an ArrayList of the alphabet
     */
    private ArrayList<Character> getAlphabet(String line) {

        ArrayList<Character> alphabet = new ArrayList<>();

        if (line == null) showInputError();

        try {
            String[] tokens = line.split("\\s+");

            if (tokens.length < 3) showInputError();

            int alphabetSize = Integer.parseInt(tokens[1]);


            if (alphabetSize < 1 || tokens.length - 2 < alphabetSize) showInputError();

            for (int i = 0; i < alphabetSize; i++) {

                if (tokens[i + 2].charAt(0) == '_') showInputError();
                alphabet.add(tokens[i + 2].charAt(0));
            }

        } catch (NullPointerException ex) {
            showInputError();
        }

        return alphabet;
    }


    /**
     * Parse through the states and add them to a state table.
     *
     * @param numberOfStates - number of states the turing machine has
     * @param tm             - the turing machine being initialised
     * @return a HashMap (key state name, value state) - the state table
     * @throws IOException if there are file IO errors
     */
    private HashMap<String, State> getStates(int numberOfStates, TuringMachine tm) throws IOException {

        String line;

        HashMap<String, State> stateTable = new HashMap<>();

        for (int i = 0; i < numberOfStates; i++) {
            line = reader.readLine();

            if (line == null) showInputError();

            String[] tokens = line.split("\\s+");

            // Validate state name - can't be "-" or "+" or "alphabet"
            if (tokens[0].equals("-") || tokens[0].equals("+") || tokens[0].equals("alphabet")) showInputError();

            String stateName = tokens[0];
            boolean accept = false;
            boolean reject = false;

            // Check if this state has a label. If so, check if it's reject or accept.

            if (tokens.length > 1) {
                switch (tokens[1]) {
                    case "+":
                        accept = true;
                        break;
                    case "-":
                        reject = true;
                        break;
                    default:
                        break;
                }
            }

            State state = new State(stateName, accept, reject);


            // Set the accept/reject state if we need to
            if (accept) {
                tm.setAcceptState(state);
            } else if (reject) {
                tm.setRejectState(state);
            }

            if (i == 0) {
                tm.setStartState(state);
            }
            stateTable.put(stateName, state);
        }

        return stateTable;
    }

    /**
     * Initialise the turing machine, parsing through the description file.
     *
     * @param tape - a tape to set for the TM
     * @return an initialised Turing machine object ready to run
     * @throws IOException if there are file IO errors
     */
    public TuringMachine initialiseTuringMachine(Tape tape) throws IOException {

        TuringMachine tm = new TuringMachine();
        tm.setTape(tape);

        HashMap<String, State> stateTable;
        HashMap<String, Transition> transitionTable = new HashMap<>();
        int numberOfStates = 0;

        if (this.reader != null) {
            String line;
            line = reader.readLine();

            // Get number of states
            if (line == null || !line.contains("states")) {
                showInputError();
            } else {
                numberOfStates = getNumberOfStates(line);
                tm.setNumberOfStates(numberOfStates);
            }


            // Set state table and alphabet
            stateTable = getStates(numberOfStates, tm);
            tm.setStateTable(stateTable);
            ArrayList<Character> alphabet = getAlphabet(reader.readLine());
            tm.setAlphabet(alphabet);

            // Get the transition table by parsing the rest of the file
            do {
                line = reader.readLine();

                if (line == null) {
                    break;
                } else if (line.equals("")) {
                    // Skip newlines
                    continue;
                }

                String[] tokens = line.split("\\s+");
                if (tokens.length != 5) showInputError();

                State inputState, outputState;
                inputState = stateTable.get(tokens[0]);
                outputState = stateTable.get(tokens[2]);

                if (inputState == null || outputState == null) showInputError();

                Transition transition = new Transition(inputState, tokens[1].charAt(0),
                        outputState, tokens[3].charAt(0),
                        tokens[4].charAt(0));

                // Check special cases based on the transition just constructed
                validateTransition(transition, tm, transitionTable);
                transitionTable.put(transition.getCurrentState().getName() + transition.getTapeInput(), transition);
            } while (true);
        }

        // Set the transition table and start state

        tm.setTransitionTable(transitionTable);
        tm.setCurrentState(tm.getStartState());

        return tm;
    }

    /**
     * Validate a number of special conditions that would require us to show an input error e.g. duplicate transitions,
     * invalid move character, invalid input/output, etc.
     *
     * @param transition      - the transition to be validated
     * @param tm              - the turing machine being initialised
     * @param transitionTable - the turing machine's transition table
     */
    private void validateTransition(Transition transition, TuringMachine tm, HashMap<String, Transition> transitionTable) {
        if (transition.getMove() != 'L' && transition.getMove() != 'R') showInputError();
        if (!tm.getStateTable().containsKey(transition.getCurrentState().getName())) showInputError();
        if (!tm.getStateTable().containsKey(transition.getOutputState().getName())) showInputError();

        if (transition.getTapeInput() != '_' && !tm.getAlphabet().contains(transition.getTapeInput())) {
            showInputError();
        }

        if (transition.getTapeOutput() != '_' && !tm.getAlphabet().contains(transition.getTapeOutput())) {
            showInputError();
        }

        if (transitionTable.containsKey(transition.getCurrentState().getName() + transition.getTapeInput())) {
            showInputError();
        }
    }

    /**
     * Display input error to the user and exit with appropriate exit code.
     */
    private void showInputError() {

        System.out.println("input error");
        System.exit(2);
    }
}
