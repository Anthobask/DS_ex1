package examples;

public class Offer {
	private String m_identity;
	private String m_stockType;
	private String m_quantity;
	private String m_stockName;
	private String m_price;

	public Offer() {

	}

	public String getM_identity() {
		return m_identity;
	}

	public void setM_identity(String m_identity) {
		this.m_identity = m_identity;
	}

	public String getM_stockType() {
		if (m_stockType == "B") {
			return "to sell";
		}
		if (m_stockType == "A") {
			return "to buy";
		}else{
			return "Must be A or B";
		}
	}

	public void setM_stockType(String m_stockType) {
		this.m_stockType = m_stockType;
	}

	public String getM_quantity() {
		return m_quantity;
	}

	public void setM_quantity(String m_quantity) {
		this.m_quantity = m_quantity;
	}

	public String getM_stockName() {
		return m_stockName;
	}

	public void setM_stockName(String m_stockName) {
		this.m_stockName = m_stockName;
	}

	public String getM_price() {
		return m_price;
	}

	public void setM_price(String m_price) {
		this.m_price = m_price;
	}

}
