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

		// lineProtocol = "localhost;localhost;A;APPL;50;20!" +'\n';
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
		toServer.writeBytes((lineProtocol = user.getAllInformations()) + '\n');
		if (lineProtocol.equals("!")) { // Does the user want to end the
										// session?
			holdTheLine = false;
		}
		return holdTheLine;
	}

	private static void receiveResponse() throws IOException {
		String serverAnswers =new String(fromServer.readLine());
		user.output("Server answers: " + serverAnswers + '\n');
		translateProtocolLine(serverAnswers);
	}
	
	private static void translateProtocolLine(String serverAnswers)
	{
		String[] tbPL = serverAnswers.split(";");
		
		String translation = "";
		//Client
		if(tbPL[2].equals("A"))
		{
			translation +="The Client ("+tbPL[0]+") send to Server ("+tbPL[1]+") :";
			translation += "I want buy "+tbPL[4]+" "+tbPL[3]+".";
		}
		else if(tbPL[2].equals("B"))
		{
			translation +="The Client ("+tbPL[0]+") send to Server ("+tbPL[1]+") :";
			translation += "I want sell "+tbPL[4]+" "+tbPL[3]+"for "+tbPL[5]+"$.";
		}
		//server
		else if(tbPL[2].equals("RA"))
		{
			translation +="The Server ("+tbPL[0]+") send to Client ("+tbPL[1]+") :";
			translation += "You have bought "+tbPL[4]+" "+tbPL[3]+"for "+tbPL[5]+"$.";
		}
		else if(tbPL[2].equals("RB"))
		{
			translation +="The Server ("+tbPL[0]+") send to Client ("+tbPL[1]+") :";
			translation += "You have sold "+tbPL[4]+" "+tbPL[3]+"for "+tbPL[5]+"$.";
		}
		else if(tbPL[2].equals("W"))
		{
			translation +="The Server ("+tbPL[0]+") send to Client ("+tbPL[1]+") :";
			translation +="The transaction is waiting.....";
		}
		user.output(translation + '\n');
	}
}
