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
	static Offer off;

	public static void main(String[] args) throws Exception {
		ServerSocket contactSocket = new ServerSocket(9999);
		while (true) { // Handle connection request
			Socket client = contactSocket.accept(); // creat communication
													// socket
			System.out.println("Connection with: "
					+ client.getRemoteSocketAddress());
			handleRequests(client);
		}
		
	}

	static void handleRequests(Socket s) {
		try {
			fromClient = new BufferedReader( // Datastream FROM Client
					new InputStreamReader(s.getInputStream()));
			toClient = new DataOutputStream(s.getOutputStream()); // Datastream
																	// TO Client
			while (receiveRequest()) { // As long as connection exists
				parseFromClient(line);
				sendResponse();			
			}
			fromClient.close();
			toClient.close();
			s.close();
			System.out.println("Session ended, Server remains active");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static void parseFromClient(String clientRequest) {
		off = new Offer();
		String[] tbRequest = clientRequest.split(";");
		off.setM_identity(tbRequest[0]);
		off.setM_stockType(tbRequest[2]);
		off.setM_stockName(tbRequest[3]);
		off.setM_quantity(tbRequest[4]);
		off.setM_price(tbRequest[5]);
	}

	static boolean receiveRequest() throws IOException {
		boolean holdTheLine = true;
		System.out.println("Received: " + (line = fromClient.readLine()));
		if (line.equals("!")) { // End of session?
			holdTheLine = false;
		}
		return holdTheLine;
	}

	static void sendResponse() throws IOException {
		//toClient.writeBytes("toto" +'\n');
		toClient.writeBytes("Hi, you are : " + off.getM_identity()
				+ "Your request is : You want " + off.getM_stockType()
				+ " " + off.getM_quantity() + " of " + off.getM_stockName()
				+ " in price of " + off.getM_price() + '\n'); // Send answer
	}
}
