import java.util.Collection;
import java.util.LinkedList;

public class CheckingAccount extends Account 
{
	private Collection<Check> cashedChecks = new LinkedList<Check>();
	
	public CheckingAccount() 
	{

	}

	public CheckingAccount(int accountNumber) 
	{
		super(accountNumber);
	}

	public Collection<Check> getCashedChecks() 
	{
		return this.cashedChecks;
	}
	
	public void cashCheck(Check check)
	{
		cashedChecks.add(check);
	}
	
	@Override
	public String toString() 
	{
		if(cashedChecks.isEmpty() == true)
			return "CheckingAccount: " + super.toString() + ", *No cashed checks*\n";
		else
			return "CheckingAccount: " + super.toString() + ", CashedCheckHistory=" + cashedChecks + "\n";
	}
	
}
