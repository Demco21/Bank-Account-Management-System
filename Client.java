import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Client implements IClient
{
	private Person accountHolder;
	private int clientID;
	private Date creationDate;
	private List<IAccount> accounts = new LinkedList<IAccount>();
	
	public Client(){}

	public Client(Person accountHolder, int clientID) 
	{
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
	
	public IAccount getAccount(int accountNum)
	{
		for(IAccount account: accounts)
			if(account.getAccountNumber() == accountNum)
				return account;
		return null;
	}
	
	public List<IAccount> getAccounts()
	{
		return this.accounts;
	}

	public IAccount addCheckingAccount(int accountNumber)
	{
		IAccount newAccount = new CheckingAccount(accountNumber, this.clientID);
		this.accounts.add(newAccount);
		return newAccount;
	}
	
	public IAccount addSavingsAccount(int accountNumber, double interestRate)
	{
		IAccount newAccount = new SavingsAccount(accountNumber, this.clientID, interestRate);
		this.accounts.add(newAccount);
		return newAccount;
	}
	
	public void removeAccount(IAccount account)
	{
		this.accounts.remove(account);
	}
	
	public String clientAccountsToString()
	{
		String accountsInfo = new String();
		for(IAccount account: this.accounts)
		{
			accountsInfo += account.toString();
		}
		return accountsInfo;
	}
	
	@Override
	public String toString() 
	{
		if(this.accounts.isEmpty() == true)
			return "\nClient: " + accountHolder + ", ID=" + clientID + "\n*Client has no accounts*\n" + "Created on " + creationDate + "\n" + "-----------------------------------------------------------";
		else
			return "\nClient: " + accountHolder + ", ID=" + clientID + "\n" + this.clientAccountsToString() + "Created on " + creationDate + "\n"+ "-----------------------------------------------------------";
	}
}
