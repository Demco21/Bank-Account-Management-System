public interface IAccount 
{
	public int getClientID();
	
	public double getBalance();
	
	public int getAccountNumber();	
	
	public boolean withdrawl(double amount);
	
	public boolean deposit(double amount);
}
