package de.mine.apache.collections;

import java.util.UUID;

import org.apache.commons.collections.map.MultiKeyMap;

public class MultiKeyMapExperiments {

	public static void main(String[] args) {
		DomainObject domainObject1 = new DomainObject();
		DomainObject domainObject2 = new DomainObject();
		DomainObject domainObject3 = new DomainObject();
		
		MultiKeyMap multiKeyMap = new MultiKeyMap();
		multiKeyMap.put(new Integer(1), domainObject1);
		multiKeyMap.put(new Integer(2), domainObject2);
		multiKeyMap.put(new Integer(3), domainObject3);
		
		multiKeyMap.containsKey(1);
		multiKeyMap.containsKey(domainObject2);
		
		System.out.println("The end");
	}

}




class DomainObject{
	public UUID uuid = UUID.randomUUID();
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof DomainObject){
			return ((DomainObject)object).uuid.equals(uuid);
		}
		return false;
	}
}
