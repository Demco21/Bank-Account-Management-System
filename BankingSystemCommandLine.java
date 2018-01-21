import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemCommandLine 
{
	private static BankingSystemCommandLine instance = new BankingSystemCommandLine();
	private BankingSystemController controller = new BankingSystemController();
	private static final String exitCommand = "exit";
	private Scanner scanner = new Scanner(System.in);
	private String command = new String();
	
	private BankingSystemCommandLine(){}
	
	public static BankingSystemCommandLine getInstance()
	{
		return instance;
	}
	
	public void commandLoop()
	{	
		printMenu();
		while(!command.equals(exitCommand))
		{
			command = getNextLine();
			switch(command)
			{
				case "add client": addClient();
				break;
				case "add account": addAccount();
				break;
				case "print clients": printClientList();
				break;
				case "look up client": lookUpClient();
				break;
				case "look up account": lookUpAccount();
				break;
				case "withdrawl": withdrawl();
				break;
				case "deposit": deposit();
				break;
				case "transfer": transfer();
				break;
				case "cashcheck": cashCheck();
				break;
				case "check history": getCheckHistory();
				break;
				case "close account": closeAccount();
				break;
				case "remove client": removeClient();
				break;
				case "sort clients by id": sortClientByID();
				break;
				case "sort by first name": sortClientByFirstName();
				break;
				case "sort by last name": sortClientByLastName();
				break;
				case "sort by creation date": sortClientByCreationDate();
				break;
				case "sort accounts by number": sortAccountsByNumber();
				break;
				case "sort accounts by balance": sortAccountsByBalance();
				break;
				case "menu": printMenu();
				break;
				case exitCommand: System.out.println("Goodbye.");
				break;
				case "":
				break;
				default: System.out.println("Invalid input.");
				break;
			}
		}
	}

	public String getNextLine()
	{
		String userInput = new String();
		userInput = scanner.nextLine();
		return userInput;
	}
	
	public int getNextInt()
	{
		int userInput = 0;
		try
		{
			userInput = scanner.nextInt();
			scanner.nextLine();
		}
		catch(InputMismatchException inputMismatchException)
		{
			System.err.println("Error: "+ inputMismatchException);
		}
		return userInput;
	}
	
	public double getNextDouble()
	{
		double userInput = 0.0 ;
		try
		{
			userInput = scanner.nextDouble();
			scanner.nextLine();
		}
		catch(InputMismatchException inputMismatchException)
		{
			System.err.println("Error: " + inputMismatchException);
		}
		return userInput;
	}
	
	public void addClient()
	{	
		System.out.println("Enter the client's first name.");
		String firstName = getNextLine();
		
		System.out.println("Enter the client's last name.");
		String lastName = getNextLine();
		
		System.out.println("Enter the client's address.");
		String address = getNextLine();
		
		controller.addClient(firstName, lastName, address);
		
		System.out.println("This client's ID number is: " + controller.getGUID().getLastID());
	}
	
	public void addAccount()
	{	
		System.out.println("Enter your ID");
		int clientID = getNextInt();
		
		System.out.println("Enter 'checking' to add a checking account.\nEnter 'savings' to add a savings account.");
		String acctType = getNextLine();
		
		System.out.println("If you are creating a savings account, enter the interest rate otherwise enter 0.");
		double interestRate = getNextDouble();

		if(controller.addAccount(acctType, clientID, interestRate) == true)
			System.out.println("Your account has been created.\nYour account number is " + controller.getGUID().getLastID());
		else
			System.out.println("Failed to create account.");
	}
	
	public void printClientList()
	{
		String clientList = controller.clientListToString();
		System.out.println(clientList);
	}
	
	public void lookUpClient()
	{
		System.out.println("Enter the client's ID.");
		int clientID = getNextInt();
		String clientInfo = controller.lookUpClient(clientID);
		System.out.println(clientInfo);
	}
	
	public void lookUpAccount()
	{	
		System.out.println("Enter the account number.");
		int acctNum = getNextInt();
		String acctInfo = controller.lookUpAccount(acctNum);
		System.out.println(acctInfo);
	}
	
	public void withdrawl()
	{
		System.out.println("Enter the account number");
		int acctNum = getNextInt();
		
		System.out.println("Enter the amount.");
		double amount = getNextDouble();
		
		if(controller.getAccountByNum(acctNum) == null)
			System.out.println("Account does not exist.");
		else
		{
			if(controller.withdrawl(acctNum, amount) == true)
				System.out.println("You have successfully withdrawn $" + amount);
			else
				System.out.println("Insufficient funds.");
		}
	}
	
	public void deposit()
	{
		System.out.println("Enter the account number");
		int acctNum = getNextInt();
		
		System.out.println("Enter the amount.");
		double amount = getNextDouble();
		
		if(controller.getAccountByNum(acctNum) == null)
			System.out.println("Account does not exist.");
		else
		{
			if(controller.deposit(acctNum, amount) == true)
				System.out.println("You have successfully deposited $" + amount);
			else
				System.out.println("Deposit failed.");
		}
	}
	
	public void transfer()
	{
		System.out.println("Enter the client's ID.");
		int clientID = getNextInt();
		System.out.println("Enter the account number you wish to transfer from.");
		int acctNum1 = getNextInt();
		System.out.println("Enter the account number you wish to transfer to.");
		int acctNum2 = getNextInt();
		System.out.println("Enter the amount you would like to transfer.");
		double amount = getNextDouble();
		
		if(controller.getClientByID(clientID) != null)
		{
			if(controller.transfer(clientID, acctNum1, acctNum2, amount) == true)
				System.out.println("Transfer was successful.");
			else
				System.out.println("Transfer failed. Check that the account numbers entered are correct and that you have sufficient funds.");
		}
		else
			System.out.println("Could not find the client.");
	}
	
	public void cashCheck()
	{
		System.out.println("Enter the checking account number.");
		int acctNum = getNextInt();

		if(controller.getAccountByNum(acctNum) == null)
			System.out.println("Account does not exist.");
		else if(controller.getAccountByNum(acctNum) instanceof SavingsAccount)
			System.out.println("Account entered is not a checking account.");
		else if (controller.getAccountByNum(acctNum) instanceof CheckingAccount)
		{
			System.out.println("Enter the check number.");
			int checkNumber = getNextInt();
			System.out.println("Enter the check amount.");
			Double checkAmount = getNextDouble();
		
				if(checkNumber < 0 || checkAmount < 0)
					System.out.println("Cannot cash this check.");
				else
				{
					controller.cashCheck(acctNum, checkNumber, checkAmount);
					System.out.println("Your check has been cashed.");
				}
		}
	}
	
	public void getCheckHistory()
	{	
		System.out.println("Enter the checking account number.");
		int acctNum = getNextInt();
		
		if(controller.getAccountByNum(acctNum) == null)
			System.out.println("Account does not exist.");
		else if(controller.getAccountByNum(acctNum) instanceof SavingsAccount)
			System.out.println("Cannot cash a check into a savings account.");
		else if (controller.getAccountByNum(acctNum) instanceof CheckingAccount)
			System.out.println(controller.getCheckHistory(acctNum));
	}
	
	public void closeAccount()
	{
		System.out.println("Enter the account number you wish to close.");
		int acctNum = getNextInt();
		
		if(controller.getAccountByNum(acctNum) == null)
			System.out.println("Account does not exist.");
		else
		{
			if(controller.closeAccount(acctNum) == true)
				System.out.println("Account has successfully been closed.");
			else
				System.out.println("Failed to close the account.");
		}
	}
	
	public void removeClient()
	{
		System.out.println("Enter the client ID.");
		int clientID = getNextInt();
		
		if(controller.removeClient(clientID) == true)
			System.out.println("Client has successfully been removed.");
		else
			System.out.println("Client does not exist.");

	}
	
	public void sortClientByID()
	{
		if(controller.sortByID())
			printClientList();
		else
			System.out.println("No clients.");
	}
	
	public void sortClientByFirstName()
	{	
		if(controller.sortByFirstName())
			printClientList();
		else
			System.out.println("No clients.");
	}
	
	public void sortClientByLastName()
	{	
		if(controller.sortByLastName())
			printClientList();
		else
			System.out.println("No clients.");
	}
	
	public void sortClientByCreationDate()
	{	
		if(controller.sortByCreationDate())
			printClientList();
		else
			System.out.println("No clients.");
	}
	
	public void sortAccountsByNumber()
	{
		System.out.println("Enter client ID.");
		int clientID = getNextInt();
		
		if(controller.getClientByID(clientID) == null)
			System.out.println("Client does not exist.");
		else
		{
			if(controller.sortByAccountNum(clientID) == true)
			{
				String accountList = controller.getAccountsString(clientID);
				System.out.println(accountList);
			}
			else
				System.out.println("Client has no accounts.");
		}
	}
	
	public void sortAccountsByBalance()
	{
		System.out.println("Enter client ID.");
		int clientID = getNextInt();
		
		if(controller.getClientByID(clientID) == null)
			System.out.println("Client does not exist.");
		else
		{
			if(controller.sortByAccountBalance(clientID) == true)
			{
				String accountList = controller.getAccountsString(clientID);
				System.out.println(accountList);
			}
			else
				System.out.println("Client has no accounts.");
		}
	}
	
	public void printMenu()
	{
		System.out.println("Enter 'add client' to add a client.");
		System.out.println("Enter 'add account' to add a new account for a specified client.");
		System.out.println("Enter 'print clients' to view all clients.");
		System.out.println("Enter 'look up client' to look up a client by ID.");
		System.out.println("Enter 'look up account' to look up a specific account by account number.");
		System.out.println("Enter 'withdrawl' to make a withdrawl for a specified account.");
		System.out.println("Enter 'deposit' to make a deposit for a specified account.");
		System.out.println("Enter 'transfer' to transfer money between accounts.");
		System.out.println("Enter 'cash check' to cash a check.");
		System.out.println("Enter 'check history' to obtain a check transaction history.");
		System.out.println("Enter 'close account' to close an account.");
		System.out.println("Enter 'remove client' to remove a client.");
		System.out.println("Enter 'sort clients by id' to print clients sorted by ID.");
		System.out.println("Enter 'sort by first name' to print clients sorted by their first name.");
		System.out.println("Enter 'sort by last name' to print clients sorted by their last name.");
		System.out.println("Enter 'sort by creation date' to print clients sorted by the date they were created.");
		System.out.println("Enter 'sort accounts by number' to print accounts sorted by their number.");
		System.out.println("Enter 'sort accounts by balance' to print accounts sorted by their balance.");
		System.out.println("Enter 'menu' to print the menu.");
		System.out.println("Enter 'exit' to exit the program.");
	}
}
