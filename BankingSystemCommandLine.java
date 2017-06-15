import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemCommandLine 
{
	private static BankingSystemCommandLine instance = null;
	private BankingSystemController controller = new BankingSystemController();
	private static final String exitCommand = "exit";
	private Scanner getInput = new Scanner(System.in);
	private String inputString = "";
	private String commandString = "";
	private int commandInt;
	private double commandDouble;
	
	private BankingSystemCommandLine()
	{
		
	}
	
	public static BankingSystemCommandLine getInstance()
	{
		if(instance == null)
		{
			instance = new BankingSystemCommandLine();
			return instance;
		}
		else
		{
			System.err.println("Can only have one instance of BankingSystemCommandLine.");
			return null;
		}
	}
	
	public void commandLoop()
	{	
		this.printMenu();
		
		while(inputString.equals(exitCommand) == false)
		{
			this.resetCommand();
			this.promptUserString();

			if(getCommandString().equals("addclient"))
			{				
				this.addClient();
			}
			else if(getCommandString().equals("addaccount"))
			{	
				this.addAccount();
			}
			else if(getCommandString().equals("printclients"))
			{
				this.printClientList();
			}
			else if(getCommandString().equals("lookupclient"))
			{
				this.LookUpClient();
			}
			else if (getCommandString().equals("lookupaccount"))
			{
				this.lookUpAccount();
			}
			else if(getCommandString().equals("withdrawl"))
			{
				this.withdrawl();
			}
			else if(getCommandString().equals("deposit"))
			{
				this.deposit();
			}
			else if(getCommandString().equals("transfer"))
			{
				this.transfer();
			}
			else if(getCommandString().equals("cashcheck"))
			{
				this.cashCheck();
			}
			else if(getCommandString().equals("checkhistory"))
			{
				this.getCheckHistory();
			}
			else if(getCommandString().equals("closeaccount"))
			{
				this.closeAccount();
			}
			else if(getCommandString().equals("removeclient"))
			{
				this.removeClient();
			}
			else if(getCommandString().equals("sortclientsbyid"))
			{
				this.sortClientByID();
			}
			else if(getCommandString().equals("sortbyfirstname"))
			{
				this.sortClientByFirstName();
			}
			else if(getCommandString().equals("sortbylastname"))
			{
				this.sortClientByLastName();
			}
			else if(getCommandString().equals("sortbycreationdate"))
			{
				this.sortClientByCreationDate();
			}
			else if(getCommandString().equals("sortaccountsbynumber"))
			{
				this.sortAccountsByNumber();
			}
			else if(getCommandString().equals("sortaccountsbybalance"))
			{
				this.sortAccountsByBalance();
			}
			else if(getCommandString().equals("menu"))
			{
				this.printMenu();
			}
			else if(getCommandString().equals("exit"))
			{
				System.out.println("Goodbye, Andrew.");
			}
			else if(getCommandString().equals(""))//So program does not print 'invalid input' when promptUserString() is not being used
			{
				
			}
			else
			{
				System.out.print("Invalid input.");
			}
		}
	}
	
	public void promptUserString() //Stores user input as lower case string with no spaces in command field
	{
		this.inputString = this.getInput.nextLine();
		
		for (String retval: this.inputString.split(" "))
		{
	         this.commandString += retval;
	    }
		
		this.commandString = this.commandString.toLowerCase();
	}
	
	public void promptUserInt() //Stores user input as an integer
	{
		try
		{
			this.commandInt = this.getInput.nextInt();
		}
		catch(InputMismatchException inputMismatchException)
		{
			System.err.println("Error: InputMismatchException");
		}
	}
	
	public void promptUserDouble() //Stores user input as a double
	{
		try
		{
			this.commandDouble = this.getInput.nextDouble();
		}
		catch(InputMismatchException inputMismatchException)
		{
			System.err.println("Error: InputMismatchException\n");
		}
	}
	
	public int getCommandInt() //returns user input integer
	{
		return commandInt;
	}
	
	public double getCommandDouble() //returns user input double
	{
		return this.commandDouble;
	}
	
	public String getCommandString() //returns user input string
	{
		return this.commandString;
	}
	
	public void resetCommand()
	{
		this.commandString = "";
		this.commandInt = -1;
		this.commandDouble = -1;
	}
	
	public void addClient()
	{	
		String firstName = "";
		String lastName = "";
		String address = "";
		
		System.out.println("Enter the client's first name.");
		firstName = this.getInput.nextLine();
		
		System.out.println("Enter the client's last name.");
		lastName = this.getInput.nextLine();
		
		System.out.println("Enter the client's address.");
		address = this.getInput.nextLine();
		
			Person newPerson = new Person(firstName, lastName, address);
		
			controller.addClient(newPerson, this.controller.getNewID().generateID());
		
			System.out.println("This client's ID number is: " + controller.getNewID().getLastID());
	}
	
	public void addAccount()
	{
		System.out.println("Enter 'checking' to add a checking account");
		System.out.println("Enter 'savings' to add a savings account");
		this.resetCommand();
		this.promptUserString();

		if(getCommandString().equals("checking"))
		{
			System.out.println("What is your ID");
			this.resetCommand();
			this.promptUserInt();
			
			if(controller.getClientByID(getCommandInt()) == null) //if no client is found
			{
				System.out.println("Client does not exist.");
			}
			else
			{
			controller.addCheckingAccount(controller.getClientByID(getCommandInt())); //search for client and add checking account
		
			System.out.println("Your checking account number is " + controller.getNewID().getLastID());
			}
		}
		else if(getCommandString().equals("savings"))
		{	
			double interest;
			
			System.out.println("Enter an interest rate");
			this.resetCommand();
			this.promptUserDouble();
			
			interest = getCommandDouble();
			
			System.out.println("What is your ID");
			this.resetCommand();
			this.promptUserInt();
			
			if(controller.getClientByID(getCommandInt()) == null)
			{
				System.out.println("Client does not exist.");
			}
			else
			{
			controller.addSavingsAccount(controller.getClientByID(getCommandInt()), interest); //search for client and add savings account
			System.out.println("Your savings account number is " + controller.getNewID().getLastID());
			}
			interest = 0.0;
		}
		else
		{
			System.out.println("Invalid Input.");
		}
	}
	
	public void printClientList()
	{
		if(controller.getClientListString().equals(""))
		{
			System.out.println("No clients.");
		}
		else
		{
			System.out.println(controller.getClientListString());
		}
	}
	
	public void LookUpClient()
	{
		System.out.println("Enter the client's ID.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getClientByID(getCommandInt()) == null)
		{
			System.out.println("Client does not exist.");
		}
		else
		{
		System.out.println(controller.getClientByID(getCommandInt()).toString());
		}
	}
	
	public void lookUpAccount()
	{	
		System.out.println("Enter the account number.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else
		{
			System.out.println(controller.getAccountByNum(getCommandInt()).toString()); //searches for account and prints
		}
	}
	
	public void withdrawl()
	{
		double amount;
		
		System.out.println("Enter the amount.");
		this.resetCommand();
		this.promptUserDouble();
		
		amount = getCommandDouble();
		
		System.out.println("Enter the account number");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else
		{
			if(controller.withdrawl(controller.getAccountByNum(getCommandInt()), amount) == true)
				System.out.println("You have successfully withdrawn $" + amount);
			else
				System.out.println("Insufficient funds.");
		}
		amount = 0.0;
	}
	
	public void deposit()
	{
		double amount;
		
		System.out.println("Enter the amount.");
		this.resetCommand();
		this.promptUserDouble();
		
		amount = getCommandDouble();
		
		System.out.println("Enter the account number");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else
		{
			controller.deposit(controller.getAccountByNum(getCommandInt()), amount);
			System.out.println("You have successfully deposited $" + amount);
		}
		amount = 0.0;
	}
	
	public void transfer()
	{
		System.out.println("Enter '1' to transfer from checking to savings.");
		System.out.println("Enter '2' to transfer from savings to checking.");
		this.resetCommand();
		this.promptUserInt();
		
		if(getCommandInt() == 1)
		{
			System.out.println("Enter the checking account number you would like to transfer from");
			this.resetCommand();
			this.promptUserInt();
			
			if(controller.getAccountByNum(getCommandInt()) == null)
			{
				System.out.println("Account does not exist.");
			}
			else if(controller.getAccountByNum(getCommandInt()) instanceof SavingsAccount)
			{
				System.out.println("Account entered is not a checking account.");
			}
			else if(controller.getAccountByNum(getCommandInt()) instanceof CheckingAccount)
			{
				int checkingAccountNum = getCommandInt();
				
				System.out.println("Enter the savings account number you would like to transfer to.");
				this.resetCommand();
				this.promptUserInt();
				
				if(controller.getAccountByNum(getCommandInt()) == null)
				{
					System.out.println("Account does not exist.");
				}
				else if(controller.getAccountByNum(getCommandInt()) instanceof CheckingAccount)
				{
					System.out.println("Account entered is not a savings account.");
				}
				else if(controller.getAccountByNum(getCommandInt()) instanceof SavingsAccount)
				{
					int savingsAccountNum = getCommandInt();
					
					System.out.println("Enter the amount you would like to transfer.");
					this.resetCommand();
					this.promptUserDouble();
					
					if(this.controller.withdrawl(this.controller.getAccountByNum(checkingAccountNum), getCommandDouble()) == true)
					{
						this.controller.deposit(this.controller.getAccountByNum(savingsAccountNum), getCommandDouble());
						System.out.println("Transfer was successful.");
					}
					else
					{
						System.out.println("Insufficient funds.");
					}
				}
				
			}
			
		}
		else if(getCommandInt() == 2)
		{
			System.out.println("Enter the savings account number you would like to transfer from");
			this.resetCommand();
			this.promptUserInt();
			
			if(controller.getAccountByNum(getCommandInt()) == null)
			{
				System.out.println("Account does not exist.");
			}
			else if(controller.getAccountByNum(getCommandInt()) instanceof CheckingAccount)
			{
				System.out.println("Account entered is not a savings account.");
			}
			else if(controller.getAccountByNum(getCommandInt()) instanceof SavingsAccount)
			{
				int savingsAccountNum = getCommandInt();
				
				System.out.println("Enter the checking account number you would like to transfer to.");
				this.resetCommand();
				this.promptUserInt();
				
				if(controller.getAccountByNum(this.getCommandInt()) == null)
				{
					System.out.println("Account does not exist.");
				}
				else if(controller.getAccountByNum(this.getCommandInt()) instanceof SavingsAccount)
				{
					System.out.println("Account entered is not a checking account.");
				}
				else if(controller.getAccountByNum(this.getCommandInt()) instanceof CheckingAccount)
				{
					int checkingAccountNum = getCommandInt();
					
					System.out.println("Enter the amount you would like to transfer.");
					this.resetCommand();
					this.promptUserDouble();
					
					if(this.controller.withdrawl(this.controller.getAccountByNum(savingsAccountNum), getCommandDouble()) == true)
					{
						this.controller.deposit(this.controller.getAccountByNum(checkingAccountNum), getCommandDouble());
						System.out.println("Transfer was successful.");
					}
					else
					{
						System.out.println("Insufficient funds.");
					}
				}
				
			}
		}
		else
		{
			System.out.println("Invalid input.");
		}
	}
	
	public void cashCheck()
	{	
		System.out.println("Enter the checking account number.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(this.getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else if(controller.getAccountByNum(this.getCommandInt()) instanceof SavingsAccount)
		{
			System.out.println("Account entered is not a checking account.");
		}
		else
		{
		int accountNum = getCommandInt();
		
		System.out.println("Enter the check number.");
		this.resetCommand();
		this.promptUserInt();
		
		int checkNumber = getCommandInt();
		
		System.out.println("Enter the check amount.");
		this.resetCommand();
		this.promptUserDouble();
		
		Double checkAmount = getCommandDouble();
		
			if(checkNumber < 0 || checkAmount < 0)
			{
				System.out.println("Cannot cash this check.");
			}
			else
			{
				CheckingAccount checkingAccount = (CheckingAccount) controller.getAccountByNum(accountNum);
		
				Check newCheck = new Check(checkNumber, checkAmount);
				controller.cashCheck(newCheck, checkingAccount);
		
				System.out.println("Your check has been cashed.");
			}
		}
	}
	
	public void getCheckHistory()
	{	
		System.out.println("Enter the checking account number.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(this.getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else if(controller.getAccountByNum(this.getCommandInt()) instanceof SavingsAccount)
		{
			System.out.println("This is not a checking account.");
		}
		else
		{
		System.out.println(controller.getCheckHistory(controller.getAccountByNum(getCommandInt())));
		}
	}
	
	public void closeAccount()
	{
		System.out.println("Enter the account number you wish to close.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getAccountByNum(this.getCommandInt()) == null)
		{
			System.out.println("Account does not exist.");
		}
		else
		{
			controller.closeAccount(getCommandInt());
			System.out.println("Account has successfully been closed.");
		}
	}
	
	public void removeClient()
	{
		System.out.println("Enter the client ID.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getClientByID(getCommandInt()) == null)
		{
			System.out.println("Client does not exist.");
		}
		else
		{
		controller.removeClient(controller.getClientByID(getCommandInt()));
		System.out.println("Client has successfully been removed.");
		}
	}
	
	public void sortClientByID()
	{	
		if(controller.getClientListString().equals(""))
		{
			System.out.println("No clients.");
		}
		else
		{
			controller.sortByID();
			this.printClientList();
		}
	}
	
	public void sortClientByFirstName()
	{	
		if(controller.getClientListString().equals(""))
		{
			System.out.println("No clients.");
		}
		else
		{
			controller.sortByFirstName();
			this.printClientList();
		}
	}
	
	public void sortClientByLastName()
	{	
		if(controller.getClientListString().equals(""))
		{
			System.out.println("No clients.");
		}
		else
		{
			controller.sortByLastName();
			this.printClientList();
		}
	}
	
	public void sortClientByCreationDate()
	{	
		if(controller.getClientListString().equals(""))
		{
			System.out.println("No clients.");
		}
		else
		{
			controller.sortByCreationDate();
			this.printClientList();
		}
	}
	
	public void sortAccountsByNumber()
	{
		System.out.println("Enter client ID.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getClientByID(this.getCommandInt()) == null)
		{
			System.out.println("Client does not exist.");
		}
		else
		{
			if(controller.getClientByID(this.getCommandInt()).getAccounts() == null)
			{
				System.out.println("Client has no accounts.");
			}
			else
			{
				controller.sortByAccountNum(controller.getClientByID(getCommandInt()));
				System.out.println(controller.getAccountsString(controller.getClientByID(getCommandInt())));
			}
		}
	}
	
	public void sortAccountsByBalance()
	{
		System.out.println("Enter client ID.");
		this.resetCommand();
		this.promptUserInt();
		
		if(controller.getClientByID(this.getCommandInt()) == null)
		{
			System.out.println("Client does not exist.");
		}
		else
		{
			if(controller.getClientByID(this.getCommandInt()).getAccounts() == null)
			{
				System.out.println("Client has no accounts.");
			}
			else
			{
				controller.sortByAccountBalance(controller.getClientByID(getCommandInt()));
				System.out.println(controller.getAccountsString(controller.getClientByID(getCommandInt())));
			}
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