import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class BankingSystemController 
{
	private List<Client> clients = new LinkedList<Client>();
	private GUIDGenerator newID = new GUIDGenerator();
	
	public BankingSystemController() 
	{

	}
	
	public List<Account> getAccounts(Client client)
	{
		return client.getAccounts();
	}
	
	public String getAccountsString(Client client)
	{
		return client.accountsString();
	}
	
	public void sortByAccountNum(IClient client)
	{
		Collections.sort(client.getAccounts(), new AccountNumberComparator());
	}
	
	public void sortByAccountBalance(IClient client)
	{
		Collections.sort(client.getAccounts(), new AccountBalanceComparator());
	}
	
	public void sortByID()
	{
		Collections.sort(clients, new ClientIDComparator());
	}
	
	public void sortByFirstName()
	{
		Collections.sort(clients, new ClientFirstNameComparator());
	}
	
	public void sortByLastName()
	{
		Collections.sort(clients, new ClientLastNameComparator());
	}
	
	public void sortByCreationDate()
	{
		Collections.sort(clients, new ClientCreationDateComparator());
	}
	
	public GUIDGenerator getNewID()
	{
		return this.newID;
	}
	
	public void addClient(Person accountHolder, int clientID)
	{
		Client newClient = new Client(accountHolder, clientID);
		clients.add(newClient);
	}
	
	public String getClientListString()
	{
		String retval = "";
		for(Client c : this.clients)
		{
			 retval += c.toString();
		}
		return retval;
	}
	
	public Client getClientByID(int id)
	{
		for(Client c : clients)
		{
			if(id == c.getClientID())
			{
				return c;
			}
		}
		return null;
	}
	
	public Account getAccountByNum(int accountNum)
	{
		for(Client client : clients)
		{
			for(Account acct : client.getAccounts())
			{
				if(accountNum == acct.getAccountNumber())
				{
					return acct;
				}
			}
		}
		return null;
	}
	
	public boolean deposit(Account account, double amount)
	{
		if(account.deposit(amount) == true)
			return true;
		else
			return false;
	}
	
	public boolean withdrawl(Account account, double amount)
	{
		if(account.withdrawl(amount) == true)
			return true;
		else
			return false;
	}
	
	public void cashCheck(Check check, CheckingAccount account)
	{
		account.cashCheck(check);
	}
	
	public void addCheckingAccount(Client client)
	{
		client.addCheckingAccount(this.newID.generateID());
	}
	
	public void addSavingsAccount(Client client, double interestRate)
	{
		client.addSavingsAccount(this.newID.generateID(), interestRate);
	}	
	
	public boolean closeAccount(int accountNumber)
	{
		for (Client client: clients)
		{
			for (Account acct : client.getAccounts())
			{
				if(accountNumber == acct.getAccountNumber())
				{
					client.removeAccount(acct);
					return true;
				}
			}
		}
		return false;
	}
	
	public String getCheckHistory(Account checkingAccount)
	{
		return checkingAccount.toString();
	}
	
	public void removeClient(Client client)
	{
		clients.remove(client);
	}
	
}
