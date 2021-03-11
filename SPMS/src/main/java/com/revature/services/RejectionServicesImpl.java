package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Rejections;
import com.revature.data.DAOFactory;
import com.revature.data.RejectionsDAO;

public class RejectionServicesImpl implements RejectionServices{

	private RejectionsDAO rjDao;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RejectionServicesImpl.class);
	
	public RejectionServicesImpl() {
		rjDao = DAOFactory.getRejectionsDAO();
	}
	public Rejections addRejection(Rejections rej) {
		
		return rjDao.add(rej);
	}

	public Rejections getRejectionsById(Integer Id) {
		
		return rjDao.getById(Id);
	}

	public Rejections getRejectionsByEditorId(Integer editorId) {
		
		return rjDao.getById(editorId);
	}

	public Rejections getRejectionsByAuthorId(Integer authorId) {
		
		return rjDao.getById(authorId);
	}

	public void updateRejections(Rejections rej) {
		if (getRejectionsById(rej.getId()) != null)
			rjDao.update(rej);
		else
			log.debug("The Rejection status to the Story Pitch has been updated in our database.");
	}
	@Override
	public Set<Rejections> getAllRejections() {
		
		return rjDao.getAll();
	}

	
}
