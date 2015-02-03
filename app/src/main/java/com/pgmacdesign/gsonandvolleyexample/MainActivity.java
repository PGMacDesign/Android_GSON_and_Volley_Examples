package com.pgmacdesign.gsonandvolleyexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pgmacdesign.gsonandvolleyexample.gsonstuff.SerializeRequest;
import com.pgmacdesign.gsonandvolleyexample.gsonstuff.gsonparsingresponse.LoginGSON;
import com.pgmacdesign.gsonandvolleyexample.gsonstuff.gsonparsingresponse.ParseJSON;
import com.pgmacdesign.gsonandvolleyexample.volleystuff.VolleySingleton;

import org.json.JSONObject;


public class MainActivity extends Activity {

	//Singleton requestQueue for making volley requests
	RequestQueue requestQueue = VolleySingleton.getsInstance().getmRequestQueue(); //This is utilizing the singleton

	//LoginGSON object for pulling data into GSON format and deserializing them
	LoginGSON loginGSON;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//SerializeRequest object
		SerializeRequest sr = new SerializeRequest();

		//String of the returned JSON string that holds the session id within it after being deserialized
		String sessIDJSON = sr.returnSessionID();
		Log.d("Session ID JSON Request: ", sessIDJSON);

		//Gets the session ID by parsing in the json string above. This also subsequently calls All
		//methods via a cascading effect
		getTheSessionID(sessIDJSON);
	}

	//Gets the session ID json string reply by sending out a volley request
	private void getTheSessionID(String str) {

		//This is a JSON POST Request with a json string (like the one made via Lacuna builders)
		try {
			//To access the login section, need to use this prefix of an HTML URL, hard coding here
			String url = "https://us1.lacunaexpanse.com/empire";

			Log.d("Passed string :", str);
			//Create a JSONObject (Not a gson JsonObject, different things)
			JSONObject jsonObject = new JSONObject(str);

			JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {

							Log.d("Response from Server: ", response.toString());

							//ParseJSON object
							ParseJSON parseJSON = new ParseJSON();

							//Using the LoginGSON object to get the deserialized data from the GSON code and putting it into the object
							loginGSON = parseJSON.parseTheJSON(response.toString());

							//Printing out the actual session id from the deserialized code
							Log.d("TESTING SESSION ID", loginGSON.result.session_id);

							//Build a mail JSON request using the session ID
							buildMailRequest(loginGSON.result.session_id);
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("Error.Response: ", error.getMessage());
						}
					}
			);

			requestQueue.add(getRequest);
			Log.d("It DID", " Fire");

		} catch (Exception e){
			e.printStackTrace();
			Log.d("Did Not", " Fire");
		}
	}

	//Builds a mail request JSON string which is in the SerializeRequest class
	private void buildMailRequest(String session_id) {
		String outputJSON = null;
		//Create a SerializeRequest object
		SerializeRequest sr = new SerializeRequest();
		//Set the string to the return from the json converter
		outputJSON = sr.ReturnMailRequest(session_id);
		//Call the method to send a request via volley to return the mail data via passing in the json we just made
		getTheInbox(outputJSON);
	}


	//Gets the inbox String. It takes in a JSONString that is already serialized and ready to be sent to the server
	private void getTheInbox(String json_in) {

		//This is a JSON POST Request with a json string (like the one made via Lacuna builders)
		try {
			//Accessing the mailbox needs the inbox prefix, hard coding here
			String url = "https://us1.lacunaexpanse.com/inbox"; //Change to empire to check for session id

			//Create a JSONObject (Not a gson JsonObject, different things)
			JSONObject jsonObject = new JSONObject(json_in);

			//Volley request, sends a POST method to the server via the jsonstring passed in
			JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// display response
							//Print out the mail response to the logcat
							Log.d("Mail Should Be Here: ", response.toString());
							//This is effectively the last line of the code and is the very last thing called before terminating
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("Error.Response: ", error.getMessage());
						}
					}
			);

			requestQueue.add(getRequest);
			Log.d("It DID", " Fire");

		} catch (Exception e){
			e.printStackTrace();
			Log.d("Did Not", " Fire");
		}
	}
}
