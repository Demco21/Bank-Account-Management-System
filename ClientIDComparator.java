import java.util.Comparator;

public class ClientIDComparator implements Comparator<IClient>
{
	@Override
	public int compare(IClient c1, IClient c2) 
	{
		return c1.getClientID() - c2.getClientID();
	}
}
