import java.io.IOException;

public class runtm {

    public static void main(String[] args) throws IOException {
        FileParser turingDescParser = new FileParser(args[0], false);
        FileParser tapeParser = new FileParser(args[1], true);

        Tape tape = tapeParser.initialiseTape();
        TuringMachine tm = turingDescParser.initialiseTuringMachine(tape);

        tm.run();
    }
}
