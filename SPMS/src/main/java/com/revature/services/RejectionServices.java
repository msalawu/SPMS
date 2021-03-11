package com.revature.services;

import java.util.Set;

import com.revature.beans.Rejections;

public interface RejectionServices {

	public Rejections addRejection(Rejections rej);
	public Rejections getRejectionsById(Integer Id);
	public Set<Rejections>  getAllRejections();
	public Rejections getRejectionsByEditorId(Integer editorId);
	public Rejections getRejectionsByAuthorId(Integer authorId);
	public void updateRejections(Rejections rej); 
}
