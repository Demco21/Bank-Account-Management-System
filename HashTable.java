package hashtable;

public class HashTable<K,V>
{
	private HashNode<K, V>[] hashTable;
	private int capacity;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashTable(int capacity)
	{
		hashTable = new HashNode[capacity];
		this.capacity = capacity;
		this.size = 0;
		
		for(int i = 0; i < size; i++)
			hashTable[i] = null;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public boolean isEmpty()
	{
		if(size == 0)
			return true;
		else
			return false;
	}
	
    private int getIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode % capacity);
        return index;
    }
    
    public V get(K key)
    {
        // Find head of chain for given key
        int hashIndex = getIndex(key);
        HashNode<K, V> head = hashTable[hashIndex];
 
        // Search key in chain
        while (head != null)
        {
            if (head.getKey().equals(key))
                return head.getValue();
            head = head.getNext();
        }
 
        // If key not found
        return null;
    }
    
	@SuppressWarnings("unchecked")
	public void add(K key, V value)
    {
        // Find head of chain for given key
        int hashIndex = getIndex(key);
        HashNode<K, V> head = hashTable[hashIndex];
 
        // Check if key is already present
        while (head != null)
        {
            if (head.getKey().equals(key))
            {
                head.setValue(value);
                return;
            }
            head = head.getNext();
        }
 
        head = hashTable[hashIndex];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.setNext(head);
        hashTable[hashIndex] = newNode;
        size++;
 
        /* If load factor goes beyond threshold, then double hash table size */
        if ((1.0*size)/capacity >= 0.7)
        {
        	HashNode<K,V>[] tempTable = hashTable;
        	capacity = 2 * capacity;
            hashTable = new HashNode[capacity];
            size = 0;

            for (int i = 0; i < capacity; i++)
                hashTable[i] = null;
            
            for (HashNode<K, V> headNode : tempTable)
            {
                while (headNode != null)
                {
                    add(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
            }
        }
    }
    
    // Method to remove a given key
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int hashIndex = getIndex(key);
 
        // Get head of chain
        HashNode<K, V> head = hashTable[hashIndex];
 
        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null)
        {
            // If Key found
            if (head.getKey().equals(key))
                break;
 
            // Else keep moving in chain
            prev = head;
            head = head.getNext();
        }
 
        // If key was not there
        if (head == null)
            return null;
 
        // Remove key
        if (prev != null)
            prev.setNext(head.getNext());
        else
        	hashTable[hashIndex] = head.getNext();
        size--;
 
        return head.getValue();
    }
}
