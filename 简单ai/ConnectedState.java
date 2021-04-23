package ai;

public class ConnectedState implements State {
    public String init() {
        return "Hello, I'm 奥特曼.";
    }

    public String reply(String input) {
        if (input.endsWith("?")) {
            return "Yes. " + input.substring(0, input.length() - 1) + "!";
        }
        if (input.endsWith(".")) {
            return input.substring(0, input.length() - 1) + "!";
        }
        return "不好意思 我不能理解你说啥";
    }
}