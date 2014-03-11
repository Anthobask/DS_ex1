package examples;

/*
 * 22. 10. 10
 */

/**
 *
 * @author Peter Altenberd
 * (Translated into English by Ronald Moore)
 * Computer Science Dept.                   Fachbereich Informatik
 * Darmstadt Univ. of Applied Sciences      Hochschule Darmstadt
 */

import java.io.*;
import java.net.*;

public class TCPClient {

	static String line;
	static Socket socket;
	static BufferedReader fromServer;
	static DataOutputStream toServer;
	static UserInterface user = new UserInterface();
	static String typeRequest;
	public static String lineProtocol;

	public static void main(String[] args) throws Exception {


		lineProtocol = user.getAllInformations();
		user.output("Message to send to Brocker : "+line);

		socket = new Socket("localhost", 9999);

		toServer = new DataOutputStream( // Datastream FROM Server
				socket.getOutputStream());

		fromServer = new BufferedReader( // Datastream TO Server
				new InputStreamReader(socket.getInputStream()));
		while (sendRequest()) { // Send requests while connected
			receiveResponse(); // Process server's answer
		}
		socket.close();
		toServer.close();
		fromServer.close();
	}

	private static boolean sendRequest() throws IOException {
		boolean holdTheLine = true; // Connection exists
		toServer.writeBytes(lineProtocol);
		if (line.equals("!")) { // Does the user want to end the session?
			holdTheLine = false;
		}
		return holdTheLine;
	}

	private static void receiveResponse() throws IOException {
		user.output("Server answers: " + new String(fromServer.readLine())
				+ '\n');
	}
}
