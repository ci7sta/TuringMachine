import java.io.IOException;

public class runtm {

    public static void main(String[] args) throws IOException {

        FileParser turingDescParser = new FileParser(args[0], false);

        FileParser tapeParser;

        if(args.length == 2 && args[1] != null) {
            tapeParser = new FileParser(args[1], true);
        } else {
            tapeParser = new FileParser(null, true);
        }

        Tape tape = tapeParser.initialiseTape();
        TuringMachine tm = turingDescParser.initialiseTuringMachine(tape);

        tm.run();
    }
}
