package system;

public class AccountFactory 
{
	private final String SAVINGS_ACCOUNT = "savings";
	private final String CHECKING_ACCOUNT = "checking";
	
	public IAccount createAccount(String type, IClient client, int accountNumber, double interestRate)
	{
		if(type.equals(SAVINGS_ACCOUNT) && client != null && interestRate > 0)
		{
			client.addSavingsAccount(accountNumber, interestRate);
			return client.getAccount(accountNumber);
		}
		if(type.equals(CHECKING_ACCOUNT) && client != null)
		{
			client.addCheckingAccount(accountNumber);
			return client.getAccount(accountNumber);
		}
		return null;
	}
}
