package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exceptions.NotEnoughPointsException;

public interface FrontControllerDelegate {
	void process(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, NotEnoughPointsException;

}
