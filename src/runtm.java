import java.io.IOException;

/**
 * Main class which is run by the executable.
 *
 * @author 160009591
 */
public class runtm {

    public static void main(String[] args) throws IOException {


        // Create parsers based on command-line args
        FileParser turingDescParser = new FileParser(args[0], false);
        FileParser tapeParser;

        if (args.length == 2 && args[1] != null) {
            tapeParser = new FileParser(args[1], true);
        } else {
            tapeParser = new FileParser("", true);
        }

        // Initialise TM
        Tape tape = tapeParser.initialiseTape();
        TuringMachine tm = turingDescParser.initialiseTuringMachine(tape);


        // Run the TM
        tm.run(false);
    }
}
