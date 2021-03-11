package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Requests;
import com.revature.data.DAOFactory;
import com.revature.data.RequestsDAO;

public class RequestServicesImpl implements RequestServices{

	private RequestsDAO reqDao;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RequestServicesImpl.class);
	public RequestServicesImpl() {
		reqDao = DAOFactory.getRequestsDAO();
	}
	public Requests addRequest(Requests requests) {
		
		return reqDao.add(requests);
	}
	public Requests getRequestsById(Integer Id) {
		
		return reqDao.getById(Id);
	}
	public Requests getRequestsByEditorId(Integer editorId) {
		
		return reqDao.getById(editorId);
	}
	public Requests getRequestsByAuthorId(Integer authorId) {
		
		return reqDao.getById(authorId);
	}
	public void updateRequests(Requests requests) {
		if (getRequestsById(requests.getId()) != null)
			reqDao.update(requests);
		else
			log.debug("You have successfully update your request to the author!");
	}
	@Override
	public Set<Requests> getAllRequests() {
		
		return reqDao.getAll();
	}
}
