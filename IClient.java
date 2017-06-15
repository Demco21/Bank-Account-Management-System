import java.util.Date;
import java.util.List;

public interface IClient 
{
	public int getClientID();
	
	public Person getAccountHolder();
	
	public Date getCreationDate();
	
	public List<Account> getAccounts();
}
