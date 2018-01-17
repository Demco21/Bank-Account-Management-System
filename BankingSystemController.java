import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import hashtable.HashTable;

public class BankingSystemController 
{
	private List<IClient> clients = new LinkedList<IClient>(); //used for sorting algorithms
	private HashTable<Integer, IClient> clientTable = new HashTable<Integer, IClient>(10); //used to look up clients
	private HashTable<Integer, IAccount> accountTable = new HashTable<Integer, IAccount>(10); //used to look up accounts
	private GUIDGenerator guid = new GUIDGenerator();
	
	public BankingSystemController(){}
	
	public List<IAccount> getAccounts(IClient client)
	{
		return client.getAccounts();
	}
	
	public String getAccountsString(int clientID)
	{
		IClient client = getClientByID(clientID);
		return client.clientAccountsToString();
	}
	
	public boolean sortByAccountNum(int clientID)
	{
		IClient client = getClientByID(clientID);
		
		if(client.getAccounts().isEmpty())
			return false;
		Collections.sort(client.getAccounts(), new AccountNumberComparator());
		return true;
	}
	
	public boolean sortByAccountBalance(int clientID)
	{
		IClient client = getClientByID(clientID);
		
		if(client.getAccounts().isEmpty())
			return false;
		Collections.sort(client.getAccounts(), new AccountBalanceComparator());
		return true;
	}
	
	public boolean sortByID()
	{
		if(!clients.isEmpty())
			Collections.sort(clients, new ClientIDComparator());
		else
			return false;
		return true;
	}
	
	public boolean sortByFirstName()
	{
		if(!clients.isEmpty())
			Collections.sort(clients, new ClientFirstNameComparator());
		else
			return false;
		return true;
	}
	
	public boolean sortByLastName()
	{
		if(!clients.isEmpty())
			Collections.sort(clients, new ClientLastNameComparator());
		else
			return false;
		return true;
	}
	
	public boolean sortByCreationDate()
	{
		if(!clients.isEmpty())
			Collections.sort(clients, new ClientCreationDateComparator());
		else
			return false;
		return true;
	}
	
	public GUIDGenerator getGUID()
	{
		return this.guid;
	}
	
	public void addClient(String firstName, String lastName, String address)
	{
		ClientFactory clientFactory = new ClientFactory();
		IClient client = clientFactory.createClient(firstName, lastName, address, this.guid.generateID());
		clientTable.add(client.getClientID(), client);
		clients.add(client);
	}
	
	public String clientListToString()
	{
		String clientList = new String();
		
		for(IClient c : this.clients)
			 clientList += c.toString();
		
		if(clientList.isEmpty())
			clientList = "No clients.";
			
		return clientList;
	}
	
	public IClient getClientByID(int clientID)
	{
		if(clientTable.get(clientID) != null)
			return clientTable.get(clientID);
		else
			return null;
	}
	
	public String lookUpClient(int clientID)
	{
		String clientInfo;
		IClient client = getClientByID(clientID);
		if(client != null)
		{
			clientInfo = client.toString();
		}
		else
		{
			clientInfo = "Client does not exist";
		}
		return clientInfo;
	}
	
	public String lookUpAccount(int acctNum)
	{
		String acctInfo;
		IAccount account = getAccountByNum(acctNum);
		
		if(account != null)
			acctInfo = account.toString();
		else
			acctInfo = "Account does not exist.";
		
		return acctInfo;
	}
	
	public IAccount getAccountByNum(int accountNum)
	{
		if(accountTable.get(accountNum) != null)
			return accountTable.get(accountNum);
		else
			return null;
	}
	
	public boolean deposit(int acctNum, double amount)
	{
		IAccount account = getAccountByNum(acctNum);
		
		if(account.deposit(amount) == true)
			return true;
		else
			return false;
	}
	
	public boolean withdrawl(int acctNum, double amount)
	{
		IAccount account = getAccountByNum(acctNum);
		
		if(account.withdrawl(amount) == true)
			return true;
		else
			return false;
	}
	
	public boolean transfer(int clientID, int acctNum1, int acctNum2, double amount)
	{
		IClient client = getClientByID(clientID);
		IAccount acct1 = null;
		IAccount acct2 = null;
		for(IAccount account : client.getAccounts())
		{
			if(acctNum1 == account.getAccountNumber())
				acct1 = account;
			if(acctNum2 == account.getAccountNumber())
				acct2 = account;
		}
		if(acct1 != null && acct2 != null)
		{
			if(acct1.withdrawl(amount) == true)
				if(acct2.deposit(amount) == true)
					return true;
		}
		return false;
	}
	
	public boolean cashCheck(int acctNum, int checkNumber, double checkAmount)
	{
		CheckFactory checkFactory = new CheckFactory();
		IAccount account = getAccountByNum(acctNum);
		Check check = checkFactory.createCheck(checkNumber, checkAmount);
		if(account instanceof CheckingAccount && check != null) 
		{
			((CheckingAccount)account).cashCheck(check);
			return true;
		}
		return false;
	}
	
	public boolean addAccount(String type, int clientID, double interestRate)
	{	
		AccountFactory accountFactory = new AccountFactory();
		IClient client = getClientByID(clientID);

		if(accountFactory.createAccount(type, client, this.guid.generateID(), interestRate) == null)
			return false;
		return true;
	}

	public boolean closeAccount(int accountNum)
	{
		IAccount account = accountTable.get(accountNum); //the account to close
		int clientID = account.getClientID(); //the client ID
		IClient client = clientTable.get(clientID); // the client
		
		if(account != null && client != null)
		{
			client.removeAccount(account);
			accountTable.remove(accountNum);
			return true;
		}
		return false;
	}
	
	public String getCheckHistory(int acctNum)
	{
		IAccount account = getAccountByNum(acctNum);
		if(account instanceof CheckingAccount)
			return account.toString();
		else
			return null;
	}
	
	public boolean removeClient(int clientID)
	{
		IClient client = getClientByID(clientID);
		if(client == null)
			return false;
		else
		{
			clients.remove(client);
			clientTable.remove(clientID);
			return true;
		}
	}
}
