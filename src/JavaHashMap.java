import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class JavaHashMap<K, V> implements JavaHashMapInterface<K, V> {

	private int capacity; // maximum size of hashmap
	
	private double loadFactor = .7; // percentage of size where hashmap must be resized  
	
	private int numElements; // number of key-value mappings in map currently
	
	private ArrayList<JavaHashEntry> table; // kv pairs
	
	
	/**
	 * Constructor
	 */
	public JavaHashMap() {
		this.capacity = 10;
		this.numElements = 0;
		this.table = new ArrayList<JavaHashEntry>(Collections.nCopies(capacity, null)); // initialize an arraylist of size capacity with all nulls as values
		
	}
	
	/**
	 * this function clears all elements in hashmap
	 */
	@Override
	public void clear() {
		this.table.clear();
		this.table = new ArrayList<JavaHashEntry>(Collections.nCopies(capacity, null)); // initialize an arraylist of size capacity with all nulls as values
		this.numElements = 0;
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
		int hashcode = key.hashCode() % this.capacity;
		
		int counter = 0; // prevents us from going into infinite loop
		while(counter < this.capacity) { // while the key is not found and we have not made a complete round in searching the map
			
			if(this.table.get(hashcode).getKey().equals(key)) return true;
			else {
				hashcode = linearprobe(hashcode);
				counter++;
			}
		}
		return false;
	}

	/**
	 * Similar to containsKey() method, however it looks for the 
	 * specified value instead of key
	 * Since we don't have a way of looking up values like we do for keys, we will resort to linear searching
	 */
	@Override
	public boolean containValue(V val) {
		
		for(JavaHashEntry keyvaluepair : this.table) {
			if (keyvaluepair != null && keyvaluepair.getValue().equals(val)) return true;
		}
		return false;
	}

	/**
	 * since this hashmap uses linear probing for collision resolution, this function returns the next
	 * index to look up in the map if the first index did not yield desired key/value
	 * 
	 * @param original hashcode
	 * @return new hashcode
	 */
	private int linearprobe(int originalhashcode) {
		return (originalhashcode + 1) % this.capacity;
	}
	/**
	 * It returns the value for the specified key
	 */
	@Override
	public V get(K key) {
		int hashcode = key.hashCode() % this.capacity;
		
		int counter = 0;
		while(counter < this.capacity) {
			JavaHashEntry entry = this.table.get(hashcode);
			if(entry != null && entry.getKey().equals(key)) return entry.getValue();
			else {
				hashcode = linearprobe(hashcode);
				counter++;
			}
		}
		return null;
	}

	/**
	 * It checks whether the map is empty. If there are 
	 * no key-value mapping present in the map then this 
	 * function returns true else false
	 */
	@Override
	public boolean isEmpty() {
		if (this.numElements == 0) return true;
		else return false;
	}

	/**
	 * It returns the Set of the keys fetched from the map
	 * iterates through entire table table to get keys
	 * O(capacity)
	 */
	@Override
	public Collection keySet() {
		ArrayList keys = new ArrayList<K>();
		for(int i = 0; i < capacity; i++) {
			if(this.table.get(i) != null) keys.add(table.get(i).getKey());
		}
		return keys;
	}

	/**
	 * Inserts key value mapping into the map. Used in the above example
	 */
	@Override
	public V put(K key, V value) {
		
		JavaHashEntry toInsert = new JavaHashEntry(key, value);
		int hashcode = key.hashCode() % this.capacity;
		
		int counter = 0;
		while(counter < this.capacity) {
			if(this.table.get(hashcode) == null) {
				this.table.set(hashcode, toInsert);
				this.numElements++;
				break;
			} else {
				hashcode = linearprobe(hashcode);
				counter++;
			}
		}
		
		if( ((double)this.numElements / (double)this.capacity) >= this.loadFactor) this.resize();
		
		return toInsert.getValue();
	}

	/**
	 * this function doubles the size of the map when the load factor is exceeded
	 * 
	 * O(this.capacity)
	 */
	private void resize() {
		int newCapacity = this.capacity * 2;
		ArrayList<JavaHashEntry> newTable = new ArrayList<JavaHashEntry>(Collections.nCopies(newCapacity * 2, null)); // initialize an arraylist of size capacity with all nulls as values
		
		for(int i = 0; i < this.capacity; i++) { // iterate through entire table, putting non null entries into new table
			JavaHashEntry toPut = this.table.get(i);
			if(toPut != null) {
				int hashcode = toPut.getKey().hashCode() % newCapacity;
				int counter = 0;
				while(counter < newCapacity) {
					if(newTable.get(hashcode) == null) {
						newTable.set(hashcode, toPut);
						break;
					} else {
						hashcode = linearprobe(hashcode);
						counter++;
					}
				}
			}
		}
		this.table = newTable;
		this.capacity = newCapacity;
		
		return;
	}
	
	/**
	 * Returns the size of the map – Number of key-value mappings
	 * 
	 * O(1)
	 */
	@Override
	public int capacity() {
		return this.capacity;
	}
	
	public int numElements() {
		return this.numElements;
	}

	/**
	 * It returns a collection of values of map
	 * O(this.capacity)
	 */
	@Override
	public Collection values() {
		ArrayList<V> values = new ArrayList<V>();
		for(int i = 0; i < this.capacity; i++) {
			if(this.table.get(i) != null) values.add(this.table.get(i).getValue());
		}
		return values;
	}

	/**
	 * It removes the key-value pair for the specified key.
	 * 
	 * O(1) at best. O(this.capacity) at worst 
	 */
	@Override
	public V remove(K key) {
		//if(!this.containsKey(key)) return null;
		
		int hashcode = key.hashCode() % this.capacity;
		int counter = 0;
		V returnValue;
		
		while(counter < this.capacity) {
			JavaHashEntry toPut = this.table.get(hashcode);
			if(toPut != null && toPut.getKey().equals(key)) {
				returnValue = toPut.getValue();
				this.table.set(hashcode, null);
				this.numElements--;
				return returnValue;
			} else {
				hashcode = linearprobe(hashcode);
				counter++;
			}
		}
		return null;
	}

	/**
	 * Copies all the elements of a map to the another specified map
	 */
	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub
		
	}

	private class JavaHashEntry{

		private K key;
		
		private V value;
		
		JavaHashEntry(K inputKey, V inputVal) {
			this.key = inputKey;
			this.value = inputVal;
		}
		
		public K getKey() {
			return this.key;
		}

		public V getValue() {
			return this.value;
		}

		public V setValue(V value) {
			value = this.value;
			return this.value;
		}
	}
}


