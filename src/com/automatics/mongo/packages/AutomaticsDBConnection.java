package com.automatics.mongo.packages;

import com.mongodb.*;

@SuppressWarnings("deprecation")
public class AutomaticsDBConnection 
{
	public static MongoClient getConnection(String URL,int port)
	{
		try
		{
			MongoClient mongoClient = new MongoClient(URL, port);
			return mongoClient;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	public static DB getConnection(String URL,int port, String dbName)
	{
		try
		{
			MongoClient mongoClient = getConnection(URL, port);
			DB db = mongoClient.getDB(dbName);
			return db;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	public static DBCollection getConnection(String URL,int port, String dbName,String tableName)
	{
		try
		{
			DB db = getConnection(URL, port, dbName);
			DBCollection collection = db.getCollection(tableName);
			return collection;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}
		return null;
	}
	
	
}
