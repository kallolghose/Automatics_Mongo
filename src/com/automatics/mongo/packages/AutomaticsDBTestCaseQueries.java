package com.automatics.mongo.packages;

import java.io.StringReader;
import java.util.ArrayList;

import com.mongodb.*;
import com.mongodb.util.JSON;

import javax.json.*;

public class AutomaticsDBTestCaseQueries 
{
	public static void postTC(DB db,JsonObject data)
	{
		try
		{
			//DBCollection testCaseTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testcase");
			DBCollection testCaseTable = db.getCollection("testcase");
			DBObject document = (DBObject)JSON.parse(data.toString());
			testCaseTable.insert(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static void updateTC(DB db, String tcName, JsonObject data)
	{
		try
		{
			DBCollection testCaseTable = db.getCollection("testcase");
			BasicDBObject dbQuery = new BasicDBObject();
			dbQuery.put("tcName", tcName);
			DBObject updateJson = (DBObject) JSON.parse(data.toString());
			testCaseTable.update(dbQuery, updateJson);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static JsonObject getTC(DB db,String tcName)
	{
		try
		{
			//DBCollection testCaseTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testcase");
			DBCollection testCaseTable = db.getCollection("testcase");
			DBObject projection = new BasicDBObject();
			projection.put("_id", 0);
			DBObject findObj = new BasicDBObject();
			findObj.put("tcName", tcName);
			DBCursor cursor = testCaseTable.find(findObj,projection);
			JsonObject jObj = null;
			JsonReader jsonReader = null;
			while(cursor.hasNext())
			{
				String jsonString = cursor.next().toString();
				jsonReader = Json.createReader(new StringReader(jsonString));
				jObj = jsonReader.readObject();
				jsonReader.close();
			}
			return jObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	public static ArrayList<String> getAllTC(DB db)
	{
		try
		{
			ArrayList<String> ret = new ArrayList<String>();
			//DBCollection testCaseTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testcase");
			DBCollection testCaseTable = db.getCollection("testcase");
			DBCursor cursor = testCaseTable.find();
			while(cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();
				ret.add(obj.getString("tcName"));
			}
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	public static boolean deleteTC(DB db,String tcName)
	{
		try
		{
			//DBCollection testCaseTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testcase");
			DBCollection testCaseTable = db.getCollection("testcase");
			BasicDBObject document = new BasicDBObject();
			document.put("tcName", tcName);
			testCaseTable.remove(document);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception  : " + e.getMessage());
		}
		return false;
	}
	
	
}
