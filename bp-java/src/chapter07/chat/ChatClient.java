package chapter07.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		Scanner sc = null;
		Socket socket = null;
		
		try {
			// 1. Connecting a keyboard
			
			// 2. Creating a socket for the client
			
			// 3. Connection
			
			// 4. Creating reader/writer
			BufferedReader br = new BufferedReader(
								new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(
							 new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			// 5. Join protocol
			System.out.print("Your nickname >> ");
			String nickname = sc.nextLine();
			// printWriter.println("join:" + nickname);
			// printWriter.flush();
			
			// 6. Starting ChatClientReceiveThread
			
			// 7. Processing input from the keyboard
			while (true) {
				System.out.print(">> ");
				String input = sc.nextLine();
				
				if("quit".equals(input)) {
					// 8. Processing quit protocol
					break;
				} else {
					// 9. Processing message protocol
				}
			}
		} catch (IOException ex) {
			// log("error: " + ex);
		} finally {
			// 10. Cleaning up resources
		}
	}

}
