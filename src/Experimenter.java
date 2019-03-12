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
                    tm.run(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Tape getTestTape(String testName, int inputLength) {

        Tape newTape = new Tape("");
        newTape.getTape().clear();

        switch (testName) {
            case "paren":

                newTape = new Tape("(");
                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('(');
                }

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add(')');
                }
                break;

            case "twoscomp":
                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('0');
                }
                break;

            case "binadd":
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
                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                newTape.getTape().add('#');

                for (int i = 0; i < inputLength; i++) {
                    newTape.getTape().add('1');
                }

                break;
        }

        newTape.setCurrent(newTape.getTape().get(0));
        return newTape;
    }
}
