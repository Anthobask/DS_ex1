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
	    private ArrayList<Stock> listeStocks;
	    private ArrayList<Stock> ListActionToSell;
	    private ArrayList<Offer> listTradders;
	    //private ReentrantReadWriteLock reentrant;
	    //private Lock lockListeStockWrite;
	    //private Lock lockListeTradWrite;

	    

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
	      listTradders = new ArrayList<Tradder>();
	      listeStocks = new ArrayList<Stock>();
	      //reentrant = new ReentrantReadWriteLock();
	      //lockListeStockWrite = reentrant.writeLock();
	      //lockListeTradWrite = reentrant.writeLock();
	      listeStocks.add(new Stock("Stock1"));
	      listeStocks.add(new Stock("Stock2"));
	      listeStocks.add(new Stock("Stock3"));
	      listeStocks.add(new Stock("Stock4"));
	      listeStocks.add(new Stock("Stock5"));
	    }

	    public void addStockToSell(String stockName)
	    {
	        //lockListeTradWrite.lock();
	        this.ListActionToSell.add(new Stock(stockName));
	        //lockListeStockWrite.unlock();
	    }

	    public void removeStockToSell(String stockName)
	    {
	        Stock s = searchStockToSellByName(stockName);
	        if(s != null)
	        {
	           // lockListeStockWrite.lock();
	            this.ListActionToSell.remove(s);
	            //lockListeStockWrite.unlock();
	        }
	    }

	    private Stock searchStockToSellByName(String stockName)
	    {
	        //lockListeStockRead.lock();
	        for(Stock s : this.ListActionToSell)
	            if(s.getName().equals(stockName))
	                return s;
	        //lockListeStockRead.unlock();
	        return null;
	    }

	    public void addTradder(String nomTrad)
	    {
	        //lockListeTradWrite.lock();
	        this.listTradders.add(new Tradder(nomTrad, 100));
	        //lockListeTradWrite.unlock();
	    }

	    public void addTradder(Tradder trad)
	    {
	        //lockListeTradWrite.lock();
	        this.listTradders.add(trad);
	        //lockListeTradWrite.unlock();
	    }

	    public void removeTradder(Tradder trad)
	    {
	        if(this.listTradders.contains(trad))
	        {
	          //  lockListeTradWrite.lock();
	            this.listTradders.remove(trad);
	            //lockListeTradWrite.unlock();
	        }
	    }

	    public void removeTradder(String nomTrad)
	    {
	        Tradder t = searchTradderByName(nomTrad);
	        if(t != null)
	        {
	            //lockListeTradWrite.lock();
	            listTradders.remove(t);
	            //lockListeTradWrite.unlock();
	        }
	    }

	    public Tradder searchTradderByName(String tradName)
	    {
	        //lockListeTradRead.lock();
	        for(Tradder t : this.listTradders)
	            if(t.getName().equals(tradName))
	                return t;
	        //lockListeTradRead.unlock();
	        return null;
	    }

	    public void updatePrice()
	    {
	        for(Stock s : listeStocks)
	                s.ticker();
	     }

	    /**
	     * @return the listeStocks
	     */
	    public ArrayList<Stock> getListeStocks() {
	        return listeStocks;
	    }

	    /**
	     * @param listeStocks the listeStocks to set
	     */
	    public void setListeStocks(ArrayList<Stock> listeStocks) {
	        this.listeStocks = listeStocks;
	    }

	    /**
	     * @return the ListActionToSell
	     */
	    public ArrayList<Stock> getListActionToSell() {
	        return ListActionToSell;
	    }

	    /**
	     * @return the listTradders
	     */
	    public ArrayList<Tradder> getListTradders() {
	        return listTradders;
	    }

}
