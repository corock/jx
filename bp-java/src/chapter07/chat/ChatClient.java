package chapter07.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	private static final String SERVER_IP = "192.168.0.68";
	private static final int SERVER_PORT = 9999;

	public static void main(String[] args) {
		Scanner sc = null;
		Socket socket = null;
		
		try {
			// 1. Connecting a keyboard
			sc = new Scanner(System.in);
			
			// 2. Creating a socket for the client
			socket = new Socket();
			
			// 3. Connection
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			// 4. Creating reader/writer
			BufferedReader br = new BufferedReader(
								new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(
							 new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			// 5. Join protocol
			System.out.print("Your nickname >> ");
			String nickname = sc.nextLine();

			pw.println("join:" + nickname);
			pw.flush();

			// 6. Starting ChatClientReceiveThread
			Thread thread = new ChatClientReceiveThread(socket, br);
			thread.start();
			
			// 7. Processing input from the keyboard
			while (true) {
				String input = sc.nextLine();
				
				if("quit".equals(input)) {
					// 8. Processing quit protocol
					pw.println("quit");
					pw.flush();
					break;
				} else {
					// 9. Processing message protocol
					pw.println("message:" + input + ":" + nickname);
					pw.flush();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// 10. Cleaning up resources
			try {
				if (socket != null &&
					socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
