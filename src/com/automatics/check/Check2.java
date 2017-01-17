package com.automatics.check;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.automatics.mongo.packages.AutomaticsDBConnection;
import com.automatics.mongo.packages.AutomaticsDBObjectMapQueries;
import com.automatics.mongo.packages.AutomaticsDBTestCaseQueries;
import com.automatics.mongo.packages.AutomaticsDBTestSuiteQueries;
import com.mongodb.DB;

public class Check2 {
	public static void main(String[] args) 
	{
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("tcName", "Test_Case1")
			   .add("tcDesc", "Test Case Description")
			   .add("tcType", "Some Type")
			   .add("tcIdentifier", "Some Identifier")
			   .add("tcObjectMapLink", Json.createArrayBuilder().add("First").add("Second").build())
			   .add("tcSteps", Json.createArrayBuilder().add(Json.createObjectBuilder()
					   											  .add("stepNo", "1")
					   											  .add("stepOperation", "OpenURL")
					   											  .add("stepPageName", "NA")
					   											  .add("stepObjName", "NA")
					   											  .add("stepArgument","ARG1")
					   											  .add("stepVarName", "Some Name")
					   											  .add("omName", "First")))
			    .add("tcParams", Json.createArrayBuilder().add(Json.createObjectBuilder().add("iterNum", "1").add("iterParams", 
			    		Json.createArrayBuilder().add(Json.createObjectBuilder().add("iparamName", "First Name").add("iparamValue", "First Value")))));
	
		JsonObject obj = builder.build();
		
		DB db = AutomaticsDBConnection.getConnection("localhost", 27017, "checkDB");
		//AutomaticsDBTestCaseQueries.postTC(db, obj);
		//JsonObject someObj = AutomaticsDBTestCaseQueries.getTC(db, "Test Case1");
		System.out.println(AutomaticsDBTestCaseQueries.getAllTC(db));
		
		builder = Json.createObjectBuilder();
		builder.add("omName", "First Object")
			   .add("omDesc", "Some Description")
			   .add("omIdentifier", "Some Identifier")
			   .add("omDetails", Json.createArrayBuilder().add(
					   	Json.createObjectBuilder().add("pageName", "Some pagename1")
					   							   .add("objName", "Some objectName1")
					   							   .add("locatorType", "Type")
					   							   .add("locatorInfo","info")
					   							   
					   )
					   .add(Json.createObjectBuilder().add("pageName", "Some pagename2")
   							   .add("objName", "Some objectName2")
   							   .add("locatorType", "Type2")
   							   .add("locatorInfo","info2")));
	
		
		//AutomaticsDBObjectMapQueries.postOM(db, builder.build());
		//System.out.println(AutomaticsDBObjectMapQueries.getAllOM(db));
		//System.out.println(AutomaticsDBObjectMapQueries.deleteOM(db, "First Object"));
		System.out.println(AutomaticsDBObjectMapQueries.getAllOM(db));
		
		builder = Json.createObjectBuilder();
		builder.add("tsName", "FisrtTestCase")
			   .add("tsDesc", "Test Suite description")
			   .add("tsIdentifier", "SomeIdentifier")
			   .add("tsTCLink", Json.createArrayBuilder().add(Json.createObjectBuilder().add("tcName", "Test_Case1")
					   																	.add("tcParams", Json.createArrayBuilder().add(
					   																		Json.createObjectBuilder().add("tcparamName","Param1")
					   																								  .add("tcparamValue","Value1")
					   																								  .build()	
					   																			
					   																	)
					   																	.add(Json.createObjectBuilder().add("tcparamName", "Param2")
					   																								   .add("tcparamValue", "Value2").build())
					   																	.build())));
		//AutomaticsDBTestSuiteQueries.postTC(db, builder.build());
		System.out.println(AutomaticsDBTestSuiteQueries.getAllTS(db));
		
	}
}
