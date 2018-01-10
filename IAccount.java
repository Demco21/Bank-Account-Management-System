public interface IAccount 
{
	public double getBalance();
	
	public int getAccountNumber();	
	
	public boolean withdrawl(double amount);
	
	public boolean deposit(double amount);
}
