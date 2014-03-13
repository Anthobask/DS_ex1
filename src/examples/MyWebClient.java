package examples;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class MyWebClient {
	private UserInterface userInterface;
	private String[] userInformationStrings;

	public MyWebClient() {

	}

	public void runClient() throws XmlRpcException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
			config.setServerURL(new URL("http://localhost:8080/xmlrpc"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		userInterface = new UserInterface();

		userInformationStrings = userInterface.getTabOfAllInformations();

		Object[] params = new Object[] { userInformationStrings[0],
				userInformationStrings[1], userInformationStrings[2],
				userInformationStrings[3], userInformationStrings[4],
				userInformationStrings[5] };
		System.out.println("About to get results...(params[0] = " + params[0]
				+ ", params[1] = " + params[1] + ").");
	}
}
