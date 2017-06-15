import java.util.Comparator;

public class ClientCreationDateComparator implements Comparator<IClient>
{
	@Override
	public int compare(IClient c1, IClient c2) 
	{
		if((c1.getCreationDate()).before(c2.getCreationDate()) == true)
			return -1;
		else
			return 1;
	}
}
