import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileParser {

    private BufferedReader reader;

    public FileParser (String filename) {
        try {
            this.reader = new BufferedReader(new FileReader("../tmfiles/" + filename));
        } catch (FileNotFoundException ex) {
            System.exit(3);
        }
    }

    public TuringMachine initialiseTuringMachine() {
        if (this.reader == null) {
            return null;
        } else {

        }
    }
}
