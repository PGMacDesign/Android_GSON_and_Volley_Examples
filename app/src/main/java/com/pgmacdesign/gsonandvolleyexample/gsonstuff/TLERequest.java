package com.pgmacdesign.gsonandvolleyexample.gsonstuff;

import java.util.List;

//This compiles all base elements into a class that can be used for serialization
public class TLERequest {

	//id, jsonrpc, method, params
	public int id = 1;
	String jsonrpc = "2.0";
	public String method;
	public List<Object> params;

	public TLERequest(){
	}

}
