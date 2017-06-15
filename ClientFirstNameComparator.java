import java.util.Comparator;

public class ClientFirstNameComparator implements Comparator<IClient>
{
	@Override
	public int compare(IClient c1, IClient c2) 
	{
		return (c1.getAccountHolder().getFirstName()).compareToIgnoreCase(c2.getAccountHolder().getFirstName());
	}
}
