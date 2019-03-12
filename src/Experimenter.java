/**
 * Experimenter class, used to gather data from repeated TM runs.
 *
 * @author 160009591
 */
public class Experimenter {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Invalid command-line args. Usage: java Experimenter <tmDesc> <maxInputLength> <testName>");
        } else {

            try {
                FileParser turingDescParser = new FileParser(args[0], false);
                int maxInputLength = Integer.parseInt(args[1]);
                Tape newTape = new Tape("");
                TuringMachine tm = turingDescParser.initialiseTuringMachine(newTape);


                // Repeatedly run the tm with increasing input lengths
                for (int i = 0; i < maxInputLength; i++) {

                    // Get new tape
                    newTape = getTestTape(args[2], i);

                    tm.setTransitions(0);
                    tm.setTape(newTape);
                    tm.setCurrentState(tm.getStartState());
                    tm.run(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Tape getTestTape(String testName, int inputLength) {

        // Based on the specified test, initialise a tape with a given length
        System.out.println("finished");
        Tape newTape = new Tape("");
        newTape.getTape().clear();

        switch (testName) {
            case "paren":

                // Creates tapes of the form (((........)))

                newTape = new Tape("(");
                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('(');
                }

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add(')');
                }
                break;

            case "twoscomp":

                // Creates tapes of the form 1111......#0000......

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('0');
                }
                break;

            case "binadd":

                // Creates tapes of the form 11111........#000000.......#1111......

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('0');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }
                break;

            case "lessthan":

                // Creates tapes of the form 11111......#111111......

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }
        System.out.println("finished");
                break;
        }

        newTape.setCurrent(newTape.getTape().get(0));
        return newTape;
    }
}
