import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Client implements IClient
{
	private Person accountHolder;
	private int clientID;
	private Date creationDate;
	private List<Account> accounts = new LinkedList<Account>();
	
	public Client()
	{

	}

	public Client(Person accountHolder, int clientID) 
	{
		super();
		this.accountHolder = accountHolder;
		this.clientID = clientID;
		this.creationDate = new Date();
	}
	
	public Person getAccountHolder() 
	{
		return accountHolder;
	}

	public int getClientID() 
	{
		return clientID;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}
	
	public List<Account> getAccounts()
	{
		return this.accounts;
	}

	public void addCheckingAccount(int accountNumber)
	{
		CheckingAccount newAccount = new CheckingAccount(accountNumber);
		this.accounts.add(newAccount);
	}
	
	public void addSavingsAccount(int accountNumber, double interestRate)
	{
		SavingsAccount newAccount = new SavingsAccount(accountNumber, interestRate);
		this.accounts.add(newAccount);
	}
	
	public void removeAccount(Account account)
	{
		this.accounts.remove(account);
	}
	
	public String accountsString()
	{
		String retval = new String();
		for(Account account: this.accounts)
		{
			retval += account.toString();
		}
		return retval;
	}
	
	@Override
	public String toString() 
	{
		if(this.accounts.isEmpty() == true)
			return "\nClient: " + accountHolder + ", ID=" + clientID + "\n*Client has no accounts*\n" + "Created on " + creationDate + "\n" + "-----------------------------------------------------------";
		else
			return "\nClient: " + accountHolder + ", ID=" + clientID + "\n" + this.accountsString() + "Created on " + creationDate + "\n"+ "-----------------------------------------------------------";
	}
	
}