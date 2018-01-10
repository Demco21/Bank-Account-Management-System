
public class Account implements IAccount
{
	private int accountNumber = 0;
	private double balance = 0.0;
	
	public Account(){}
	
	public Account(int accountNumber) 
	{
		super();
		this.accountNumber = accountNumber;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	
	public boolean withdrawl(double amount)
	{
		if(amount <= balance && amount > 0)
		{
			balance -= amount;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean deposit(double amount)
	{
		if(amount > 0)
		{
			balance += amount;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String toString() 
	{
		return String.format("AccountNumber=%d, ", accountNumber) + String.format("Balance=$%.2f" , balance);
	}
	
}
