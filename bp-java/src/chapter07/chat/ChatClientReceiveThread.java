package chapter07.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

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

				if ("quit".equals(response)) {
					break;
				}

				if ("join:ok".equals(response)) {
					System.out.println("환영합니다.");
				} else {
					System.out.println(response);
				}
			}

		} catch (SocketException e) {
			return;
		} catch (IOException e) {
			ChatServer.log("error: " + e);
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
