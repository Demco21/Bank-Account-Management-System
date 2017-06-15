
public class SavingsAccount extends Account 
{
	private double interestRate;

	public SavingsAccount()
	{
		super();
	}

	public SavingsAccount(int accountNumber, double interestRate) 
	{
		super(accountNumber);
		this.interestRate = interestRate;
	}

	public double getInterestRate()
	{
		return this.interestRate;
	}

	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}

	@Override
	public String toString()
	{
		String savingsAccount = "SavingsAccount:  ";
		return String.format("%s", savingsAccount) + super.toString() + String.format(", InterestRate=%3.2f", interestRate) + "%\n";
	}
	
}
