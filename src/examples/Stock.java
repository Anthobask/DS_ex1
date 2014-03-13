package examples;

import java.util.Random;

public class Stock
{
	private String name;
	private int price;

	Stock(String name)
	{
		price = random_in_range(1, 100);
		this.name = name;
	}

	/**
	 * Update price at random.
	 * @return Updated price.
	 */
	public float ticker()
	{
		float multiplier = random_in_range(1.0f - volatility, 1.0f + volatility);
		price *= multiplier;
		return price;
	}

	public float getVolatility()
	{
		return volatility;
	}

	public float getPrice()
	{
		return price;
	}

	public String getName()
	{
		return this.name;
	}

	public boolean isRightName(String name)
	{
		return (name.equals(this.name));
	}

	private static Random random = new Random();

	public static float random_in_range(float min, float max)
	{
		return (min + ((max-min) * random.nextFloat()));
	}
}