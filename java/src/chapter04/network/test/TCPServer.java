package chapter04.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	
	private static final int PORT = 5001;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			// 1. Create a ServerSocket
			serverSocket = new ServerSocket();
			
			// 2. Bind socket address(IP address + port) to socket
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(localhostAddress, PORT));
			System.out.println("[server] binding " + localhostAddress + ":" + PORT);

			// 3. Accept (클라이언트로부터 연결요청을 기다린다)
			socket = serverSocket.accept();							// blocking
			InetSocketAddress inetRemoteSocketAddress =
					(InetSocketAddress) socket.getRemoteSocketAddress();	// downcasting
			String remoteHostAddress = 
					inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			
			System.out.println(
					"[server] connected by client[" + 
					remoteHostAddress + ":" + remotePort + "]"
			);

			// 4. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			while (true) {
				// 5. 데이터 읽기
				byte[] buffer = new byte[256];
				int readByteCount = is.read(buffer); // blocking
				if (readByteCount == -1) {
					// 정상 종료: remote socket called close()
					// 메서드를 통해서 정상적으로 소켓을 닫은 경우
					System.out.println("[server] closed by client");
					break;
				}
				String data = new String(buffer, 0, readByteCount, "UTF-8");
				System.out.println("[server] recevied: " + data);

				// 6. 데이터 쓰기
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				os.write(data.getBytes("UTF-8"));
			}

		} catch (SocketException e) {
			System.out.println("[server] abnormal closed by ");
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

}
