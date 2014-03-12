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
import java.util.ArrayList;

public class TCPServer {

	static String line;
	static BufferedReader fromClient;
	static DataOutputStream toClient;
	static Offer off; // current offer
	static Offer off_event; // offer which buy or sell after current off.
	static ArrayList<Offer> tabBids = new ArrayList<Offer>();
	static ArrayList<Offer> tabAsks = new ArrayList<Offer>();
	static String IPSource = "localhost";
	static String IPDest = "localhost";
	static String responseText = "Error...";

	public static void main(String[] args) throws Exception {
		ServerSocket contactSocket = new ServerSocket(9999);
		while (true) { // Handle connection request
			Socket client = contactSocket.accept(); // create communication
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
		IPDest = tbRequest[1];

		ManageProtocol();
	}

	static void ManageProtocol() {
		// we add this offer in a tab

		if (off.getM_stockType().equals("B")) {
			tabBids.add(off);
			// If bids, we check if ask match
			int match = 0;
			for (Offer anOffer : tabAsks) {
				if (anOffer.getM_stockName().equals(off.getM_stockName())) {
					if (anOffer.getM_quantity() >= off.getM_quantity()) {
						match = 1;
						off_event = anOffer;
					}

				}
			}
			if (match == 0) { // send "Waiting..."
				responseText = IPSource + ";" + IPDest + ";"
						+ "W" + ";" + off.getM_stockName()
						+ ";" + off.getM_price() + ";" + off.getM_quantity()
						+ ";";
			}
		} else if (off.getM_stockType().equals("A")) {
			tabAsks.add(off);
		}

		/*
		 * / try { InetAddress thisIp = InetAddress.getLocalHost(); IPSource =
		 * thisIp.getHostAddress(); } catch (UnknownHostException e) {
		 * System.out.println("IP is not found... "); e.printStackTrace(); } //
		 * IPSource + ";" + IPDest + ";" + typeRequest + ";" + stocksName + ";"
		 * // + stocksPrice + ";" + stocksNumbers;
		 * 
		 * String text = IPSource + ";" + IPDest + ";" + off.getM_stockType() +
		 * ";" + off.getM_stockName() + ";" + off.getM_price() + ";" +
		 * off.getM_quantity() + ";";
		 */

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

		toClient.writeBytes(responseText + '\n'); // Send answer
	}
}
