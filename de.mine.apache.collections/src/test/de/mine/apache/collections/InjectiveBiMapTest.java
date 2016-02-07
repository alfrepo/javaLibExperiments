package de.mine.apache.collections;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import de.mine.collections.InjectiveBiMap;

public class InjectiveBiMapTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testOneObjectTwoRows(){
		InjectiveBiMap<DomainObject> injectiveBiMap = new InjectiveBiMap<DomainObject>();
		
		DomainObject d1 = new DomainObject("d1");
		DomainObject d2 = new DomainObject("d2");

		injectiveBiMap.put(1, d1);
		injectiveBiMap.put(2, d1);
		injectiveBiMap.put(3, d1);
		injectiveBiMap.put(4, d2);
		
		Assert.assertTrue(injectiveBiMap.getKeys().contains(1));
		Assert.assertTrue(injectiveBiMap.getKeys().contains(2));
		Assert.assertTrue(injectiveBiMap.getKeys().contains(3));
		Assert.assertTrue(injectiveBiMap.getKeys().contains(4));
		Assert.assertTrue(injectiveBiMap.getValues().contains(d1));
		Assert.assertTrue(injectiveBiMap.getValues().contains(d2));
		
		Assert.assertFalse(injectiveBiMap.getKeys(d1).isEmpty());
		Assert.assertFalse(injectiveBiMap.getKeys(d2).isEmpty());
		
		Assert.assertTrue(injectiveBiMap.getValue(1) != null);
		Assert.assertTrue(injectiveBiMap.getValue(2) != null);
		Assert.assertTrue(injectiveBiMap.getValue(3) != null);
		Assert.assertTrue(injectiveBiMap.getValue(4) != null);
		
		
		Assert.assertTrue(injectiveBiMap.getKeys(d1).size() == 3);
		Assert.assertTrue(injectiveBiMap.getKeys(d2).size() == 1);
		
		Assert.assertTrue(  injectiveBiMap.getKeys(d1).contains(1) );
		Assert.assertTrue(  injectiveBiMap.getKeys(d1).contains(2) );
		Assert.assertTrue(  injectiveBiMap.getKeys(d1).contains(3) );
		Assert.assertTrue( !injectiveBiMap.getKeys(d1).contains(4) );
		
		Assert.assertTrue(  injectiveBiMap.getKeys(d2).contains(4) );
		
		
		
		Assert.assertTrue( injectiveBiMap.getKeys(d1).contains(3) );
		injectiveBiMap.removeByKey(3);
		Assert.assertTrue( !injectiveBiMap.getKeys(d1).contains(3) );
		
		Assert.assertTrue( injectiveBiMap.getKeys(d1).contains(2) );
		injectiveBiMap.removeByKey(2);
		Assert.assertTrue( !injectiveBiMap.getKeys(d1).contains(2) );
		
		Assert.assertTrue( injectiveBiMap.getKeys(d1).contains(1) );
		injectiveBiMap.removeByKey(1);
		Assert.assertTrue( injectiveBiMap.getKeys(d1).isEmpty() );
		// here the d1 should disappear as value, after rows 1,2,3 were deleted 
		
		
		
		Assert.assertTrue( injectiveBiMap.getKeys(d2).contains(4) );
		Assert.assertTrue( injectiveBiMap.getKeys(d2).size() == 1 );
		injectiveBiMap.remove(d2);
		Assert.assertFalse( injectiveBiMap.getKeys(d2).contains(4) );
		Assert.assertTrue( injectiveBiMap.getKeys(d2).size() == 0 );
		Assert.assertTrue( injectiveBiMap.getKeys(d2).isEmpty() );
		
		
		Assert.assertFalse(injectiveBiMap.getKeys().contains(1));
		Assert.assertFalse(injectiveBiMap.getKeys().contains(2));
		Assert.assertFalse(injectiveBiMap.getKeys().contains(3));
		Assert.assertFalse(injectiveBiMap.getKeys().contains(4));
		Assert.assertFalse(injectiveBiMap.getValues().contains(d1));
		Assert.assertFalse(injectiveBiMap.getValues().contains(d2));
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
