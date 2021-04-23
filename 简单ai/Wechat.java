package ai;

import java.util.Scanner;

public class Wechat {
	private State state = new DisconnectedState();

	public String chat(String input) {
		if ("hello".equalsIgnoreCase(input)) {
            // 收到hello切换到在线状态:
			state = new ConnectedState();
			return state.init();
		} else if ("bye".equalsIgnoreCase(input)) {
            //收到bye切换到离线状态:
			state = new DisconnectedState();
			return state.init();
		}
		return state.reply(input);
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Wechat wechat = new Wechat();
		for (;;) {
		    System.out.print("me :");
		    String input = scanner.nextLine();
		    String output = wechat.chat(input);
		    System.out.println(output.isEmpty() ? "对方不在线,say hello 可唤醒" : "AI :" + output);
		    if(input.equals("bye")) break;
		}
	}
	
}