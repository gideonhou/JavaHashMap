import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface JavaHashMapInterface<K, V> {
	
	void clear();
	Map clone();
	boolean containsKey(K key);
	boolean containValue(V val);
	V get(K key);
	boolean isEmpty();
	Set keySet();
	V put(K key, V val);
	int size();
	Collection values();
	V remove(K key);
	void putAll(Map m);

}
