package com.revature.delegates;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Story;
import com.revature.beans.Users;
import com.revature.exceptions.NotEnoughPointsException;
import com.revature.services.StoryServices;
import com.revature.services.StoryServicesImpl;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImpl;

public class AuthorDelegate implements FrontControllerDelegate{

	private ObjectMapper om = new ObjectMapper();
    private StoryServices sService = new StoryServicesImpl();
    private UserServices uService = new UserServicesImpl();
    Logger log = Logger.getLogger(AuthorDelegate.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = (String) req.getAttribute("path");
        Users u = (Users) req.getSession().getAttribute("user");

        if (path == null || path.equals("")) {
            switch (req.getMethod()) {
                case "POST":
                    log.debug("Into post method");
                    // Add a pitch for the author
                    Map m = om.readValue(req.getInputStream(), Map.class);
                    log.debug(m);                    
                    Story s = new Story();
                    s.setId((Integer) m.get("id"));
                    s.setStoryTypeId((Integer) m.get("storyTypeId"));
                    s.setGenreId((Integer) m.get("genreId"));
                    s.setTitle((String) m.get("title"));
                    s.setTagline((String) m.get("tagline"));
                    s.setDescription((String) m.get("description"));
                    s.setAuthorId((Integer) m.get("authorId"));
                    s.setCompletionDate((String) m.get("completionDate"));
                    s.setDescription((String) m.get("description"));
                    
                    res.getWriter().write(om.writeValueAsString(s));
                    req.getSession().setAttribute("user", u);
                    res.setStatus(HttpServletResponse.SC_CREATED);
                    break;
                case "GET":
                    // Gets all pitches for author
                    res.getWriter().write(om.writeValueAsString(sService.getAllStories()));
                    break;
                default:
                    res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } else {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
