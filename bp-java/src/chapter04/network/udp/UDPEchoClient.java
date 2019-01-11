package chapter04.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	
	private static final String SERVER_IP = "192.168.0.68";

	public static void main(String[] args) {
		Scanner scanner = null;
		DatagramSocket socket = null;
		
		try {
			// 1. keyboard connection
			scanner = new Scanner(System.in);

			// 2. create a socket
			socket = new DatagramSocket();

			while (true) {
				// 3. get user input
				System.out.print(">> ");
				String message = scanner.nextLine();

				if ("quit".equals(message)) {
					break;
				}
				
				// 4. send a message
				byte[] data = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(
						data,
						data.length,
						new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT)
				);
				socket.send(sendPacket);
				
				// 5. message reception
				DatagramPacket receivePacket = new DatagramPacket(
						new byte[UDPEchoServer.BUFFER_SIZE], UDPEchoServer.BUFFER_SIZE);
				synchronized (socket) {					
					socket.receive(receivePacket);
				}
				
				message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println("<< " + message);
			}
			
		} catch (IOException e) {
			e.printStackTrace();;
		} finally {
			// clean-up resources
			if (scanner != null) {
				scanner.close();
			}
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}

}
