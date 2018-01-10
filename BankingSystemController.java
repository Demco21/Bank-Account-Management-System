import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class BankingSystemController 
{
	private List<IClient> clients = new LinkedList<IClient>();
	private GUIDGenerator newID = new GUIDGenerator();
	
	public BankingSystemController(){}
	
	public List<IAccount> getAccounts(IClient client)
	{
		return client.getAccounts();
	}
	
	public String getAccountsString(IClient client)
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
	
	public void addClient(String firstName, String lastName, String address)
	{
		Person accountHolder = new Person(firstName, lastName, address);
		IClient newClient = new Client(accountHolder, getNewID().generateID());
		clients.add(newClient);
	}
	
	
	public String clientListToString()
	{
		String clientList = "";
		
		for(IClient c : this.clients)
			 clientList += c.toString();
		
		if(clientList.equals(""))
			clientList = "No clients.";
			
		return clientList;
	}
	
	public IClient getClientByID(int clientID)
	{
		for(IClient c : clients)
		{
			if(clientID == c.getClientID())
				return c;
		}
		
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
		{
			acctInfo = account.toString();
		}
		else
		{
			acctInfo = "Account does not exist.";
		}
		return acctInfo;
	}
	
	public IAccount getAccountByNum(int accountNum)
	{
		for(IClient client : clients)
		{
			for(IAccount acct : client.getAccounts())
			{
				if(accountNum == acct.getAccountNumber())
				{
					return acct;
				}
			}
		}
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
	
	public void cashCheck(int acctNum, int checkNumber, double checkAmount)
	{
		CheckingAccount account = (CheckingAccount) getAccountByNum(acctNum);
		Check check = new Check(checkNumber, checkAmount);
		account.cashCheck(check);
	}
	
	public boolean addCheckingAccount(int clientID)
	{
		if(getClientByID(clientID) == null) //if no client is found
		{
			return false;
		}
		IClient client = getClientByID(clientID);
		client.addCheckingAccount(this.newID.generateID());
		return true;
	}
	
	public boolean addSavingsAccount(int clientID, double interestRate)
	{
		if(getClientByID(clientID) == null) //if no client is found
		{
			return false;
		}
		IClient client = getClientByID(clientID);
		client.addSavingsAccount(this.newID.generateID(), interestRate);
		return true;
	}	
	
	public boolean closeAccount(int accountNumber)
	{
		for (IClient client: clients)
		{
			for (IAccount acct : client.getAccounts())
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
	
	public String getCheckHistory(IAccount checkingAccount)
	{
		return checkingAccount.toString();
	}
	
	public void removeClient(IClient client)
	{
		clients.remove(client);
	}
	
}
