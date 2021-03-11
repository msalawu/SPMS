package com.revature.data;

import com.revature.beans.Editor;

public interface EditorDAO extends GenericDAO<Editor>{

	public Editor add(Editor e);
	public Editor getByUsername(String username);
}