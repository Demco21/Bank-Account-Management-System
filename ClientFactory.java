package system;

public class ClientFactory 
{
	public IClient createClient(String firstname, String lastname, String address, int clientID)
	{
		Person person = new Person(firstname, lastname, address);
		IClient client = new Client(person, clientID);
		return client;
	}
}
