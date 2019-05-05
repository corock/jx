package chapter04.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static final int PORT = 5003;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. Create a server socket
			serverSocket = new ServerSocket();
			
			// 2. Bind socket address to socket
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(localhostAddress, PORT));
			log("binding " + localhostAddress + ":" + PORT);

			// 3. Accept
			Socket socket = serverSocket.accept();
			InetSocketAddress inetRemoteSocketAddress = 
					(InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = 
					inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			
			log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
			// 4. Receive IOStream
			BufferedReader br = new BufferedReader(
								new InputStreamReader(
								socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(
							 new OutputStreamWriter(
							 socket.getOutputStream(), "UTF-8"), true);
			
			while (true) {
				// 5. Read data
				String data = br.readLine();
				if (data == null) {
					return;
				}
				if ("exit".equals(data)) {
					log("closed by client");
					break;
				}				

				log("received: " + data);
				
				// 6. Write data
				pw.println(data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null &&
					serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void log(String log) {
		System.out.println("[server] " + log);
	}

}
