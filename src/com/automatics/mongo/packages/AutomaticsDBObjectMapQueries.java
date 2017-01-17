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

public class AutomaticsDBObjectMapQueries 
{
	public static void postOM(DB db,JsonObject data)
	{
		try
		{
			//DBCollection omTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "objectmap");
			DBCollection omTable = db.getCollection("objectmap");
			DBObject document = (DBObject)JSON.parse(data.toString());
			omTable.insert(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static JsonObject getOM(DB db,String omName)
	{
		try
		{
			//DBCollection omTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "objectmap");
			DBCollection omTable = db.getCollection("objectmap");
			DBObject projection = new BasicDBObject();
			projection.put("_id", 0);
			DBObject findObj = new BasicDBObject();
			findObj.put("omName", omName);
			DBCursor cursor = omTable.find(findObj,projection);
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
	
	public static ArrayList<String> getAllOM(DB db)
	{
		try
		{
			ArrayList<String> ret = new ArrayList<String>();
			//DBCollection omTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "objectmap");
			DBCollection omTable = db.getCollection("objectmap");
			DBCursor cursor = omTable.find();
			while(cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();
				ret.add(obj.getString("omName"));
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
	
	public static void updateOM(DB db, String omName, JsonObject data)
	{
		try
		{
			DBCollection omTable = db.getCollection("objectmap");
			BasicDBObject dbQuery = new BasicDBObject();
			dbQuery.put("omName", omName);
			DBObject updateJson = (DBObject) JSON.parse(data.toString());
			omTable.update(dbQuery, updateJson);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static boolean deleteOM(DB db,String omName)
	{
		try
		{
			//DBCollection omTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "objectmap");
			DBCollection omTable = db.getCollection("objectmap");
			BasicDBObject document = new BasicDBObject();
			document.put("omName", omName);
			omTable.remove(document);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Message : " + e.getMessage());
		}
		return false;
	}
	
	
}
