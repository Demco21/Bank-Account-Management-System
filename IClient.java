import java.util.Date;
import java.util.List;

public interface IClient 
{
	public int getClientID();
	
	public Person getAccountHolder();
	
	public Date getCreationDate();
	
	public void removeAccount(IAccount account);
	
	public void addCheckingAccount(int accountNumber);
	
	public void addSavingsAccount(int accountNumber, double interestRate);
	
	public List<IAccount> getAccounts();
	
	public String accountsString();
}
