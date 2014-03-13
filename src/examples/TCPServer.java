package examples;

// 22. 10. 10

/**
 *
 * @author Peter Altenberd
 * (Translated into English by Ronald Moore)
 * Computer Science Dept.                   Fachbereich Informatik
 * Darmstadt Univ. of Applied Sciences      Hochschule Darmstadt
 */

import java.io.*;
import java.net.*;

public class TCPServer {

	static String line;
	static BufferedReader fromClient;
	static DataOutputStream toClient;
	
	public static void main(String[] args) throws Exception {
		// In first, we launch Web Server :
		MyWebServer serverWeb = new MyWebServer();
		serverWeb.start();
		
		// Next, we launch TCP Server :		
		ServerSocket contactSocket = new ServerSocket(9999);
		while (true) { // Handle connection request
			Socket client = contactSocket.accept(); // create communication
													// socket
			System.out.println("Connection with: "
					+ client.getRemoteSocketAddress());
			new EchoService(client).start();
		}
	}
}
