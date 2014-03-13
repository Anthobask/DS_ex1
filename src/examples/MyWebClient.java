package examples;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class MyWebClient {
	private static UserInterface userInterface;
	private static String[] userInformationStrings;

	 public static void main(String[] args) throws Exception {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
			config.setServerURL(new URL("http://127.0.0.1:6666/xmlrpc"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		userInterface = new UserInterface();

		userInformationStrings = userInterface.getTabOfAllInformations();

//		Object[] params = new Object[] { userInformationStrings[0],
//				userInformationStrings[1], userInformationStrings[2],
//				userInformationStrings[3], userInformationStrings[4],
//				userInformationStrings[5] };
		Object[] params = new Object[]{userInformationStrings[3]};
		//System.out.println("toto");
//		System.out.println("About to get results...(params[0] = " + params[0]
//			 ", params[1] = " + params[1] + ").");
		System.out.println(client.execute("StockProcedure.getPrice",params));
		
	}
}
