
public class Check 
{
	private int checkNumber;
	private double amount;
	
	public Check(int checkNumber, double amount) 
	{
		super();
		this.checkNumber = checkNumber;
		this.amount = amount;
	}

	public int getCheckNumber() 
	{
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) 
	{
		this.checkNumber = checkNumber;
	}

	public double getAmount() 
	{
		return amount;
	}

	public void setAmount(double amount) 
	{
		this.amount = amount;
	}

	@Override
	public String toString() 
	{
		return "[CheckNumber=" + checkNumber + ", " + String.format("Amount=$%.2f", amount) + "]";
	}
	
}