package examples;

public class Stock
{
	private String name;
	private int price;

	public Stock(String name, int p_price)
	{
		price =p_price;
		this.name = name;
	}
	public int getPrice()
	{
		return price;
	}

	public String getName()
	{
		return this.name;
	}

}