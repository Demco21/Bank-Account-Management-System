package system;

public class CheckFactory 
{
	public Check createCheck(int checkNumber, double amount)
	{
		if(amount > 0)
		{
			Check newCheck = new Check(checkNumber, amount);
			return newCheck;
		}
		return null;
	}
}
