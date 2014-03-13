package examples;

import java.util.ArrayList;

public class Singleton {

	
	 private static Singleton instance = null;

	    /**
	     * @param aInstance the instance to set
	     */
	    public static void setInstance(Singleton aInstance) {
	        instance = aInstance;
	    }
	    public ArrayList<Stock> listeStocks;
	    public ArrayList<Stock> ListActionToSell;
	    public ArrayList<Offer> listTradders;
	    
	    
	    public static Singleton getInstance()
	    {
	        if(instance == null)
	        {
	            instance = new Singleton();
	        }
	        return instance;
	    }

	    private Singleton()
	    {
	      ListActionToSell = new ArrayList<Stock>();
	      listTradders = new ArrayList<Offer>();
	      listeStocks = new ArrayList<Stock>();
	      listeStocks.add(new Stock("AAPL", 500));
	      listeStocks.add(new Stock("MSFT", 30));
	      listeStocks.add(new Stock("ORCL", 20));
	      listeStocks.add(new Stock("IBM", 28));
	    }
}
