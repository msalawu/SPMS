package com.revature.services;

import java.util.Set;

import com.revature.beans.Requests;

public interface RequestServices {

	public Requests addRequest(Requests requests);
	public Requests getRequestsById(Integer Id);
	public Set<Requests>  getAllRequests();
	public Requests getRequestsByEditorId(Integer editorId);
	public Requests getRequestsByAuthorId(Integer authorId);
	public void updateRequests(Requests requests);
}
