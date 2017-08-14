import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class JavaHashMap<K, V> implements JavaHashMapInterface<K, V> {

	private int MAXSIZE = 10; // maximum size of hashmap
	
	private double loadFactor = .7; // percentage of size where hashmap must be resized  
	
	/**
	 * Constructor
	 */
	public JavaHashMap() {
		
	}
	
	/**
	 * this function clears all elements in hashmap
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * It returns a copy of all the mappings 
	 * of a map and used for cloning them into another map
	 */
	@Override
	public Map clone() {
		return null;
		
	}
	/**
	 * This method returns true if the hash map has the specified key
	 */
	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Similar to containsKey() method, however it looks for the 
	 * specified value instead of key
	 */
	@Override
	public boolean containValue(V val) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * It returns the value for the specified key
	 */
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * It checks whether the map is empty. If there are 
	 * no key-value mapping present in the map then this 
	 * function returns true else false
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * It returns the Set of the keys fetched from the map
	 */
	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inserts key value mapping into the map. Used in the above example
	 */
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the size of the map – Number of key-value mappings
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * It returns a collection of values of map
	 */
	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * It removes the key-value pair for the specified key. 
	 * Used in the above example
	 */
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Copies all the elements of a map to the another specified map
	 */
	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub
		
	}

}
