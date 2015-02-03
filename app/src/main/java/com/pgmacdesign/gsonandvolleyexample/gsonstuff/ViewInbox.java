package com.pgmacdesign.gsonandvolleyexample.gsonstuff;

//View Inbox Method
public class ViewInbox {
	public int page_number;
	public String[] tags;

	//Passed in with page number and tags
	public ViewInbox(int aPage_number, String[] aTags){
		this.page_number = aPage_number;
		this.tags = aTags;
	}

	//Passed in with tags only
	public ViewInbox(String[] aTags){
		this.tags = aTags;
	}

	//No variables passed, just a view_inbox call
	public ViewInbox(){

	}
}
