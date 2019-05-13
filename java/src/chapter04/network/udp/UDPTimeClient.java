package chapter04.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPTimeClient {
	
	private static final String SERVER_IP = "192.168.0.68";

	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			Scanner sc = new Scanner(System.in);
			// 1. create a UDP socket
			socket = new DatagramSocket();

			while (true) {
				// 2. get user input
				System.out.print(">> What time is it? Press Enter key!");
				String request = sc.nextLine();

				if ("quit".equals(request)) {
					break;
				}

				// 3. send time request
				byte[] sendData = "".getBytes("UTF-8");

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						new InetSocketAddress(SERVER_IP, UDPTimeServer.PORT));
				socket.send(sendPacket);

				// 4. get the current time
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPTimeServer.BUFFER_SIZE],
						UDPTimeServer.BUFFER_SIZE);
				socket.receive(receivePacket);

				// 5. print the current time
				String time = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println("<< " + time + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();				
			}
		}
	}

}
