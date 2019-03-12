/**
 * Class to represent a state object.
 *
 * @author 160009591
 */
public class State {

    private String name;
    private boolean accept;
    private boolean reject;

    public State(String name, boolean accept, boolean reject) {

        this.name = name;
        this.accept = accept;
        this.reject = reject;
    }

    /*
     * Getters and setters
     */

    public String getName() {

        return name;
    }

    public boolean isAccept() {

        return accept;
    }

    public boolean isReject() {

        return reject;
    }
}
