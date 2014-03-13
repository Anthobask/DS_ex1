package examples;


import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class MyWebServer extends Thread {
	
	private final int port = 8080;
	
		public void run() {
		    try {

		      WebServer webServer = new WebServer(port);

		      XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
		      PropertyHandlerMapping phm = new PropertyHandlerMapping();

		      phm.addHandler( "StockProcedure", StockProcedure.class);
		      xmlRpcServer.setHandlerMapping(phm);		    
		     XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
		      serverConfig.setEnabledForExtensions(true);
		      serverConfig.setContentLengthOptional(false);

		      webServer.start();

		      System.out.println("The Web Server has been started..." );

		    } catch (Exception exception) {
		       System.err.println("JavaServer: " + exception);
		    }
		  }
}
