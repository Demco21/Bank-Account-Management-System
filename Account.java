public abstract class Account implements IAccount
{
	private int accountNumber;
	private int clientID;
	private double balance = 0.0;
	
	public Account(){}
	
	public Account(int accountNumber, int clientID) 
	{
		super();
		this.accountNumber = accountNumber;
		this.clientID = clientID;
	}
	
	public int getClientID()
	{
		return clientID;
	}
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	
	public double getBalance()
	{
		return balance;
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
