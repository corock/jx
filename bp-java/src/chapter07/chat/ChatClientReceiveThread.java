package chapter07.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientReceiveThread extends Thread {
	
	private Socket socket;
	private BufferedReader br;
	
	public ChatClientReceiveThread(Socket socket, BufferedReader br) {
		this.socket = socket;
		this.br = br;
	}

	/**
	 * reading data through reader and
	 * printing it to the console (message processing)
	 */
	@Override
	public void run() {
		try {			
			while (true) {
				System.out.print(">> ");
				String response = br.readLine();
				if ("join:ok".equals(response)) {
					System.out.println("환영합니다.");
				} else {
					System.out.println(response);
				}
			}
			
		} catch (IOException e) {
			ChatServer.log("error: " + e);
		}
	}
	
}
