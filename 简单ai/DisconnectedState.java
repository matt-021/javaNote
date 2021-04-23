package ai;

public class DisconnectedState implements State {
    public String init() {
        return "Bye! matt";
    }

    public String reply(String input) {
        return "";
    }
}