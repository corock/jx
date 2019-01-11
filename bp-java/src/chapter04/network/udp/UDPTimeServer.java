package chapter04.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {

	public static final int PORT = 6000;
	public static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			// 1. create a UDP socket
			socket = new DatagramSocket(PORT);

			while (true) {
				// 2. recption waiting
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);

				// 3. send current time
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS a").format(new Date());

				byte[] sendData = time.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(
						sendData,
						sendData.length,
						receivePacket.getAddress(),
						receivePacket.getPort()
				);
				socket.send(sendPacket);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {				
				socket.close();
			}
		}
	}

}
