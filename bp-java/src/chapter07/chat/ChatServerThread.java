package chapter07.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

public class ChatServerThread extends Thread {

	private String nickname;
	private Socket socket;
	List<Writer> listWriters;
	BufferedReader br = null;
	PrintWriter pw = null;
	
	public ChatServerThread() {
		this(null, null);
	}
	public ChatServerThread(Socket socket) {
		this(socket, null);
	}
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}
	
	@Override
	public void run() {		
		try {
			// 1. Remote host information
			
			// 2. Get IOStream
			br = new BufferedReader(
								new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(
							 new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			// 3. Processing requests
			while (true) {
				String request = br.readLine();
				if (request == null) {
					ChatServer.log("disconnected client");
					doQuit(pw);
					break;
				}
				
				// 4. Analyzing protocol
				String[] tokens = request.split(":");
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					// doQuit();
				} else {
					ChatServer.log("error: unknown request(" + tokens[0] + ")");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	// end of run()
	
	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;
		
		String data = nickname + "님이 들어왔습니다.";
		broadcast(data);
		
		// save writer pool
		addWriter(writer);
		
		// ack
		pw.println("join:ok");
		pw.flush();
	}
	
	private void doMessage(String message) {
		// TODO doMessage()
	}
	
	private void doQuit(Writer writer) {
		removeWriter(writer);
		
		String data = nickname + "님이 나갔습니다.";
		broadcast(data);
	}
	
	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void removeWriter(Writer writer) {
		// TODO removeWriter()
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}
	
}
