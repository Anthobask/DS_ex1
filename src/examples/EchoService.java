package examples;

// 22.10. 10

/**
 *
 * @author Peter Altenberd
 * (Translated into English by Ronald Moore)
 * Computer Science Dept.                   Fachbereich Informatik
 * Darmstadt Univ. of Applied Sciences      Hochschule Darmstadt
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
public class EchoService extends Thread{
    Socket client;
    EchoService(Socket client){this.client = client;}

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
	
  @Override
    public void run (){
        String line;
        BufferedReader fromClient;
        DataOutputStream toClient;
        boolean verbunden = true;
        System.out.println("Thread started: "+this); // Display Thread-ID
        try{
            fromClient = new BufferedReader              // Datastream FROM Client
            (new InputStreamReader(client.getInputStream()));
            toClient = new DataOutputStream (client.getOutputStream()); // TO Client
            while(verbunden){     // repeat as long as connection exists
                line = fromClient.readLine();              // Read Request
                
                parseFromClient(line);
                
                System.out.println("Received: "+ line);
                if (line.equals(".")) verbunden = false;   // Break Conneciton?
                else toClient.writeBytes(line.toUpperCase()+'\n'); // Response
            }
            fromClient.close(); toClient.close(); client.close(); // End
            System.out.println("Thread ended: "+this);
        }catch (Exception e){System.out.println(e);}
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

		int match = 0;
		if (off.getM_stockType().equals("B")) {
			tabBids.add(off);
			// If bids, we check if ask match
			for (Offer anOffer : tabAsks) {
				if (anOffer.getM_stockName().equals(off.getM_stockName())) {
					if (anOffer.getM_quantity() <= off.getM_quantity()) {
						match = 1;
						off_event = anOffer;
					}

				}
			}
		} else if (off.getM_stockType().equals("A")) {
			tabAsks.add(off);
			for (Offer anOffer : tabBids) {
				if (anOffer.getM_stockName().equals(off.getM_stockName())) {
					if (anOffer.getM_quantity() >= off.getM_quantity()) {
						match = 1;
						off_event = anOffer;
					}
				}
			}
		}
		
		if (match == 0) { // send "Waiting..."
			responseText = IPSource + ";" + IPDest + ";"
					+ "W" + ";" + off.getM_stockName()
					+ ";" + off.getM_price() + ";" + off.getM_quantity()
					+ ";";
		}
		else {
			
			if(off.getM_stockType().equals("A"))
			{
				//this ask have a bid !
				off_event.setM_quantity(Integer.toString(off_event.getM_quantity()- off.getM_quantity()));
				responseText = IPSource + ";" + IPDest + ";"
						+ "R" + off.getM_stockType() + ";" + off.getM_stockName()
						+ ";" + off.getM_price() + ";" + off.getM_quantity()
						+ ";";
			}
			else
			{
				//this bid have an ask !
				off.setM_quantity(Integer.toString(off.getM_quantity()- off_event.getM_quantity()));
				responseText = IPSource + ";" + IPDest + ";"
						+ "R" + off.getM_stockType() + ";" + off.getM_stockName()
						+ ";" + off.getM_price() + ";" + off.getM_quantity()
						+ ";";
			}			
		}
		
		/*
		 * 
			
		 */

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

}
