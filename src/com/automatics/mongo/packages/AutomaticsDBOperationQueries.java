package com.automatics.mongo.packages;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class AutomaticsDBOperationQueries 
{
	public static void postOPN(DB db,JsonObject data)
	{
		try
		{
			//DBCollection oprTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "operation");
			DBCollection oprTable = db.getCollection("operation");
			DBObject document = (DBObject)JSON.parse(data.toString());
			oprTable.insert(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static void updateOPN(DB db, String oprName, JsonObject data)
	{
		try
		{
			DBCollection oprTable = db.getCollection("operation");
			BasicDBObject dbQuery = new BasicDBObject();
			dbQuery.put("opnName", oprName);
			DBObject updateJson = (DBObject) JSON.parse(data.toString());
			oprTable.update(dbQuery, updateJson);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
	}
	
	public static JsonObject getOPN(DB db,String opnName)
	{
		try
		{
			//DBCollection oprTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "operation");
			DBCollection oprTable = db.getCollection("operation");
			DBObject projection = new BasicDBObject();
			projection.put("_id", 0);
			DBObject findObj = new BasicDBObject();
			findObj.put("opnName", opnName);
			DBCursor cursor = oprTable.find(findObj,projection);
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
	
	
	public static JsonObject getAllOPN(DB db)
	{
		try
		{
			JsonObject jObj = null;
			//DBCollection oprTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "operation");
			DBCollection oprTable = db.getCollection("operation");
			DBCursor cursor = oprTable.find();
			String jsonString = "{\"Records\":[";
			while(cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();
				jsonString = jsonString + obj.toString() + ",";
			}
			jsonString = jsonString.substring(0,jsonString.length()-1);
			jsonString = jsonString + "]}";
			JsonReader jsonReader = null;
			jsonReader = Json.createReader(new StringReader(jsonString));
			jObj = jsonReader.readObject();
			jsonReader.close();
			return jObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	public static boolean deleteOPN(DB db,String opnName)
	{
		try
		{
			//DBCollection oprTable = AutomaticsDBConnection.getConnection("localhost", 27017, "automatics", "operation");
			DBCollection oprTable = db.getCollection("operation");
			BasicDBObject document = new BasicDBObject();
			document.put("opnName", opnName);
			oprTable.remove(document);
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
