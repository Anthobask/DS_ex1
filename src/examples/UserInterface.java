package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 21. 5. 10
 */

/**
 * 
 * @author Peter Altenberd (Translated into English by Ronald Moore) Computer
 *         Science Dept. Fachbereich Informatik Darmstadt Univ. of Applied
 *         Sciences Hochschule Darmstadt
 */

public class UserInterface {

	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	
	String[] listStocks = {"AAPL", "IBM", "MSFT", "ORCL"};
	String[] listRequest = {"A", "B"};

	public String input() throws IOException {
		return stdIn.readLine();
	}

	public void output(String out) {
		System.out.print(out);
	}

	@Override
	public void finalize() throws IOException {
		stdIn.close();
	}
	
	public String[] getTabOfAllInformations(){
		String[] t = getAllInformations().split(";");
		return t;
	}

	public String getAllInformations() {

		int callOut;
		String typeRequest = listRequest[(int)(Math.random()*listRequest.length)]; // random
		String stocksNumbers = Integer.toString((int)(Math.random() * (100-1)) + 1); // "";
		String stocksPrice = Integer.toString((int)(Math.random() * (100-1)) + 1); // "";
		String stocksName = listStocks[(int)(Math.random()*listStocks.length)]; // random
		String IPSource = "CLIENT_2";
		String IPDest = "localhost"; // to modify....
		/*try {
			InetAddress thisIp = InetAddress.getLocalHost();
			IPSource = thisIp.getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("IP is not found... ");
			e.printStackTrace();
		}*/
		
		
		return IPSource + ";" + IPDest + ";" + typeRequest + ";"
				+ stocksName + ";" + stocksPrice + ";" + stocksNumbers;

	}
}
