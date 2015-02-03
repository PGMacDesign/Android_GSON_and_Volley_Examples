package com.pgmacdesign.gsonandvolleyexample.gsonstuff;

//Some mock example calls to use for reference
public class ExampleCalls {

	//Call to check mail with Tag correspondence:
	String str0 =
	"{\"jsonrpc\":2,\"id\":1,\"method\":\"view_inbox\",\"params\":[\"f0ae12f4-e1d2-4df0-834d-3242df1b4a43\",{\"tags\":[\"Correspondence\"],\"page_number\":1}]}";

	//Call to check mail with no tags, just all
	String str1 =
	"{\"jsonrpc\":2,\"id\":1,\"method\":\"view_inbox\",\"params\":[\"f0ae12f4-e1d2-4df0-834d-3242df1b4a43\",{\"page_number\":1}]}";

	//Call to read a specific mail with the mail ID being passed
	String str2 =
	"{\"jsonrpc\":2,\"id\":1,\"method\":\"read_message\",\"params\":[\"f0ae12f4-e1d2-4df0-834d-3242df1b4a43\",\"77982356\"]}";

	//Call to delete a message with the message ID being passed
	String str3 =
	"{\"id\":2,\"method\":\"trash_messages\",\"jsonrpc\":\"2.0\",\"params\":[\"f0ae12f4-e1d2-4df0-834d-3242df1b4a43\",[\"77982356\"]]}";

	//Login to server with username and password
	String str4 =
	"{\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":[\"Runescholar\",\"password\",\"6266769d-1f73-4325-a40f-6660c4c6440d\"],\"id\":1}";

}
