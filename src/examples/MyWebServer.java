package examples;


import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class MyWebServer extends Thread {
	private Stock s;
	private EchoService e;
	
	public MyWebServer(){
		
	}
	
	public int getPrice(String name){
		for(Stock s : Singleton.getInstance().listeStocks)
		{
			if(s.getName().equals(name))
			{
				return s.getPrice();
			}
		}
		return 0;
	}
	
	private final int port = 6666;
	
		public void run() {
		    try {

		      WebServer webServer = new WebServer(port);

		      XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
		      PropertyHandlerMapping phm = new PropertyHandlerMapping();

		      phm.addHandler( "StockProcedure", MyWebServer.class);
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
