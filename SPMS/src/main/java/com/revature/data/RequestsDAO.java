package com.revature.data;

import com.revature.beans.Requests;

public interface RequestsDAO extends GenericDAO<Requests>{

	public Requests add(Requests req);
}
