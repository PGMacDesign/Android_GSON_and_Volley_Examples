package com.pgmacdesign.gsonandvolleyexample.gsonstuff;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

//Serializes the request after it has been built
public class SerializeRequest {

	//This returns a JSON string that can be used to ping the server for the session ID
	public String returnSessionID(){

		Gson gson = new Gson();

		//List of params to pass to TLERequest
		List<Object> params_list = new ArrayList<>();

		/*
		These are the 3 parameters needed for the login attempt. Note, they CANNOT be used as 3
		object classes, they must be used as strings because if they were objects in a separate class,
		they would be organized wrong for this api. This API requires parameters to be sent with 3
		specific strings passed into the JSON, NOT hasmap value pairs if they were in a class.
		 */
		String username = "Silmarilos";
		String password = "password"; //Change the password here
		String api_key = "6266769d-1f73-4325-a40f-6660c4c6440d";

		//Add these items to the parameters object
		params_list.add(username);
		params_list.add(password);
		params_list.add(api_key);

		//TLE Request compiles all of the data into an object for holding the respective parts of the JSON String
		TLERequest tleRequest = new TLERequest();

		tleRequest.id = 1;
		tleRequest.jsonrpc = "2.0";
		tleRequest.method = "login";
		tleRequest.params = params_list;

		//Get a string by using GSON to serialize the data together into one usable JSON string
		String jsonString = gson.toJson( tleRequest );

		Log.d("Output is: ", jsonString);

		//Return the output created
		return jsonString;
	}

	//Creates a string to be sent for pulling mail requests. Same setup as the returnSessionID method
	public String ReturnMailRequest (String sessid){
			/*
         * We'll need this when we start serializing.
         */
		Gson gson = new Gson();

		//List of params to pass to TLERequest
		List<Object> params_list = new ArrayList<>();

		String sessionID = sessid;

		//View Inbox has optional parameters here, tags (IE correspondence, attack, spies, etc). It
		//Can be left out if you want to pull in ALL mail instead of a specific grouping of mail
		String [] tags = {"Correspondence"};
		//Passing in the page number to be received. This too can be left out if you want to default to page 1
		int page_number = 1;

		//Viewinbox method. Need to pass in the 2 items above so the overloaded method will capture correct set of data
		ViewInbox viewInbox = new ViewInbox(page_number, tags);

		//Add the session id (passed in) and the method
		params_list.add(sessionID);
		params_list.add(viewInbox);

		TLERequest tleRequest = new TLERequest();

		tleRequest.id = 1;
		tleRequest.jsonrpc = "2.0";
		tleRequest.method = "view_inbox";
		tleRequest.params = params_list;

		String jsonString = gson.toJson( tleRequest );

		Log.d("Output is: ", jsonString);

		return jsonString;
	}
}
