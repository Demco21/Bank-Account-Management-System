import java.util.Comparator;

public class AccountBalanceComparator implements Comparator<IAccount>
{
	@Override
	public int compare(IAccount a1, IAccount a2) 
	{
		if(a1.getBalance() - a2.getBalance() < 0)
			return 1;
		else if(a1.getBalance() - a2.getBalance() > 0)
			return -1;
		else
			return 0;
	}
}
