package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	public String getAllInformations() {

		int callOut;
		String typeRequest="";
		String stocksNumbers="";
		String stocksPrice="";
		String stocksName="AAPL";

		String protocol="";
		try {

			// seller or buyer ?
			callOut = 1;
			while (callOut == 1) {
				callOut = 0;
				this.output("Do you want to buy (1) or to sell (2) ?");
				String line = stdIn.readLine();
				if (line.equals("1")) {
					typeRequest = "A"; // type request is "Adds"
				} else if (line.equals("2")) {
					typeRequest = "B"; // type request is "Bids
				} else if (line.equals("0")) {
					callOut = -1; // go out
				} 
				else {
					callOut = 1;
				}
			}
			// Number ?
			if (callOut >= 0) {
				callOut = 1;
				while (callOut == 1) {
					callOut = 0;
					this.output("How many stocks ?");
					String line = this.input();
					if (line.equals("0")) {
						callOut = -1;
					} else {
						stocksNumbers = line;
					}
				}
			}

			// price ?
			if (callOut >= 0) {
				callOut = 1;
				while (callOut == 1) {
					callOut = 0;
					this.output("How much ?");
					String line = this.input();
					if (line.equals("0")) {
						callOut = -1;
					} else {
						stocksPrice = line;
					}
				}
			}
			
		String IPSource = 	"192.168.10.1";
		String IPDest = 	"192.168.10.1";
		
		return IPSource+";"+IPDest+";"+typeRequest+";"+stocksName+";"+stocksPrice+";"+stocksNumbers+"!";
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("testError");
			e.printStackTrace();
		}

		return "";
	}
}
