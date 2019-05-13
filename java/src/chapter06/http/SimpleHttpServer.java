package chapter06.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
	
	private static final int PORT = 8093;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. create a server socket
			serverSocket = new ServerSocket();
			   
			// 2. binding
			String localhost = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind( new InetSocketAddress( localhost, PORT ) );
			consoleLog("bind " + localhost + ":" + PORT);

			while (true) {
				// 3. wait for connecting ( accept )
				Socket socket = serverSocket.accept();

				// 4. delegate processing request
				new RequestHandler(socket).start();
			}

		} catch (IOException ex) {
			consoleLog("error: " + ex);
		} finally {
			// 5. clean-up resources
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException ex) {
				consoleLog("error: " + ex);
			}
		}
	}

	public static void consoleLog(String message) {
		System.out.println("[HttpServer#" + Thread.currentThread().getId()  + "] " + message);
	}
}
