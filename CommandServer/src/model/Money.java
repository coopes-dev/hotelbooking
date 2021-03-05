package model;

/**
 * Stores a money amount uncluding currency unit
 * @author coopes
 *
 */
public class Money {
	// For most currencties this will be 100, for other's this might need to adapt
	private static int MIN_UNIT_IN_UNIT=100;
	public Money(int value) {
		super();
		this.value = value;
	}
	// Currently the currency is fixed to GBP
	// TO DO make system be able to handle range of international currencies
	private String currencyCode="GBP";	   // default currency
	private int value=0;					// amount in min unit
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		String ret="";
		ret=""+(value % MIN_UNIT_IN_UNIT);
		if (ret.length()==1) {
			ret="0"+ret;
		}
		ret=""+(value/MIN_UNIT_IN_UNIT)+"."+ret;
		return(ret);
	}
}
