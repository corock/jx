package chapter05.thread.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static final String SERVER_IP = "192.168.0.68";
	private static final int SERVER_PORT = 6000;
	
	public static void main(String[] args) {
		Socket socket = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			// 1. Create a socket for the client
			socket = new Socket();

			// 2. Connecting to the server
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			// 3. Receive IOStream
			BufferedReader br = new BufferedReader(
								new InputStreamReader(
								socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(
							 new OutputStreamWriter(
							 socket.getOutputStream(), "UTF-8"), true);
			
			while (true) {
				// 4. Write data
				System.out.print(">> ");
				String data = sc.nextLine();
				pw.println(data);
				
				if (data == null) {
					return;
				}
				if ("exit".equals(data)) {
					break;
				}
				
				// 5. Read data
				System.out.print("<< ");
				System.out.println(br.readLine());
			}					
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null &&
					socket.isClosed() == false) {
					socket.close();
				}
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
