import java.util.Comparator;

public class ClientLastNameComparator implements Comparator<IClient>
{
	@Override
	public int compare(IClient c1, IClient c2) 
	{
		return (c1.getAccountHolder().getLastName()).compareToIgnoreCase(c2.getAccountHolder().getLastName());
	}
}
