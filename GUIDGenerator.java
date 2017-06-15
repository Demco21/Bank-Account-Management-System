
public class GUIDGenerator 
{
	private int lastIDUsed = 1;
	
	public GUIDGenerator() 
	{
	
	}

	public int generateID()
	{
		int id = this.lastIDUsed;
		this.lastIDUsed++;
		return id;
	}
	
	public int getLastID()
	{
		return this.lastIDUsed-1;
	}
	
}
