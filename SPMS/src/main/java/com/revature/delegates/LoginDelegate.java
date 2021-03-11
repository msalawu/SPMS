package com.revature.delegates;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Users;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImpl;
@WebServlet("/login/")
public class LoginDelegate implements FrontControllerDelegate{

	private UserServices uServ = 
			new UserServicesImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				// register a user
				Users u = (Users) om.readValue(req.getInputStream(), Users.class);
				@SuppressWarnings("rawtypes")
				Map m = om.readValue(req.getInputStream(), Map.class);
				u.setRoleId((Integer) m.get("roleId"));
				u.setUsername((String) m.get("username"));
				u.setPasswd((String) m.get("passwd"));
				u.setFirstName((String) m.get("firstName"));
				u.setLastName((String) m.get("lastName"));				
				
				try {
					u = (uServ.addUser(u));
				} catch (NonUniqueUsernameException e) {
					resp.sendError(HttpServletResponse.SC_CONFLICT, "Username already exists");
				}
				if (u.getId() == 0) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					resp.getWriter().write(om.writeValueAsString(u));
					resp.setStatus(HttpServletResponse.SC_CREATED);
				}
			} else if ("GET".equals(req.getMethod())) {
				Users u = (Users) req.getSession().getAttribute("users");
				resp.getWriter().write(om.writeValueAsString(u));
				resp.setStatus(200);
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else if (path.contains("login")) {
			if ("POST".equals(req.getMethod()))
				logIn(req, resp);
			else if ("DELETE".equals(req.getMethod()))
				req.getSession().invalidate();
			else
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} else {
			userWithId(req, resp, Integer.valueOf(path));
		}
	}
	
	private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("passwd");
		
		Users u = uServ.getUserByUsername(username);
		if (u != null) {
			if (u.getPasswd().equals(password)) {
				req.getSession().setAttribute("user", u);
				resp.getWriter().write(om.writeValueAsString(u));
			} else {
				resp.sendError(404, "Incorrect password.");
			}
		} else {
			resp.sendError(404, "No user found with that username.");
		}
	}
	
	private void userWithId(HttpServletRequest req, HttpServletResponse resp, Integer id) throws IOException {
		switch (req.getMethod()) {
			case "GET":
				Users u = uServ.getUserById(id);
				if (u != null) {
					resp.getWriter().write(om.writeValueAsString(u));
				} else {
					resp.sendError(404, "User not found.");
				}
				break;
			case "PUT":
				String password = req.getParameter("passwd");
				Users user = (Users) req.getSession().getAttribute("users");
				if (user != null) {
					user.setPasswd(password);
					uServ.updateUser(user);
				} else
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				break;
			case "DELETE":
				user  = om.readValue(req.getInputStream(), Users.class);
				uServ.deleteUser(user);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
		}
	}
}
