package com.automatics.mongo.packages;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AutomaticsDBTestSuiteQueries 
{
	public static void postTC(DB db,JsonObject data)
	{
		try
		{
			//DBCollection testSuiteTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testsuite");
			DBCollection testSuiteTable = db.getCollection("testsuite");
			DBObject document = (DBObject)JSON.parse(data.toString());
			testSuiteTable.insert(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception  : " + e.getMessage());
		}
	}
	
	public static void updateTS(DB db, String tsName, JsonObject data)
	{
		try
		{
			DBCollection testSuiteTable = db.getCollection("testsuite");
			BasicDBObject dbQuery = new BasicDBObject();
			dbQuery.put("tsName", tsName);
			DBObject updateJson = (DBObject) JSON.parse(data.toString());
			testSuiteTable.update(dbQuery, updateJson);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static JsonObject getTS(DB db,String tsName)
	{
		try
		{
			//DBCollection testSuiteTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testsuite");
			DBCollection testSuiteTable = db.getCollection("testsuite");
			DBObject projection = new BasicDBObject();
			projection.put("_id", 0);
			DBObject findObj = new BasicDBObject();
			findObj.put("tsName", tsName);
			DBCursor cursor = testSuiteTable.find(findObj,projection);
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
	
	public static ArrayList<String> getAllTS(DB db)
	{
		try
		{
			ArrayList<String> ret = new ArrayList<String>();
			//DBCollection testSuiteTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testsuite");
			DBCollection testSuiteTable = db.getCollection("testsuite");
			DBCursor cursor = testSuiteTable.find();
			while(cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();
				ret.add(obj.getString("tsName"));
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
	
	public static boolean deleteTS(DB db,String tsName)
	{
		try
		{
			//DBCollection testSuiteTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "testsuite");
			DBCollection testSuiteTable = db.getCollection("testsuite");
			BasicDBObject document = new BasicDBObject();
			document.put("tsName", tsName);
			testSuiteTable.remove(document);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return false;
	}
}
