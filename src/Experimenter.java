public class Experimenter {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid command-line args. Usage: java Experimenter <tmDesc> <tapeFile> <maxInputLength> <testName>");
        } else {

            try {
                FileParser turingDescParser = new FileParser(args[0], false);
                FileParser tapeParser;

                if (args[1] != null) {
                    tapeParser = new FileParser(args[1], true);
                } else {
                    tapeParser = new FileParser("", true);
                }

                int maxInputLength = Integer.parseInt(args[2]);
                Tape newTape = new Tape("");
                TuringMachine tm = turingDescParser.initialiseTuringMachine(newTape);


                for (int i = 0; i < maxInputLength; i++) {
                    newTape = getTestTape(args[3], i);

                    tm.setTransitions(0);
                    tm.setTape(newTape);
                    tm.setCurrentState(tm.getStartState());
                    tm.run(true, i);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Tape getTestTape(String testName, int inputLength) {

        Tape newTape = new Tape("");

        switch (testName) {
            case "paren":
                newTape.getTape().clear();
                newTape = new Tape("(");
                for (int i = 0; i < (inputLength / 2); i++) {
                    newTape.getTape().add('(');
                }

                for (int i = 0; i < (inputLength / 2); i++) {
                    newTape.getTape().add(')');
                }
                newTape.setCurrent(newTape.getTape().get(0));
                break;

            case "twoscomp":
                newTape.getTape().clear();
                for (int i = 0; i < (inputLength / 2); i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < (inputLength / 2); i++) {
                    newTape.getTape().add('0');
                }
                newTape.setCurrent(newTape.getTape().get(0));
                break;

        }
        return newTape;
    }
}
