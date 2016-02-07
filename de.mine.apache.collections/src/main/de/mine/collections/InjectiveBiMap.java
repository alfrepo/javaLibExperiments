package de.mine.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Collection allows to retrieve data
 * <ol>
 * <li>by key and
 * <li>by value.
 * </ol>
 * This collection is <b>injective</b>: same object can be associated with
 * multiple keys - {@link #getKeys(Object)} returns a list of associated keys.
 * 
 * @author skip
 * 
 */
public class InjectiveBiMap<T> {
	private TreeMap<Integer, T> rowToObject = new TreeMap<>();
	private HashMap<T, List<Integer>> objectToRows = new HashMap<>();

	public void put(Integer key, T value) {
		rowToObject.put(key, value);
		getRows(value, true).add(key);
	}

	public boolean remove(T value) {
		if (objectToRows.containsKey(value)) {
			// remove all keys
			for (Integer key : objectToRows.get(value)) {
				rowToObject.remove(key);
			}
			objectToRows.remove(value);
			return true;
		}
		return false;
	}


	public void removeByKey(Integer key) {
		if (rowToObject.containsKey(key)) {
			Object value = rowToObject.get(key);
			rowToObject.remove(key);

			List<Integer> keys = objectToRows.get(value);
			keys.remove(key);

			if (keys == null || keys.isEmpty()) {
				objectToRows.remove(value);
			}
		}
	}

	/**
	 * Is immutable
	 * 
	 * @return all available values
	 */
	public List<T> getValues() {
		return new ArrayList<>(objectToRows.keySet());
	}

	/**
	 * Is immutable
	 * 
	 * @return all available keys
	 */
	public List<Integer> getKeys() {
		return new ArrayList<>(rowToObject.keySet());
	}

	public T getValue(Integer key) {
		return rowToObject.get(key);
	}

	public List<Integer> getKeys(T value) {
		List<Integer> mutable = getRows(value, false);
		List<Integer> immutable = null;
		if (mutable != null) {
			immutable = new ArrayList<>(getRows(value, false));
		} else {
			immutable = new ArrayList<>();
		}
		return immutable;
	}
	
	public void clear(){
		rowToObject.clear();
		objectToRows.clear();
	}
	

	private List<Integer> getRows(T object, boolean createIfNull) {
		List<Integer> rows = objectToRows.get(object);
		if (rows == null && createIfNull) {
			rows = new ArrayList<>();
			objectToRows.put(object, rows);
		}
		return rows;
	}

}
