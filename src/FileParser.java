import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileParser {

    private BufferedReader reader;

    public FileParser(String filename, boolean readingTape) {

        try {
            this.reader = new BufferedReader(new FileReader("tmfiles/" + filename));
        } catch (FileNotFoundException ex) {
            if (readingTape) {
                this.reader = null;
            } else {
                System.exit(3);
            }
        }
    }

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
            return new Tape(sb.toString());
        }
    }

    private int getNumberOfStates(String line) {

        String[] tokens = line.split("\\s+");
        int numberOfStates = 0;

        try {
            numberOfStates = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException ex) {
            showInputError();
        }

        if (numberOfStates < 2) {
            showInputError();
        }

        return numberOfStates;
    }

    private ArrayList<Character> getAlphabet(String line) {

        ArrayList<Character> alphabet = new ArrayList<>();

        if (line == null) showInputError();

        try {
            String[] tokens = line.split("\\s+");

            if (tokens.length < 3) showInputError();

            int alphabetSize = Integer.parseInt(tokens[1]);

            if (alphabetSize < 1 || tokens.length < alphabetSize) showInputError();

            for (int i = 0; i < alphabetSize; i++) {

                if (tokens[i + 2].charAt(0) == '_') showInputError();
                alphabet.add(tokens[i + 2].charAt(0));
            }

        } catch (NullPointerException ex) {
            showInputError();
        }

        return alphabet;
    }

    private HashMap<String, State> getStates(String line, int numberOfStates, TuringMachine tm) throws IOException {

        HashMap<String, State> stateTable = new HashMap<>();

        for (int i = 0; i < numberOfStates; i++) {
            line = reader.readLine();

            if (line == null) showInputError();

            String[] tokens = line.split("\\s+");

            if (tokens[0].equals("-") || tokens[0].equals("+") || tokens[0].equals("alphabet")) showInputError();

            String stateName = tokens[0];
            boolean accept = false;
            boolean reject = false;

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

    public TuringMachine initialiseTuringMachine(Tape tape) throws IOException {

        TuringMachine tm = new TuringMachine();
        tm.setTape(tape);

        HashMap<String, State> stateTable;
        HashMap<State, HashMap<Character, Transition>> transitionTable = new HashMap<>();
        HashMap<Character, Transition> innerHashMap = new HashMap<>();
        boolean firstTime = true;
        int numberOfStates = 0;

        if (this.reader != null) {
            String line;

            line = reader.readLine();

            if (!line.contains("states")) {
                showInputError();
            } else {
                numberOfStates = getNumberOfStates(line);
                tm.setNumberOfStates(numberOfStates);
            }

            stateTable = getStates(line, numberOfStates, tm);
            tm.setStateTable(stateTable);
            ArrayList<Character> alphabet = getAlphabet(reader.readLine());
            tm.setAlphabet(alphabet);

            String prevReadState = "";

            do {
                line = reader.readLine();

                if (line == null && !firstTime) {
                    transitionTable.put(stateTable.get(prevReadState), innerHashMap);
                    break;
                }

                if (line.equals("")) {
                    continue;
                }

                String[] tokens = line.split("\\s+");
                if (tokens.length != 5) showInputError();

                State inputState, outputState;
                inputState = stateTable.get(tokens[0]);
                outputState = stateTable.get(tokens[2]);

                Transition transition = new Transition(inputState, tokens[1].charAt(0),
                        outputState, tokens[3].charAt(0),
                        tokens[4].charAt(0));

                if (!tokens[0].equals(prevReadState) && !firstTime) {
                    transitionTable.put(stateTable.get(prevReadState), innerHashMap);
                    innerHashMap = new HashMap<>();
                    innerHashMap.put(transition.getTapeInput(), transition);
                } else {
                    innerHashMap.put(transition.getTapeInput(), transition);
                }
                prevReadState = inputState.getName();
                firstTime = false;
            } while (true);
        }

        tm.setTransitionTable(transitionTable);
        tm.setCurrentState(tm.getStartState());

        return tm;
    }

    private void showInputError() {

        System.out.println("input error");
        System.exit(2);
    }
}
