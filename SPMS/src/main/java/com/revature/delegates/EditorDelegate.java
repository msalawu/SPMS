package com.revature.delegates;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Genre;
import com.revature.beans.Rejections;
import com.revature.beans.Requests;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.StoryType;
import com.revature.services.RejectionServices;
import com.revature.services.RejectionServicesImpl;
import com.revature.services.RequestServices;
import com.revature.services.RequestServicesImpl;
import com.revature.services.StoryServices;
import com.revature.services.StoryServicesImpl;

public class EditorDelegate implements FrontControllerDelegate {

	private ObjectMapper om = new ObjectMapper();
    private StoryServices sServ = new StoryServicesImpl();
    private RequestServices reqServ = new RequestServicesImpl();
    private RejectionServices rejServ = new RejectionServicesImpl();
    Logger log = Logger.getLogger(AuthorDelegate.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path.contains("status")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Status> statusset = sServ.getAllStatuses();
					resp.getWriter().write(om.writeValueAsString(statusset));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		}
		else if (path.contains("story_type")) {
			switch (req.getMethod()) {
				case "GET":
					Set<StoryType> stSet = sServ.getAllStoryType();
					resp.getWriter().write(om.writeValueAsString(stSet));
					break;
				case "POST":
					StoryType st = om.readValue(req.getInputStream(), StoryType.class);
					sServ.addStoryType(st);
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else if (path.contains("genre")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Genre> genres = sServ.getAllGenre();
					resp.getWriter().write(om.writeValueAsString(genres));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		}else if (path.contains("requests")) {
				switch (req.getMethod()) {
					case "GET":
						Set<Requests> reqSet = reqServ.getAllRequests();
						resp.getWriter().write(om.writeValueAsString(reqSet));
						break;
					case "POST":
						Requests r = om.readValue(req.getInputStream(), Requests.class);
						reqServ.addRequest(r);
						break;
					default:
						resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						break;
				}
		}else if (path.contains("rejections")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Rejections> rejSet = rejServ.getAllRejections();
					resp.getWriter().write(om.writeValueAsString(rejSet));
					break;
				case "POST":
					Rejections rej = om.readValue(req.getInputStream(), Rejections.class);
					rejServ.addRejection(rej);
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else if (path.contains("role")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Role> rolls = sServ.getAllRole();
					resp.getWriter().write(om.writeValueAsString(rolls));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else {
			resp.sendError(404);
		}
	}
}
