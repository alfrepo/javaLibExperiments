package de.mine.apache.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import de.mine.collections.InjectiveBiMap;

public class RemoveHashmapKeyTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveHashmapKey(){
		
		HashMap<Object, List<Integer>> objectToRows = new HashMap<>();
		
		DomainObject d1 = new DomainObject("d1");
		DomainObject d2 = new DomainObject("d2");
		
		objectToRows.put(d1, new ArrayList<Integer>());
		objectToRows.put(d2, new ArrayList<Integer>());

		objectToRows.remove(d1);
		objectToRows.remove(d2);
		
	}
	
	
	class DomainObject{
		public UUID uuid = UUID.randomUUID();
		public String name;
		
		private DomainObject(String objectName){
			this.name = objectName;
		}
		
		@Override
		public boolean equals(Object object) {
			if(object instanceof DomainObject){
				return ((DomainObject)object).uuid.equals(uuid);
			}
			return false;
		}
	}
}
