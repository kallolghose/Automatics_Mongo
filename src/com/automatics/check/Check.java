package com.automatics.check;
import com.mongodb.*;

import java.util.*;

public class Check 
{
	public static void main(String [] args)
	{
		try
		{
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("checkDB");
			DBCollection table = db.getCollection("testsuite");
			//DBObject document = new BasicDBObject();
			//document.put("Name", "New Name Again");
			//document.put("Description", "New Description Again");
			//table.insert(document);
			
			//DBCursor cursor = table.find();
			//while(cursor.hasNext())
			//{
			//		DBObject someObj = cursor.next();
			//		System.out.println(someObj);
			//}	
			List<DBObject> list = new ArrayList<DBObject>();
			
			DBObject testCases = new BasicDBObject();
			testCases.put("Name", "TC 4");
			testCases.put("Description", "Test Case Four");
			
			list.add(testCases);
			
			testCases = new BasicDBObject();
			testCases.put("Name", "TC 5");
			testCases.put("Description", "Test Case Five");
			
			list.add(testCases);
			
			testCases = new BasicDBObject();
			testCases.put("Name", "TC 6");
			testCases.put("Description", "Test Case Six");
			
			list.add(testCases);
			
			DBObject testSuite = new BasicDBObject();
			testSuite.put("Name", "Second TS");
			testSuite.put("Description", "Some Description");
			testSuite.put("TestCases", list);
			
			DBObject update = new BasicDBObject();
			update.put("Name", "Second TS");
			table.update(update, testSuite);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
