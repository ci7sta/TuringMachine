import java.io.IOException;

public class runtm {

    public static void main(String[] args) throws IOException {
        FileParser fp = new FileParser("manystates.tm", false);
        FileParser tapeParser = new FileParser("test.tape", true);
        Tape tape = tapeParser.initialiseTape();
        TuringMachine tm = fp.initialiseTuringMachine(tape);

        tm.run();
    }
}
