public class State {

    private String name;
    private boolean accept;
    private boolean reject;

    public State(String name, boolean accept, boolean reject) {

        this.name = name;
        this.accept = accept;
        this.reject = reject;
    }

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
