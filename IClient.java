import java.util.Date;
import java.util.List;

public interface IClient 
{
	public int getClientID();
	
	public Person getAccountHolder();
	
	public Date getCreationDate();
	
	public void removeAccount(IAccount account);
	
	public IAccount addCheckingAccount(int accountNumber);
	
	public IAccount addSavingsAccount(int accountNumber, double interestRate);
	
	public IAccount getAccount(int accountNum);
	
	public List<IAccount> getAccounts();
	
	public String clientAccountsToString();
}
