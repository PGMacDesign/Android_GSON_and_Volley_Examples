package com.pgmacdesign.gsonandvolleyexample.gsonstuff.gsonparsingresponse;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
This class parses JSON using GSON to do so. If utilizes the respective classes in the same package to
deserialize the data and place it into the correct data type.
*/
public class ParseJSON {

	//This is the head of the objects in the json object structure. It holds smaller objects of each matching the json tree structure
	LoginGSON loginObject = new LoginGSON();

	//Returns a LoginGSON object and takes in the JSON string (received from the website call) to be be deserialized.
	public LoginGSON parseTheJSON(String jsonStr){
		try {

			//GSON Builder to use for deserialization
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			//Set the object equal to the deserialized data. If it returned an array of objects, would need to change structure here
			loginObject = gson.fromJson(jsonStr, LoginGSON.class);


		} catch (Exception ex) {
			Log.e("Error", "Failed to parse JSON due to: " + ex);
		}

		return loginObject;
	}


}