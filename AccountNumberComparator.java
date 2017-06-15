import java.util.Comparator;

public class AccountNumberComparator implements Comparator<IAccount>
{
	@Override
	public int compare(IAccount a1, IAccount a2) 
	{
		return a1.getAccountNumber() - a2.getAccountNumber();
	}
}
